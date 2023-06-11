package com.example.atminterface.util;

import com.example.atminterface.controller.AtmAuth;
import com.example.atminterface.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonUtil {

    public static boolean isCardNumberIsValid(String cardNumber) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = new DBConnection().getConnection();
            String query = "select  card from users where card = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, cardNumber);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection, ps, rs);
        }

    }

    public boolean updatePin(String cardNumber,String oldPin,String newPin) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = new DBConnection().getConnection();
            String query = "update users set pin = ? where card = ? and pin = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, newPin);
            ps.setString(2, cardNumber);
            ps.setString(3, oldPin);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            DBConnection.closeConnection(connection, ps, rs);
        }
    }

    public boolean depositAmount(int userId,double amount) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = new DBConnection().getConnection();
            connection.setAutoCommit(false);
            String query = "update users set bal =bal+ ? where id = ?";
            ps = connection.prepareStatement(query);
            ps.setDouble(1,amount);
            ps.setInt(2,userId);

            int res = ps.executeUpdate();

            if (res>0){
                ps = null;
                res = 0;

                String queryTransactions = "INSERT INTO transactions(user_id,amount,stat,bal)values (?,?,?,?)";
                ps = connection.prepareStatement(queryTransactions);
                ps.setInt(1,userId);
                ps.setDouble(2,amount);
                ps.setString(3,"DEPOSIT");
                ps.setDouble(4,checkBalanceById(userId));
                res = ps.executeUpdate();

                if (res>0){
                    connection.commit();
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DBConnection.closeConnection(connection, ps, null);
        }
    }

    public boolean withdrawAmount(double amount,int userId) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = new DBConnection().getConnection();
            connection.setAutoCommit(false);
            String query = "update users set bal =bal- ? where id = ?";
            ps = connection.prepareStatement(query);
            ps.setDouble(1,amount);
            ps.setInt(2,userId);

            int res = ps.executeUpdate();

            if (res>0){

                ps = null;
                res = 0;
                String queryTransactions = "INSERT INTO transactions(user_id,amount,stat,bal)values (?,?,?,?)";
                ps = connection.prepareStatement(queryTransactions);
                ps.setInt(1,userId);
                ps.setDouble(2,amount);
                ps.setString(3,"WITHDRAW");
                ps.setDouble(4,checkBalanceById(userId));
                res = ps.executeUpdate();

                if (res>0){
                    connection.commit();
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        } finally {
            DBConnection.closeConnection(connection, ps, null);
        }
    }

    public static boolean isPinIsValid(String cardNumber,String pin) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = new DBConnection().getConnection();
            String query = "select  card,pin,id from users where card = ? and pin = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, cardNumber);
            ps.setString(2, pin);
            rs = ps.executeQuery();
            boolean isSuccess = rs.next();
            if (isSuccess){
                AtmAuth.userId = rs.getInt("id");
            }
            return isSuccess;
        } catch (SQLException e) {
            return false;
        } finally {
            DBConnection.closeConnection(connection, ps, rs);
        }

    }

    public static double checkBalanceById(int userId) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = new DBConnection().getConnection();
            String query = "select  bal from users where id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
           if ( rs.next()){
               return rs.getDouble("bal") ;
           }else {
               return 0;
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;

        } finally {
            DBConnection.closeConnection(connection, ps, rs);
        }

    }
}
