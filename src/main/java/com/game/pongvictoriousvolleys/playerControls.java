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
import java.io.*;


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

    private double playerOneUpSpeedY = 7;
    private double playerOneDownSpeedY = 7;
    private double playerOneForwardSpeedX = 3;
    private double playerOneBackwardSpeedX = 3;

    private double playerTwoUpSpeedY = 7;
    private double playerTwoDownSpeedY = 7;
    private double playerTwoForwardSpeedX = 3;
    private double playerTwoBackwardSpeedX = 3;

    private int winStreakOne = 0;
    private int winStreakTwo = 0;

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
    private Rectangle ice;
    @FXML
    private Label streakOne;
    @FXML
    private Label streakTwo;

    @FXML
    private AnchorPane sceneGame;

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

            ice.setLayoutX(-50000);
            iceCube();
        }
    };

    double circleMovementX = .5;
    double circleMovementY = .5;
    int playerOneScore = 4;
    String stringOneScore = "";
    int playerTwoScore = 4;
    String stringTwoScore = "";
    String playerOneWins = "Player One Wins";
    String playerTwoWins = "Player Two Wins";
    String showStreakOne = "";
    String showStreakTwo = "";



    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {



        @Override
        public void handle(ActionEvent actionEvent) {
            gameBall.setLayoutX(gameBall.getLayoutX() + circleMovementX);
            gameBall.setLayoutY(gameBall.getLayoutY() + circleMovementY);

            Bounds bounds = sceneGame.getBoundsInLocal();
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

            //Change values, values should be when the ball is completely out the screen on the right end
            if(gameBall.getLayoutX() > 2000){
                playerOneScore++;
                stringOneScore = Integer.toString(playerOneScore);
                scoreOne.setText(stringOneScore);

                gameBall.setLayoutX(688);
                gameBall.setLayoutY(324);
                circleMovementX =-2.5;
                playerTwoUpSpeedY = 7;
                playerTwoDownSpeedY = 7;
                playerTwoForwardSpeedX = 3;
                playerTwoBackwardSpeedX = 3;
                playerOneUpSpeedY = 7;
                playerOneDownSpeedY = 7;
                playerOneForwardSpeedX = 3;
                playerOneBackwardSpeedX = 3;
            }

            //Change values, values should be when the ball is completely out the screen on the left end
            if(gameBall.getLayoutX() < -300){
                playerTwoScore++;
                stringTwoScore = Integer.toString(playerTwoScore);
                scoreTwo.setText(stringTwoScore);

                gameBall.setLayoutX(688);
                gameBall.setLayoutY(324);
                circleMovementX =2.5;
                playerTwoUpSpeedY = 7;
                playerTwoDownSpeedY = 7;
                playerTwoForwardSpeedX = 3;
                playerTwoBackwardSpeedX = 3;
                playerOneUpSpeedY = 7;
                playerOneDownSpeedY = 7;
                playerOneForwardSpeedX = 3;
                playerOneBackwardSpeedX = 3;
            }

            if (playerOneScore == 10) {
                winMessage.setText(playerOneWins);
                winMessage.setVisible(true);
                timeline.stop();
                playAgain.setVisible(true);
                timeline.stop();
                winStreakOne++;
                winStreakTwo = 0;
                showStreakOne = Integer.toString(winStreakOne);
                showStreakTwo = Integer.toString(winStreakTwo);
                streakOne.setVisible(true);
                streakTwo.setVisible(true);
                streakOne.setText("Win Streak: " + showStreakOne);
                streakTwo.setText("Win Streak: " + showStreakTwo);

            }

            if (playerTwoScore == 10) {
                winMessage.setText(playerTwoWins);
                winMessage.setVisible(true);
                playAgain.setVisible(true);
                timeline.stop();
                winStreakTwo++;
                winStreakOne = 0;
                showStreakOne = Integer.toString(winStreakOne);
                showStreakTwo = Integer.toString(winStreakTwo);
                streakOne.setVisible(true);
                streakTwo.setVisible(true);
                streakOne.setText("Win Streak: " + showStreakOne);
                streakTwo.setText("Win Streak: " + showStreakTwo);
            }



        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementSetup();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        winStreakOne = readIntegerOne();
        winStreakTwo = readIntegerTwo();

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
            playerOneForwardSpeedX =3;
        }

        if(playerTwo.getBoundsInParent().intersects(verticalBorderOne.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderTwo.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderThree.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderFour.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderFive.getBoundsInParent())||playerTwo.getBoundsInParent().intersects(verticalBorderSix.getBoundsInParent())
                ||playerTwo.getBoundsInParent().intersects(verticalBorderSeven.getBoundsInParent())){
            playerTwoBackwardSpeedX = 0;
        }
        else{
            playerTwoBackwardSpeedX =3;
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

        playAgain.setVisible(false);
        winMessage.setVisible(false);
        playerOneScore = 0;
        playerTwoScore = 0;
        timeline.play();
        scoreOne.setText("0");
        scoreTwo.setText("0");

        streakOne.setVisible(false);
        streakTwo.setVisible(false);

        winIntegerOne(winStreakOne);
        winIntegerTwo(winStreakTwo);
    }

    public void backButton(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("secondInterface.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void iceCube () {

        if((playerTwoScore == 5 || playerOneScore == 5)) {
            if (playerTwoScore < playerOneScore) {
                ice.setLayoutX(737);
                ice.setLayoutY(307);
                ice.setVisible(true);
            }
            else if (playerTwoScore > playerOneScore) {
                ice.setLayoutX(545);
                ice.setLayoutY(382);
                ice.setVisible(true);
            }
        }

        if(playerOne.getBoundsInParent().intersects(ice.getBoundsInParent())) {
            playerTwoBackwardSpeedX = 0;
            playerTwoForwardSpeedX = 0;
            playerTwoUpSpeedY = 0;
            playerTwoDownSpeedY = 0;
            ice.setVisible(false);
            ice.setLayoutX(5000);
        }

        if (playerTwo.getBoundsInParent().intersects(ice.getBoundsInParent())) {
            playerOneBackwardSpeedX = 0;
            playerOneForwardSpeedX = 0;
            playerOneUpSpeedY = 0;
            playerOneDownSpeedY = 0;
            ice.setVisible(false);
            ice.setLayoutX(5000);
        }
    }


    private static final String saveValueOne = "keep-win-one-integer.txt";
    private static final String saveValueTwo = "keep-win-two-integer.txt";

    public static void winIntegerOne(int winsOne) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(saveValueOne))) {
            writer.println(winsOne);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readIntegerOne() {
        int winsOne = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(saveValueOne))) {
            String line = reader.readLine();
            if (line != null) {
                winsOne = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return winsOne;
    }

    public static void winIntegerTwo(int winsTwo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(saveValueTwo))) {
            writer.println(winsTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readIntegerTwo() {
        int winsTwo = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(saveValueTwo))) {
            String line = reader.readLine();
            if (line != null) {
                winsTwo = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return winsTwo;
    }

}

