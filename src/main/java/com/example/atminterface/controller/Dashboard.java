package com.example.atminterface.controller;

import com.example.atminterface.CustomDialog;
import com.example.atminterface.Main;
import com.example.atminterface.OptionalMethod;
import com.example.atminterface.Statements;
import com.example.atminterface.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    private OptionalMethod method;
    private CustomDialog customDialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        method = new OptionalMethod();
        customDialog = new CustomDialog();
    }

    public void quickCashClick(MouseEvent mouseEvent) {

        new Main().changeScene("quickCash.fxml", "Quick Cash");

    }

    public void withdrawClick(MouseEvent mouseEvent) {

        new Main().changeScene("withdrawAmount.fxml", "Withdraw Amount");

    }

    public void depositClick(MouseEvent mouseEvent) {

        new Main().changeScene("depositAmount.fxml","Deposit Amount");
    }

    public void statementClick(MouseEvent mouseEvent) {

        try {
            new Statements().stateView(AtmAuth.userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void balanceEnquiryClick(MouseEvent mouseEvent) {

        new Main().changeScene("balance.fxml", "Balance Enquiry");
    }

    public void changePinClick(MouseEvent mouseEvent) {

        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter new pin.");
        textInputDialog.getDialogPane().setMinSize(300, 200);

        TextField textField = textInputDialog.getEditor();

        textField.setMinHeight(35);
        textField.setStyle("-fx-border-color: grey;-fx-border-radius: 3");

        Button ok = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);

        ok.addEventFilter(ActionEvent.ACTION, ae -> {

            String newPin = textInputDialog.getEditor().getText();
            if (newPin.isEmpty()) {
                method.show_popup("Please enter name pin", textInputDialog.getEditor());
                ae.consume(); //not valid
            }

            boolean isUpdate = new CommonUtil().updatePin(AtmAuth.cardNum, AtmAuth.pin, newPin);
            if (isUpdate) {
                new Main().changeScene("atm_auth.fxml", "ATM INTERFACE");
            }else {

                customDialog.showAlertBox("Failed !!","Something went wrong !!");
            }

        });

        textInputDialog.showAndWait();
    }

    public void cancel(ActionEvent actionEvent) {

        new Main().changeScene("atm_auth.fxml","");

    }
}
