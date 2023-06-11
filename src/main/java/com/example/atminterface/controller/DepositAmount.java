package com.example.atminterface.controller;

import com.example.atminterface.CustomDialog;
import com.example.atminterface.Main;
import com.example.atminterface.OptionalMethod;
import com.example.atminterface.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositAmount implements Initializable {
    public TextField depositAmountTf;
    private OptionalMethod method;
    private CustomDialog customDialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        method = new OptionalMethod();
        customDialog = new CustomDialog();

    }

    public void submit(ActionEvent actionEvent) {

        String amountStr = depositAmountTf.getText();
        if (amountStr.isEmpty()) {
            method.show_popup("Please enter deposit amount.", depositAmountTf);
          return;
        }

        double amount = 0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            method.show_popup("Please enter valid amount.", depositAmountTf);
           return;
        }
        boolean isSuccess = new CommonUtil().depositAmount(AtmAuth.userId, amount);
        if (isSuccess) {
            back(null);
            customDialog.showAlertBox("Success","Amount successfully deposited");
        }else {

            customDialog.showAlertBox("Failed !!","Something went wrong !!");
        }
    }

    public void back(ActionEvent actionEvent) {
        new Main().changeScene("dashboard.fxml","");

    }
}
