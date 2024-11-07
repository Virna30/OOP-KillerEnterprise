import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Virna Lissi Martínez Sánchez
 * @version FINAL
 */
public class Timer extends Actor
{
    private int hours;
    private int minutes;
    private int seconds;
    /**
     * @param hours The hours given to play.
     * @param minutes The minutes given to play.
     * @param seconds The seconds given to play.
     */
    public Timer(int hours, int minutes, int seconds)
    {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }
    
    public void act() {
        update(1);
        display();
    }
    /**
     * updates the value of the three attributes of this object.
     */
    private void update(int timeFactor) {
        if (seconds > timeFactor) {
            seconds -= timeFactor;
        } else {
            int leftoverSeconds = timeFactor - seconds;
            if (minutes > 0) {
                minutes -= (1 + leftoverSeconds / 60);
                seconds = 60 - (leftoverSeconds % 60);
            } else {
                if (hours > 0) {
                    hours--;
                    minutes = 59 - (leftoverSeconds / 60);
                    seconds = 60 - (leftoverSeconds % 60);
                } else {
                    Greenfoot.setWorld(new GameDone("time"));
                }
            }
        }
    }
    /**
     * shows and updates the time left on this object.
     */
    private void display() {
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        Color textColor = Color.WHITE;
        
        if (hours == 0 && minutes <= 15) {
            textColor = Color.RED;
        }
    
        setImage(new GreenfootImage(time, 24, textColor, Color.BLACK));
    }
    /**
     * substracts an amount of seconds to the seconds of this timer.
     */
    public void subtractTime(int sec)
    {
        this.seconds -= sec;   
    }
}
