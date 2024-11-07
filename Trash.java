import greenfoot.*;
/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Trash extends Item 
{
    private GreenfootImage image;
    private GreenfootSound sound;
    
    private Screen info;
    private int type;
    private static int trashPickedUp = 0;
    /**
     * Constructor for objects of class Trash
     * @param imageName The name of the image file.
     * @param value The value this trash is worth.
     * @param weight The weight of this item.
     * @param type The specialty of this trash.
     * @param name The name of this trash.
     */
    public Trash(String imageName,int value,int weight,int type,String name)
    {
        super(value,weight,name);
        this.type = type;
        image = new GreenfootImage(imageName);
        setImage(image);
        info = new Screen(this);
    }
    public void act()
    {
        collisionPlayer();
    }
    /**
    * if a player presses the key "e",the trash does his special effect, 
    * adds the value and weight to the amount in
    * player, plays a sound and removes this object from the world.
    */
    public void pickUp(Player p)
    {
        if(Greenfoot.isKeyDown("e"))
        {
            doSpecialty(p);
            p.grabbingTrash(super.getValue(),super.getWeight());
            info.setOnScreen();
            getWorld().removeObject(info);
            doSound();
            addMoreTrash();
            
            getWorld().removeObject(this);
        }
    }
    /**
     * if an instace of player is touching this object
     * prepares this object to be picked up.
     */
    public void collisionPlayer()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            getWorld().addObject(info,getX(),getY()+30);
            info.setOnScreen();
            pickUp((Player)p);
        }
        else
        {
            getWorld().removeObject(info);
            info.setOnScreen();
            if(info.isOnScreen())
            {
                getWorld().removeObject(info);
                info.setOnScreen();
            }
        }
    }
    /**
     * does the special effect of this trash.
     */
    private void doSpecialty(Player p)
    {
        switch(this.type)
        {
            case 1:
                //nada que hacer.
            break;
            case 2:
                if(TrashType.breakChances())
                {
                    super.setValue(0);
                }
            break;
            case 3:
                p.switchKeys(1000);
            break;
        }
    }
    /**
     * if all the trash of the current world have been picked up, adds more.
     */
    private void addMoreTrash()
    {
        int amount = 0;
        trashPickedUp++;
        switch(SelectMoon.getLevel())
        {
            case 1:
                amount = 30;
            break;
            case 2:
                amount = 20;
            break;
            case 3:
                amount = 15;
            break;
        }
        if(trashPickedUp >= amount)
        {
            World w = getWorld();
            
            ((ScrollMap) w).setTrash(15);
        }
    }
    /**
     * if this object is touching an instance of enemy, returns true, otherwise, false.
     */
    public boolean badSpawn()
    {
        if(isTouching(Enemy.class))
        {
            return true;
        }
        else 
        return false;
    }
    /**
     * plays a sound based on the type and state of this trash.
     */
    public void doSound()
    {
        switch(this.type)
        {
            case 1:
                sound = new GreenfootSound("pickup.wav");
            break;
            case 2:
                if(super.getValue() == 0)
                {
                    sound = new GreenfootSound("breakingGlass.wav");
                }
                else
                {
                    sound = new GreenfootSound("pickup.wav");
                }
            break;
            case 3:
                sound = new GreenfootSound("radioactive.wav");
            break;
            default:
                sound = new GreenfootSound("pickup.wav");
        }
        sound.play();
    }
    public int getType()
    {
        return this.type;
    }
    public static void setTrash()
    {
        trashPickedUp = 0;
    }
}
