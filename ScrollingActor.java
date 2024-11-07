import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class ScrollingActor extends Actor
{
    public void act()
    {
        // Add your action code here.
    }
    /**
     * Moves an instance of ScrollingActor in a world.
     */
    public void setAbsoluteLocation(int dx,int dy)
    {
        setLocation(getX()+dx,getY()+dy);
    }
}
