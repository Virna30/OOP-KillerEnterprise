import greenfoot.*;

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Flashlight extends Item
{
    private Player p;
    protected GreenfootImage image;
    private boolean inUse;
    public Flashlight()
    {
        super("flashlight");
        image = new GreenfootImage("light.png");
        image.setTransparency(0);
        image.scale(128,130);
        setImage(image);
        this.inUse = false;
    }
    /**
     * links this item to a player.
     */
    public void givePlayer(Player p)
    {
        this.p = p;
    }
    public void act()
    {
        followPlayerRotation();
        use();
    }
    /**
     * sets the state of use of the flashlight
     */
    private void use()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            if(!inUse)
            {
                inUse = true;
                this.image.setTransparency(128);
            }
        }
        else
        {
            if(inUse)
            {
                inUse = false;
                this.image.setTransparency(0);
            }
        }
    }
    /**
     * moves the image of the flashlight with the player and also changes the 
     * orientation of the image.
     */
    protected void followPlayerRotation()
    {
        int rot = p.getRotation();
        setRotation(rot);
        if(rot == 0)
        {
            setLocation(p.getX(),p.getY()-80);
        }
        else
        {
            if(rot == 90)
            {
                setLocation(p.getX()+80,p.getY());
            }
            else
            {
                if(rot == 180)
                {
                    setLocation(p.getX(),p.getY()+80);
                }
                else
                {
                    setLocation(p.getX()-80,p.getY());   
                }
            }
        }
    }
    @Override
    public int getValue()
    {
        return super.getValue();
    }
    @Override
    public int getWeight()
    {
        return super.getWeight();
    }
    /**
     * @return the value of the attribute inUse.
     */
    public boolean isInUse()
    {
        return this.inUse;
    }
    /**
     * Sets the value of the attribute inUse to a given state.
     * @param state The new value of inUse.
     */
    public void setInUse(boolean state)
    {
        this.inUse = state;
    }
}
