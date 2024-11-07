import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class PlasticBoots extends Item
{
    private Player p;
    private Screen info;
    
    private int uses = 5;
    private boolean inLava;
    private boolean inFloor;
    /**
     * @param p The player this object will be linked to.
     */
    public PlasticBoots(Player p)
    {
        super("plasticBoots");
        this.p = p;
        this.info = new Screen("Plastic Boots",15,Color.BLACK,Color.WHITE);
        this.inFloor = true;
        this.inLava = false;
    }
    public void act()
    {
          if(this.inFloor)
          {
              playerCol();
          }
          else
          {
              use();
          }
    }
    /**
     * sets the state of uses of this object.
     */
    private void use()
    {
        if(this.p.hasPlasticBoots() && this.p.isInLava())
        {
            if(!this.inLava)
            {
                this.inLava = true;
                setUses();
            }
        }
        else
        {
            checkUses();
            if(this.inLava)
                {
                    this.inLava = false;
                }
        }
    }
    /**
     * if uses is equal or less than 0, "removes" the link between this object
     * and player.
     */
    private void checkUses()
    {
        if(this.uses <= 0)
        {
            this.p.setPlasticBoots(false);
        }
    }
    /**
     * if a player is touching this item, adds the attribute name to the world.
     * 
     */
    private void playerCol()
    {
        if(isTouching(Player.class))
        {
            getWorld().addObject(this.info,getX(),getY()+50);
            pickUp();
        }
        else
        {
            getWorld().removeObject(this.info);
        }
    }
    /**
     * starts the next stage of this item.
     */
    private void pickUp()
    {
        if(Greenfoot.isKeyDown("e"))
        {
            getWorld().removeObject(this.info);
            ((ScrollMap)getWorld()).setSecondItemImage(super.getName());
            p.setPlasticBoots();
            this.inFloor = false;
            nextStage();
        }
    }
    /**
     * prepares this boots to be used.
     */
    private void nextStage()
    {
        ((GreenfootImage)this.info.getImage()).clear();
        this.info.setTimeLeft(this.uses);
        getWorld().addObject(this.info,60,180);
        GreenfootImage img = getImage();
        img.clear();
        setImage(img);
    }
    /**
     * @return The amount of uses left.
     */
    public int getUses()
    {
        return this.uses;
    }
    /**
     * reduces the amount of uses by one.
     */
    public void setUses()
    {
        this.uses--;
        this.info.setTimeLeft(this.uses);
    }
}
