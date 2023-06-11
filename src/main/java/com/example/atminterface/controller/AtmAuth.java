package com.example.atminterface.controller;

import com.example.atminterface.Main;
import com.example.atminterface.OptionalMethod;
import com.example.atminterface.util.CommonUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AtmAuth implements Initializable {


    public VBox cardNumberContainer;
    public TextField cardNumTf;
    public VBox pinContainer;
    public TextField pinTf;

    private OptionalMethod method;
    public static String cardNum, pin;
    public static int userId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        method = new OptionalMethod();
        method.hideElement(pinContainer);
    }

    public void submitCardNumber(ActionEvent actionEvent) {

        String cardNumber = cardNumTf.getText();

        if (cardNumber.isEmpty()) {
            method.show_popup("Enter card number", cardNumTf);
            return;
        }

        boolean isCardNumValid = CommonUtil.isCardNumberIsValid(cardNumber);

        if (!isCardNumValid) {
            method.show_popup("Invalid card number", cardNumTf);
            return;
        } else {
            method.hideElement(cardNumberContainer);
            pinContainer.setVisible(true);
        }

    }

    public void submitPin(ActionEvent actionEvent) {

        String pinTfText = pinTf.getText();

        if (pinTfText.isEmpty()) {
            method.show_popup("Enter your pin", pinTf);
            return;
        }

        boolean isPinValid = CommonUtil.isPinIsValid(cardNumTf.getText(), pinTfText);

        if (!isPinValid) {
            method.show_popup("Invalid pin number", pinTf);
            return;
        } else {
            cardNum = cardNumTf.getText();
            pin = pinTf.getText();
            new Main().changeScene("dashboard.fxml", "");
        }
    }


}
