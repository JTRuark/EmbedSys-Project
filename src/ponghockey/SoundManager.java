/*
 * Music listed in this file belongs to HackNet. 
 */
package ponghockey;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Justin Ruark, jtr5391
 */
public class SoundManager {
    private final AudioClip bounce = 
            new AudioClip("file:src/ponghockey/BallBounce.mp3");
    private final AudioClip bottom = 
            new AudioClip("file:src/ponghockey/BallBottom.mp3");
    private final AudioClip buttonClick = 
            new AudioClip("file:src/ponghockey/ButtonClick.mp3");
//    private final Media intro = 
//            new Media("src/ponghockey/IntroMusic.mp3");
//    private final Media trackOne = 
//            new Media("file:src/ponghockey/Track1.mp3");
//    private final Media trackTwo = 
//            new Media("file:src/ponghockey/Track2.mp3");
//    private final Media trackThree = 
//            new Media("file:src/ponghockey/Track3.mp3");
    
//    private final ArrayList<Media> musicTrack = new ArrayList<>();
    //private MediaPlayer introPlay;
    //private MediaPlayer musicPlay;
    private static SoundManager sound_manager_instance;
    
    public static SoundManager getInstance(){
        if(sound_manager_instance == null){
            sound_manager_instance = new SoundManager();
        }
        return sound_manager_instance;
    }
    
    public SoundManager(){
        bounce.setVolume(0.2);
        bottom.setVolume(0.2);
        buttonClick.setVolume(0.2);
        
//        musicTrack.add(trackOne);
//        musicTrack.add(trackTwo);
//        musicTrack.add(trackThree);
    }
    
    public void ballBounceSound(){
        bounce.play();
    }
    public void bottomBorderHit(){
        bottom.play();
    }
    public void buttonClickSound(){
        buttonClick.play();
    }
//    public void playIntroMusic(){
//        MediaPlayer introPlay = new MediaPlayer(intro);
//        introPlay.play();
//    }
//    public void playTracks(){
//        for(Media track : musicTrack){
//            musicPlay = new MediaPlayer(track);
//            musicPlay.play();
//        }
//    }
//    public void stopMusic(){
////        musicPlay.stop();
//    }
}