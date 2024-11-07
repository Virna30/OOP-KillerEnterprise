import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra 
 * @version FINAL
 */
public class Lava extends Enemy
{
    private int axis;
    private GifImage gimage;
    public Lava(int axis)
    {
        super("Lava");
        this.axis = axis;
        this.gimage = new GifImage("lava.gif");
        setImage(gimage.getCurrentImage());
        if(this.axis == 2)
        {
            setRotation(90);
        }
    }
    public void act()
    {
        this.killPlayer();
        setImage(gimage.getCurrentImage());
    }
    /**
     * If a player is touching this object and has no immunity to lava, calls the
     * method of super class, killPlayer.
     */
    @Override
    public void killPlayer()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            if(checkPlayerImmunity((Player)p))
            {
                super.killPlayer();
            }
        }
    }
    /**
     * if the player has spawn immunity, or has either plastic or iron boots, returns
     * false, otherwise, returns true.
     */
    private boolean checkPlayerImmunity(Player p)
    {
        boolean isKillable = true;
        Actor pb;
        if(p.getItem().getName() == "boots" || p.hasPlasticBoots() || p.isImmune())
        {
            isKillable = false;
        }
        return isKillable;
    }
}
