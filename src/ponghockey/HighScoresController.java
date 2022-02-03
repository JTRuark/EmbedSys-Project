/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Justin Ruark, jtr5391
 */
public class HighScoresController implements Initializable {
    private final SceneLoader loader = SceneLoader.getInstance();
    private final SoundManager sound = SoundManager.getInstance();
    private final PlayerInfo player = PlayerInfo.getInstance();
    
    @FXML 
    private GridPane scoreDisplay;
    
    @FXML 
    void mainMenuButtonPressed(MouseEvent e) throws IOException{
        sound.buttonClickSound();
        loader.loadMainMenu(e);
    }
    @FXML
    void loadHighScores(){
        int count = 0;
        
        for(Integer level : player.getLevel()){
            for(double time : player.getTime()){
                Label playerLevel = new Label();
                Label totalTime = new Label();
                
                playerLevel.setAlignment(Pos.CENTER);
                playerLevel.setFont(new Font("Consolas", 28));
                playerLevel.setPrefSize(300, 140);
                playerLevel.setText("Level: " + level);
        
                totalTime.setAlignment(Pos.CENTER);
                totalTime.setFont(new Font("Consolas", 28));
                totalTime.setPrefSize(300, 140);
                totalTime.setText("  Total Time: " + String.valueOf(time));
                
                scoreDisplay.add(playerLevel, 0, count);
                scoreDisplay.add(totalTime, 1, count);
                
                count++;
            }
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadHighScores();
    }    
    
}
