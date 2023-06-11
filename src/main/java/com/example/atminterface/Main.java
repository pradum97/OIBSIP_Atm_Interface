package com.example.atminterface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("atm_auth.fxml")));
        //  Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("dashboard.fxml")));
        primaryStage.setTitle("ATM INTERFACE");
        primaryStage.setMaximized(true);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/common.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

    public void changeScene(String fxml, String title) {

        try {
            if (null != primaryStage && primaryStage.isShowing()) {
                Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
                primaryStage.getScene().setRoot(pane);
                Platform.runLater(() -> {
                    primaryStage.setTitle("ATM INTERFACE");
                });
                primaryStage.show();
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
