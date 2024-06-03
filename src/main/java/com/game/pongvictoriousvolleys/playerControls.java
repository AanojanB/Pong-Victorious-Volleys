package com.game.pongvictoriousvolleys;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class playerControls implements Initializable {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty leftPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(upPressed).or(leftPressed).or(downPressed).or(rightPressed);

    private int playerSpeedY = 5;
    private int playerSpeedX = 2;

    @FXML
    private Rectangle playerOne;

    @FXML
    private Rectangle playerTwo;

    @FXML
    private AnchorPane sceneGame;

    @FXML
    void start(ActionEvent event) {
        playerOne.setLayoutY(200);
        playerOne.setLayoutX(280);

        playerTwo.setLayoutY(100);
        playerTwo.setLayoutX(180);
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if(wPressed.get()) {
                playerOne.setLayoutY(playerOne.getLayoutY() - playerSpeedY);
            }

            if(sPressed.get()){
                playerOne.setLayoutY(playerOne.getLayoutY() + playerSpeedY);
            }

            if(aPressed.get()){
                playerOne.setLayoutX(playerOne.getLayoutX() - playerSpeedX);
            }

            if(dPressed.get()){
                playerOne.setLayoutX(playerOne.getLayoutX() + playerSpeedX);
            }

            if(upPressed.get()) {
                playerTwo.setLayoutY(playerTwo.getLayoutY() - playerSpeedY);
            }

            if(downPressed.get()){
                playerTwo.setLayoutY(playerTwo.getLayoutY() + playerSpeedY);
            }

            if(leftPressed.get()){
                playerTwo.setLayoutX(playerTwo.getLayoutX() - playerSpeedX);
            }

            if(rightPressed.get()){
                playerTwo.setLayoutX(playerTwo.getLayoutX() + playerSpeedX);
            }

        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementSetup();

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    public void movementSetup(){
        sceneGame.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }

            if(e.getCode() == KeyCode.KP_UP) {
                upPressed.set(true);
            }

            if(e.getCode() == KeyCode.KP_LEFT) {
                leftPressed.set(true);
            }

            if(e.getCode() == KeyCode.KP_DOWN) {
                downPressed.set(true);
            }

            if(e.getCode() == KeyCode.KP_RIGHT) {
                rightPressed.set(true);
            }
        });

        sceneGame.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }

            if(e.getCode() == KeyCode.KP_UP) {
                upPressed.set(false);
            }

            if(e.getCode() == KeyCode.KP_LEFT) {
                leftPressed.set(false);
            }

            if(e.getCode() == KeyCode.KP_DOWN) {
                downPressed.set(false);
            }

            if(e.getCode() == KeyCode.KP_RIGHT) {
                rightPressed.set(false);
            }
        });
    }
}

