import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
/**
 * 
 * @author Virna Lissi Martínez Sánchez
 * @version FINAL
 */

public class Music extends Actor
{   
    private GreenfootSound music;
    private boolean manuallyStopped = false;
    
    public Music() {
        music = new GreenfootSound("InitialSoundtrack.mp3");
    }
    /**
     * Start to play the music.
     */
    public void startMusic() {
        music.play();
    }
    /**
     * manually stops the music.
     */
    public void stopMusic() {
        music.stop();
        manuallyStopped = true;
    }
    /**
     *  @return if the music is playing returs true, otherwise, returns false.
     */
    public boolean isMusicPlaying() {
        return music.isPlaying();
    }
    /**
     * if the music is not playing and it has not been stopped manually,
     * replays the music.
     */
    public void restartMusic() {
        if (!isMusicPlaying() && !manuallyStopped) {
            music.play();
        }
    }
    public void resetManuallyStopped() {
        manuallyStopped = false;
    }
    
    public void act() {
        restartMusic();
    }
}
