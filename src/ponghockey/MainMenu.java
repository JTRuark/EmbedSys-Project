/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Justin Ruark, jtr5391
 */
public class MainMenu implements Initializable{
    private final SceneLoader load = SceneLoader.getInstance();
    private final SoundManager sound = new SoundManager();
    
    @FXML
    public void showMainMenu(Stage stage) throws IOException{
        //sound.playIntroMusic();
        load.loadMainMenu(stage);
        
    }
    @FXML
    public void startNewGame(MouseEvent e) throws IOException{
        System.out.println("Start Button Clicked");
       sound.buttonClickSound();
        load.loadGame(e);
    }
    @FXML
    public void showHighScore(MouseEvent e) throws IOException{
        System.out.println("High Score Button Clicked");
        sound.buttonClickSound();
        load.loadHighScores(e);
    }
    @FXML
    public void quitApplication(MouseEvent e){
         sound.buttonClickSound();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
