import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class BadBush extends Enemy
{
    private GreenfootImage image;
    private Hitbox hitbox;
    public BadBush()
    {
        super("Bad Bush");
        this.image = new GreenfootImage("bush.png");
        this.image.scale(190,110);
        setImage(image);
        
        this.hitbox = new Hitbox(60,60);
    }
    public void act()
    {
        // Add your action code here.
        killPlayer();
    }
    /**
     * If the attribute Hitbox is touching a player, call the method of the super class
     * killPlayer.
     */
    @Override
    public void killPlayer()
    {
        if(this.hitbox.isTouchingPlayer())
        {
            super.killPlayer();
        }
    }
    /**
     * links an object of the class Hitbox to this object.
     */
    public void setHitbox()
    {
        getWorld().addObject(this.hitbox,getX(),getY());
    }
}
