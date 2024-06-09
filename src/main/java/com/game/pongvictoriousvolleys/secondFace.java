package com.game.pongvictoriousvolleys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class secondFace {

    @FXML
    private AnchorPane secondScene;
    @FXML
    private Button backButton;
    @FXML
    private Button actualPlayButton;
    @FXML
    private Rectangle pOne;
    @FXML
    private Rectangle pTwo;
    @FXML
    private Label pOneSettings;
    @FXML
    private Label pTwoSettings;
    @FXML
    private Label streakOne;
    @FXML
    private Label streakTwo;
    @FXML
    private Image crownStreak;

    private Stage stage;
    private Scene scene;
    private Parent root;


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
