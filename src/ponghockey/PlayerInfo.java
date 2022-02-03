/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponghockey;

import java.util.ArrayList;

/**
 *
 * @author AlphaTwo
 */
public class PlayerInfo {
    private static PlayerInfo player_info_instance;
    
    private final ArrayList<Integer> levelReached = new ArrayList<>();
    private final ArrayList<Double> timeReached = new ArrayList<>();
    private String name;
    
    public static PlayerInfo getInstance(){
        if(player_info_instance == null){
            player_info_instance = new PlayerInfo();
        }
        return player_info_instance;
    } 
    public void setLevelReached(int level){
        levelReached.add(level);
    }
    public void setTimeReached(double time){
        timeReached.add(time);
    }
    public ArrayList<Integer> getLevel(){
        return levelReached;
    }
    public ArrayList<Double> getTime(){
        return timeReached;
    }
}
