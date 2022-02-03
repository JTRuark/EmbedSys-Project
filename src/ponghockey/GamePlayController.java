/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Justin Ruark, jtr5391
 */
public class GamePlayController implements Initializable {
    private final GameManager game = GameManager.getInstance();
    private final SoundManager sounds = SoundManager.getInstance();
    private BooleanProperty leftKeyPress = new SimpleBooleanProperty();
    private BooleanProperty rightKeyPress = new SimpleBooleanProperty();
    private final BooleanBinding keyPress = leftKeyPress.or(rightKeyPress);
   
    private final SceneLoader loader = SceneLoader.getInstance();
    
    private double ballSpeed = game.getBallSpeed();
    private int paddleSpeed = 4;
    private int counter = 0;
    private String name;

    @FXML private Rectangle paddleRect;
    @FXML private Pane playArea;
    @FXML private Circle ball;
    @FXML private Button playAgain;
    @FXML private Button pauseButton;
    @FXML private Button quitButton;
    @FXML private Button continueButton;
    @FXML private Label pauseLabel;
    @FXML private Label pointsLabel;
    @FXML private Label levelLabel;
    @FXML private Label timerLabel;
    
    @FXML private final ArrayList<Rectangle> obstacleList = new ArrayList<Rectangle>();
    
    @FXML
    void start(KeyEvent event) {
        
        paddleRect.setLayoutY(560);
        paddleRect.setLayoutX(641);
    }
    @FXML
    void pauseButtonPressed(MouseEvent e){
        playButtonSound();
        pauseLabel.setText("Game Paused");
        if(playTime.getStatus().equals(Animation.Status.RUNNING)){
            pauseButton.setText("Resume");
            pauseLabel.setVisible(true);
            quitButton.setVisible(true);
            quitButton.setDisable(false);
            
            countDownTimer.pause();
            playTime.pause();
        }
        else if(playTime.getStatus().equals(Animation.Status.PAUSED)){
            pauseButton.setText("Pause");
            pauseLabel.setVisible(false);
            quitButton.setVisible(false);
            quitButton.setDisable(true);
            
            countDownTimer.play();
            playTime.play();
        }
    }
    @FXML
    void quitButtonPressed(MouseEvent e) throws IOException{
        playButtonSound();
        PlayerInfo player = PlayerInfo.getInstance();
        player.setLevelReached(game.getLevel());
        player.setTimeReached(game.getElapsedTime());
        
        
        loader.loadMainMenu(e);
    }
    
    @FXML
    void playAgainButtonPressed(MouseEvent e){
        playButtonSound();
        
        pauseLabel.setVisible(false);
        pauseButton.setDisable(false);
        playAgain.setDisable(true);
        playAgain.setVisible(false);
        quitButton.setDisable(true);
        quitButton.setVisible(false);
        game.resetGameValues();
        
        pointsLabel.setText(String.valueOf(game.getPoints() + 1));
        levelLabel.setText(String.valueOf(game.getLevel()));
        
        counter = 0;
        obstacleList.clear();
        setBallLocation();
        
        playTime.playFromStart();
        countDownTimer.playFromStart();
    }
    @FXML
    void continueButtonPressed(MouseEvent e){
        
        playButtonSound();
        
        Rectangle rect = game.createObstacle();
        obstacleList.add(rect);
        setObstacleLocation();
        
        //}
        
        quitButton.setVisible(false);
        quitButton.setDisable(true);
        continueButton.setVisible(false);
        continueButton.setDisable(true);
        pauseLabel.setVisible(false);
        game.updateGame();
        
        ballSpeed += 2;
        pointsLabel.setText(String.valueOf(game.getPoints() + 1));
        levelLabel.setText(String.valueOf(game.getLevel()));
        
        setBallLocation();
        
        playTime.setRate(game.getBallSpeed());
        playTime.play();
        countDownTimer.playFromStart();
    }
    @FXML
    void setBallLocation(){
        ball.setLayoutX(640);
        ball.setLayoutY(27);
    }
    @FXML
    void setObstacleLocation(){
        Rectangle temp = obstacleList.get(counter);
        
        temp.setLayoutX(temp.getX());
        temp.setLayoutY(temp.getY());

        playArea.getChildren().add(temp);

        counter += 1;
    }
    void checkGameTime(){
        if(game.getStartTime() == -1){
            playTime.pause();
            countDownTimer.pause();
            //display label
            pauseLabel.setText("Level Completed!");
            pauseLabel.setVisible(true);
            //display button to continue or quit
            continueButton.setVisible(true);
            continueButton.setDisable(false);
            quitButton.setVisible(true);
            quitButton.setDisable(false);
        }
    }
    void updateGameValues(){
        
        if(game.getPoints() > 0){
            game.updatePoints();
            pointsLabel.setText(String.valueOf(game.getPoints() + 1));
        }
        else if(game.getPoints() == 0){
            playTime.pause();
            pauseButton.setDisable(true);
            pointsLabel.setText("0");
            pauseLabel.setText("GAME OVER");
            pauseLabel.setVisible(true);
            quitButton.setVisible(true);
            quitButton.setDisable(false);
            playAgain.setDisable(false);
            playAgain.setVisible(true);
        }
    }
    void playButtonSound(){
        sounds.buttonClickSound();
    }
    
    AnimationTimer timer = new AnimationTimer(){
        @Override
        public void handle(long timestamp){
            if(leftKeyPress.get()){
                paddleRect.setLayoutX(paddleRect.getLayoutX() - paddleSpeed);
            }
            if(rightKeyPress.get()){
                paddleRect.setLayoutX(paddleRect.getLayoutX() + paddleSpeed);
            }
        }};
    Timeline countDownTimer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            timerLabel.setText(String.valueOf(game.decrementTimer()));
        }
        
    }));
    
    Timeline playTime = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>(){
        double dX = ballSpeed;
        double dY = ballSpeed;
        
        @Override
        public void handle(ActionEvent event){
            checkGameTime();
            ball.setLayoutX(ball.getLayoutX() + dX);
            ball.setLayoutY(ball.getLayoutY() + dY);

            Bounds borders = playArea.getLayoutBounds();
            Bounds paddleBorder = paddleRect.getBoundsInParent();
            
            Bounds ballBound = ball.getBoundsInParent();
    
            boolean rightBorder = ball.getLayoutX() >= (borders.getMaxX() - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (borders.getMinX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= (borders.getMaxY() - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (borders.getMinY() + ball.getRadius());
            
            if(rightBorder || leftBorder ){
                sounds.ballBounceSound();
                dX *= -1;
            }
            if(topBorder){
                sounds.ballBounceSound();
                dY *= -1;
            }
            if(bottomBorder){
                sounds.bottomBorderHit();
                updateGameValues();
                dY *= -1;
            }
            if(ballBound.intersects(paddleBorder)){
                sounds.ballBounceSound();
                dY *= -1;
            }
            if(!obstacleList.isEmpty()){
                obstacleList.stream().filter((rect) -> (ballBound.intersects(rect.getBoundsInParent()))).map((_item) -> {
                    sounds.ballBounceSound();
                    return _item;
                }).forEachOrdered((_item) -> {
                    dY *= -1;
                });
            }
            
        }  
    }));
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMovement();
        setBallLocation();
        
        keyPress.addListener(((ob, oldVal, newVal) -> {
            if(!oldVal){
                timer.start();
            } else {
                timer.stop();
            }
        }));
        
        pointsLabel.setText(String.valueOf(game.getPoints() + 1));
        levelLabel.setText(String.valueOf(game.getLevel()));
        timerLabel.setText(String.valueOf(game.getStartTime()));
        
        countDownTimer.setCycleCount(Animation.INDEFINITE);
        countDownTimer.play();
        playTime.setCycleCount(Animation.INDEFINITE);
        playTime.play();
        
    } 
    public void initMovement(){
        
        playArea.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A) {
                leftKeyPress.set(true);
            }
            if(e.getCode() == KeyCode.D) {
                rightKeyPress.set(true);
            }
        });
        playArea.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.A) {
                leftKeyPress.set(false);
            }
            if(e.getCode() == KeyCode.D) {
                rightKeyPress.set(false);
            }
        });
    }
}
