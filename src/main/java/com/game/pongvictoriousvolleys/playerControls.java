package com.game.pongvictoriousvolleys;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class playerControls implements Initializable {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanProperty tPressed = new SimpleBooleanProperty();
    private BooleanProperty fPressed = new SimpleBooleanProperty();
    private BooleanProperty gPressed = new SimpleBooleanProperty();
    private BooleanProperty hPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(tPressed).or(fPressed).or(gPressed).or(hPressed);

    private int playerOneUpSpeedY = 5;
    private int playerOneDownSpeedY = 5;
    private int playerOneForwardSpeedX = 2;
    private int playerOneBackwardSpeedX = 2;

    private int playerTwoUpSpeedY = 5;
    private int playerTwoDownSpeedY = 5;
    private int playerTwoForwardSpeedX = 2;
    private int playerTwoBackwardSpeedX = 2;

    @FXML
    private Rectangle playerOne;
    @FXML
    private Rectangle playerTwo;

    @FXML
    private Circle gameBall;

    @FXML
    private Rectangle verticalBorderOne;
    @FXML
    private Rectangle verticalBorderTwo;
    @FXML
    private Rectangle verticalBorderThree;
    @FXML
    private Rectangle verticalBorderFour;
    @FXML
    private Rectangle verticalBorderFive;
    @FXML
    private Rectangle verticalBorderSix;
    @FXML
    private Rectangle verticalBorderSeven;

    @FXML
    private Label scoreOne;
    @FXML
    private Label scoreTwo;
    @FXML
    private Label winMessage;
    @FXML
    private Button playAgain;

    @FXML
    private AnchorPane sceneGame;

    //Maybe Need
    @FXML
    void start(ActionEvent event) {

    }

    AnimationTimer timerOne = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if(wPressed.get()) {
                playerOne.setLayoutY(playerOne.getLayoutY() - playerOneUpSpeedY);
            }
            if(sPressed.get()){
                playerOne.setLayoutY(playerOne.getLayoutY() + playerOneDownSpeedY);
            }
            if(aPressed.get()){
                playerOne.setLayoutX(playerOne.getLayoutX() - playerOneBackwardSpeedX);
            }
            if(dPressed.get()){
                playerOne.setLayoutX(playerOne.getLayoutX() + playerOneForwardSpeedX);
            }

            if(tPressed.get()) {
                playerTwo.setLayoutY(playerTwo.getLayoutY() - playerTwoUpSpeedY);
            }
            if(gPressed.get()){
                playerTwo.setLayoutY(playerTwo.getLayoutY() + playerTwoDownSpeedY);
            }
            if(fPressed.get()){
                playerTwo.setLayoutX(playerTwo.getLayoutX() - playerTwoBackwardSpeedX);
            }
            if(hPressed.get()){
                playerTwo.setLayoutX(playerTwo.getLayoutX() + playerTwoForwardSpeedX);
            }

            restrictedForwardMovement();
            gameBorder();
        }
    };

    double circleMovementX = 5.5;
    double circleMovementY = 5.5;
    int playerOneScore = 0;
    String stringOneScore = "";
    int playerTwoScore = 0;
    String stringTwoScore = "";
    String playerOneWins = "Player One Wins";
    String playerTwoWins = "Player Two Wins";
    int totalPlayerWins = 0;

    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {



        @Override
        public void handle(ActionEvent actionEvent) {
            gameBall.setLayoutX(gameBall.getLayoutX() + circleMovementX);
            gameBall.setLayoutY(gameBall.getLayoutY() + circleMovementY);

            Bounds bounds = sceneGame.getBoundsInLocal();
            boolean rightCircleBorder = gameBall.getLayoutX() >= (bounds.getMaxX() - gameBall.getRadius());
            boolean leftCircleBorder = gameBall.getLayoutX() <= (bounds.getMinX() + gameBall.getRadius());
            boolean bottomCircleBorder = gameBall.getLayoutY() >= (bounds.getMaxY() - gameBall.getRadius());
            boolean topCircleBorder = gameBall.getLayoutY() <= (bounds.getMinY() + gameBall.getRadius());




            if (bottomCircleBorder || topCircleBorder) {
                circleMovementY = -1 * circleMovementY;
            }

            if(playerOne.getBoundsInParent().intersects(gameBall.getBoundsInParent())){
                circleMovementX = -1.5 * circleMovementX;
            }

            if(playerTwo.getBoundsInParent().intersects(gameBall.getBoundsInParent())){
                circleMovementX = -1.5 * circleMovementX;
            }

            if(rightCircleBorder){
                playerOneScore++;
                stringOneScore = Integer.toString(playerOneScore);
                scoreOne.setText(stringOneScore);

                gameBall.setLayoutX(688);
                gameBall.setLayoutY(324);
                circleMovementX = -5.5;
            }

            if(leftCircleBorder){
                playerTwoScore++;
                stringTwoScore = Integer.toString(playerTwoScore);
                scoreTwo.setText(stringTwoScore);

                gameBall.setLayoutX(688);
                gameBall.setLayoutY(324);
                circleMovementX = 5.5;
            }

            if (playerOneScore == 10) {
                winMessage.setText(playerOneWins);
                winMessage.setVisible(true);
                timeline.stop();
                playAgain.setVisible(true);
                timeline.stop();
                timerOne.stop();
                System.out.println(totalPlayerWins);
            }

            if (playerTwoScore == 10) {
                winMessage.setText(playerTwoWins);
                winMessage.setVisible(true);
                playAgain.setVisible(true);
                timeline.stop();
                timerOne.stop();
                System.out.println(totalPlayerWins);
            }
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementSetup();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timerOne.start();
            } else {
                timerOne.stop();
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

            if(e.getCode() == KeyCode.T) {
                tPressed.set(true);
            }

            if(e.getCode() == KeyCode.F) {
                fPressed.set(true);
            }

            if(e.getCode() == KeyCode.G) {
                gPressed.set(true);
            }

            if(e.getCode() == KeyCode.H) {
                hPressed.set(true);
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

            if(e.getCode() == KeyCode.T) {
                tPressed.set(false);
            }

            if(e.getCode() == KeyCode.F) {
                fPressed.set(false);
            }

            if(e.getCode() == KeyCode.G) {
                gPressed.set(false);
            }

            if(e.getCode() == KeyCode.H) {
                hPressed.set(false);
            }
        });
    }

    public void restrictedForwardMovement () {
        if(playerOne.getBoundsInParent().intersects(verticalBorderOne.getBoundsInParent())||playerOne.getBoundsInParent().intersects(verticalBorderTwo.getBoundsInParent())
                ||playerOne.getBoundsInParent().intersects(verticalBorderThree.getBoundsInParent())||playerOne.getBoundsInParent().intersects(verticalBorderFour.getBoundsInParent())
                ||playerOne.getBoundsInParent().intersects(verticalBorderFive.getBoundsInParent())||playerOne.getBoundsInParent().intersects(verticalBorderSix.getBoundsInParent())
                ||playerOne.getBoundsInParent().intersects(verticalBorderSeven.getBoundsInParent())){
            playerOneForwardSpeedX = 0;
        }
        else{
            playerOneForwardSpeedX = 2;
        }

        if(playerTwo.getBoundsInParent().intersects(verticalBorderOne.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderTwo.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderThree.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderFour.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderFive.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderSix.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderSeven.getBoundsInParent())){
            playerTwoBackwardSpeedX = 0;
        }
        else{
            playerTwoBackwardSpeedX = 2;
        }
    }

    public void gameBorder () {

        double borderLeft = 0;
        double borderRight = sceneGame.getWidth() - playerOne.getWidth();
        double borderBottom = sceneGame.getHeight() - playerOne.getHeight();
        double borderTop = 0;

        double borderRight1 = sceneGame.getWidth() - playerTwo.getWidth();
        double borderBottom1 = sceneGame.getHeight() - playerTwo.getHeight();
        double borderTop1 = 0;

        if(playerOne.getLayoutX() <= borderLeft){
            playerOne.setLayoutX(borderLeft);
        }
        if(playerOne.getLayoutY() <= borderTop){
            playerOne.setLayoutY(borderTop);
        }
        if(playerOne.getLayoutY() >= borderBottom){
            playerOne.setLayoutY(borderBottom);
        }

        if(playerTwo.getLayoutX() >= borderRight1){
            playerTwo.setLayoutX(borderRight);
        }
        if(playerTwo.getLayoutY() <= borderTop1){
            playerTwo.setLayoutY(borderTop);
        }
        if(playerTwo.getLayoutY() >= borderBottom1) {
            playerTwo.setLayoutY(borderBottom);
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void playAgainButton(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("gameWork.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

