package com.example.atminterface.controller;

import com.example.atminterface.Main;
import com.example.atminterface.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Balance implements Initializable {
    public Label balanceTf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double balance = CommonUtil.checkBalanceById(AtmAuth.userId);
        balanceTf.setText(String.valueOf(balance));
    }

    public void back(ActionEvent actionEvent) {

        new Main().changeScene("dashboard.fxml","");
    }
}
