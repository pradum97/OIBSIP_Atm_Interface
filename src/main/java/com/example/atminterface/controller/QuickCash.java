package com.example.atminterface.controller;

import com.example.atminterface.CustomDialog;
import com.example.atminterface.Main;
import com.example.atminterface.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class QuickCash implements Initializable {
    private CustomDialog customDialog;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customDialog = new CustomDialog();
    }

    public void cash200Click(MouseEvent mouseEvent) {

        withdraw(200);
    }

    public void cash500Click(MouseEvent mouseEvent) {
        withdraw(500);
    }

    public void cash1000Click(MouseEvent mouseEvent) {
        withdraw(1000);
    }

    public void cash2000Click(MouseEvent mouseEvent) {
        withdraw(2000);
    }

    public void cash5000Click(MouseEvent mouseEvent) {
        withdraw(5000);
    }

    public void cash10000Click(MouseEvent mouseEvent) {

        withdraw(10000);
    }
    private void withdraw(int withdrawAmount) {

        boolean isSuccess = new CommonUtil().withdrawAmount(withdrawAmount,AtmAuth.userId);
        if (isSuccess) {
            customDialog.showAlertBox("Success","Amount successfully withdrawn.");
        }else {
            customDialog.showAlertBox("Failed !!","Something went wrong !!");
        }
    }

    public void back(ActionEvent actionEvent) {

        new Main().changeScene("dashboard.fxml","");
    }
}
