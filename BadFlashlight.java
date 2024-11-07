import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class BadFlashlight extends Flashlight
{
    private int uses = 10;
    private Screen name;
    private GreenfootImage image;
    private boolean inFloor;
    public BadFlashlight()
    {
        super();
        this.image = new GreenfootImage("badFlashlight.png");
        setImage(this.image);
        this.name = new Screen(" 10 uses Flashlight",15,Color.BLACK,Color.WHITE);
        this.inFloor = true;
    }
    public void act()
    {
        if(this.inFloor)
        {
            playerNear();
        }
        else
        {
            super.followPlayerRotation();
            use();
        }
    }
    /**
     * sets the state of use of the flashlight
     */
    private void use()
    {
        if(Greenfoot.isKeyDown("space") && this.uses > 0)
        {
            if(!super.isInUse())
            {
                super.setInUse(true);
                this.image.setTransparency(100);
                this.uses--;
                this.name.setTimeLeft(this.uses);
            }
           
        }
        else
        {
            if(super.isInUse())
            {
                super.setInUse(false);
                this.image.setTransparency(0);
            }
        }
    }
    /**
     * if a player is touching this item, adds the attribute name to the world.
     * 
     */
    public void playerNear()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            getWorld().addObject(this.name,getX(),getY()+50);
            pickUp((Player)p);
        }
        else
        {
            getWorld().removeObject(this.name);
        }
    }
    /**
     * starts the next stage of this item.
     */
    public void pickUp(Player p)
    {
        if(Greenfoot.isKeyDown("e"))
        {
            p.grabbingTrash(super.getValue(),super.getWeight());
            getWorld().removeObject(this.name);
            nextStage();
            setInWorld(p);
        }
    }
    /**
     * prepares this flashlight to be used.
     */
    public void nextStage()
    {
        this.image.clear();
        this.image = new GreenfootImage("light.png");
        this.image.scale(98,100);
        this.inFloor = false;
        image.setTransparency(0);
        setImage(this.image);
        ((ScrollMap)getWorld()).setSecondItemImage("badFlashlight");
        
        ((GreenfootImage)this.name.getImage()).clear();
        this.name.setTimeLeft(this.uses);
        getWorld().addObject(this.name,60,180);
    }
    /**
     * changes the position of this object in the screen.
     */
    public void setInWorld(Player p)
    {
        super.givePlayer(p);
        this.setLocation(p.getX(),p.getY()-50); 
    }
}
