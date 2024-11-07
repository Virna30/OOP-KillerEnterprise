import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spike here.
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Spike extends Enemy
{
    private int speed = 2;
    public Spike()
    {
        super("Spike");
    }
    /**
     * Seeks to kill the player but if the object reaches a border, then is removed
     * from the world.
     */
    public void act()
    {
        super.killPlayer();
        move(speed);
        if(this.isAtEdge())
        {
            getWorld().removeObject(this);
        }
        // Add your action code here.
    }
}
