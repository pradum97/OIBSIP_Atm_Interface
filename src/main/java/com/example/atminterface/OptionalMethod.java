package com.example.atminterface;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OptionalMethod {


    public static void minimizedStage(Stage stage ,boolean bool){
        stage.setMaximized(bool);
    }


    public ProgressIndicator getProgressBar(double height, double width) {
        ProgressIndicator pi = new ProgressIndicator();
        pi.indeterminateProperty();
        pi.setPrefHeight(height);
        pi.setPrefWidth(width);

        return pi;
    }

    public void hideElement(Node... node) {
       for (Node n : node){
            n.setVisible(false);
            n.managedProperty().bind(n.visibleProperty());
        }
    }
    public ContextMenu show_popup(String message, Object textField) {
        ContextMenu form_Validator = new ContextMenu();
        form_Validator.setAutoHide(true);
        form_Validator.getItems().add(new MenuItem(message));
        form_Validator.show((Node) textField, Side.RIGHT, 10, 0);
        return form_Validator;
    }

    public void closeStage(Node node) {

        Stage stage = (Stage) node.getScene().getWindow();
        if (stage.isShowing()) {
            stage.close();
        }
    }

    public void selectTable(int index, TableView tableView) {

        if (!tableView.getSelectionModel().isEmpty()) {
            tableView.getSelectionModel().clearSelection();
        }

        tableView.getSelectionModel().select(index);
    }

}
