package com.game.pongvictoriousvolleys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Not Ready Yet


/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(800);
            stage.setWidth(1375);
            stage.setResizable(false);
            stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
