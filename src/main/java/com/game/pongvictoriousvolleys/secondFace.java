package com.game.pongvictoriousvolleys;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class secondFace {


    private Stage stage;
    private Scene scene;
    private Parent root;
    private playerControls data;

    public secondFace () {
        data = new playerControls();
    }

    public void toTheGame(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("gameWork.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTheMenu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
