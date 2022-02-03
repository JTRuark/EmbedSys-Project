/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Justin Ruark, jtr5391
 */
public class SceneLoader implements Initializable{
    private static SceneLoader scene_loader_instance;
    
    private Parent _root;
    private Stage _stage;
    private Scene _scene;
    private FXMLLoader loader;
    
    public static SceneLoader getInstance(){
        if(scene_loader_instance == null){
            scene_loader_instance = new SceneLoader();
        }
        return scene_loader_instance;
    }
    public void loadMainMenu(Stage initStage) throws IOException{
        _stage = initStage;
        Parent root = load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        _stage.setTitle("Pong Hockey!");
        _stage.setResizable(false);
        _stage.setScene(scene);
        _stage.show();
    }
    
    public void loadMainMenu(MouseEvent e) throws IOException{
        loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        _root = loader.load();
        _stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setTitle("Pong Hockey!");
        _stage.setScene(_scene);
        _stage.show();
    }
    public void loadGame(MouseEvent e) throws IOException{
        PlayerInfo player = PlayerInfo.getInstance();
        loader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
        _root = loader.load();
        _stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setTitle("Pong Hockey!");
        _stage.setScene(_scene);
        _stage.show();
    }
    public Stage getScene(){
        return _stage;
    }
    public void loadHighScores(MouseEvent e) throws IOException{
        loader = new FXMLLoader(getClass().getResource("HighScores.fxml"));
        _root = loader.load();
        _stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setTitle("High Scores!");
        _stage.setScene(_scene);
        _stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
