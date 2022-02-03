/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Justin Ruark, jtr5391
 */
public class GameManager {
    private static GameManager game_manager_instance;
    
    private int startTime = 10;
    private final double ballSpeed = 2f;
    private int numObstacles = 1;
    private int level = 1;
    private int points = 2;
    private double timeElapsed = 0;
    private final Random random = new Random();
    
    public static GameManager getInstance(){
        if(game_manager_instance == null){
            game_manager_instance = new GameManager();
        }
        return game_manager_instance;
    }
    
    public GameManager(){}
    
    public void updateGame(){
        startTime = 10;
        //ballSpeed += 0.2f;
        
        if(level % 2 == 0){
            numObstacles += 1;
        }
        timeElapsed += startTime;
        level += 1;
    }
    public void updatePoints(){
        points--;
    }
    public void updateLevel(){
        updateGame();
    }
    public void resetGameValues(){
        startTime = 10;
        points = 2;
        level = 1;
    }
    public double getBallSpeed(){
        return ballSpeed;
    }
    public int getObstacles(){
        return numObstacles;
    }
    public int getLevel(){
        return level;
    }
    public double getElapsedTime(){
        return timeElapsed;
    }
    public int getPoints(){
        return points;
    }
    public int getStartTime(){
        return startTime;
    }
    public int decrementTimer(){
        return startTime--;
    }
    public Rectangle createObstacle(){
        Color obsCol = Color.web("#bfbf42");
            Rectangle obstacle = new Rectangle();
            obstacle.setWidth(randSize());
            obstacle.setHeight(1);
            obstacle.setFill(obsCol);
            obstacle.setStroke(obsCol);
            obstacle.setX(xRandomCoord());
            obstacle.setY(yRandomCoord());
          
            return obstacle;
    }
    private double xRandomCoord(){
        return random.nextInt(920);
        
    }
    private double yRandomCoord(){
        return ThreadLocalRandom.current().nextInt(0, 210);
    }
    private int randSize(){
        return ThreadLocalRandom.current().nextInt(200, 400);
    }
}
