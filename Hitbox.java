import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Hitbox extends ScrollingActor
{
    private GreenfootImage image;
    /**
     * @param w the width of the image.
     * @param h the height of the image.
     */
    public Hitbox(int w, int h)
    {
        this.image = new GreenfootImage(w,h);
        setImage(this.image);
    }
    public void act()
    {
        
    }
    /**
     * @return If this object is touching an instance of player, returns true,
     * otherwise, returns false.
     */
    public boolean isTouchingPlayer()
    {
        return this.isTouching(Player.class);
    }
}
