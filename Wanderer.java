
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wanderer here.
 * 
 * @author Josué Alejandro Cedillo Gámez 
 * @version FINAL
 */
public class Wanderer extends Enemy
{
    private int distance;
    private int speed = 4;
    private int side;
    
    private Timer countdown;
    GreenfootSound sound;
    /**
     * @param aleT An object of Aleatorio
     * @param w The instance of World this object will be added to.
     * @param countdown the instance of Timer the Wanderer will attack.
     */
    public Wanderer(Aleatorio aleT, World w,Timer countdown){
        super("Wanderer");
        this.side = aleT.getWandererView();
        this.distance = 360;
        this.countdown = countdown;
        this.sound = new GreenfootSound("WandererSound1.wav");
        switch (this.side){
            case 1:
                w.addObject(this, -50, w.getHeight() / 2);
                break;
            case 2:
                w.addObject(this,w.getWidth() / 2, 0);
                setRotation(90);
                break;
            case 3:
                w.addObject(this,w.getWidth() + 50,w.getHeight() / 2);
                setRotation(180);
                break;
            case 4:
                w.addObject(this, w.getWidth() / 2, w.getHeight() + 50);
                setRotation(270);
                break;
        }
    }
    public void act()
    {
        // Add your action code here.
        if(!sound.isPlaying())
        {
            sound.play();
        }
        killPlayer();
        move(this.speed);
        if (die() || miss())
            getWorld().removeObject(this);
    }
    /**
     * determines if this object if removed from the world.
     * @return True if this object is touching the light of a flashlight,
     * otherwise, returns false.
     */
    public boolean die(){
        Actor flash = getOneIntersectingObject(Flashlight.class);
        
        if (flash != null){
            GreenfootImage light = flash.getImage();
            if (light.getTransparency() != 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    /**
     * @return True if this object is in a world border, otherwise, returns false.
     */
    public boolean miss(){
        if (this.getX() <= -100 || this.getX() >= 650 || this.getY() <= -100 || this.getY() >= 500)
            return true;
        else
            return false;
    }
    /**
     * takes away time from the countdown attribute if this object is touching
     * a player.
     */
    @Override
    public void killPlayer()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            this.countdown.subtractTime(20);
        }
    }
}
