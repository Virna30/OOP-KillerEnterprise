import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Bush extends ScrollingActor
{
    private GreenfootImage image;
    private Player pl;
    /**
     * @param p An object of the class Player.
     */
    public Bush(Player p)
    {
        image = new GreenfootImage("bush.png");
        image.scale(190,110);
        setImage(image);
        this.pl = p;
    }
    
    public void act()
    {
        // Add your action code here.
        setTransparency();
    }
    /**
     * Calculates the distance between this object and the object player.
     * @return The calculated distance.
     */
    public double findDistance()
    {
        if(pl != null)
            return Math.sqrt(Math.pow(pl.getX() - this.getX(), 2) + Math.pow(pl.getY() - this.getY(), 2));
        else
            return 0;
    }
    /**
     * Sets the transparency of this object based on the distance of the player.
     */
    private void setTransparency()
    {
        double dist = findDistance();
        if(dist >= 510)
        {
            image.setTransparency(255);
        }
        else
        {
            if(dist <= 0)
            {
                image.setTransparency(0);
            }
            else
            {
                image.setTransparency((int) Math.floor(dist / 2));
            }
        }
    }
}
