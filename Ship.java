import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Ship extends ScrollingActor
{
    private int quota;
    private int currMoney;
    private boolean isEnough;
    private Screen info;
    /**
     * @param quota The amount of money required to leave succesfully.
     */
    public Ship(int quota)
    {
        GreenfootImage image = getImage();
        image.scale(64,64);
        setImage(image);
        
        this.quota = quota;
        currMoney = 0;
        this.isEnough = false;
        info = new Screen(this.currMoney + "/" + this.quota,15,Color.RED,Color.WHITE);
    }
    public void act()
    {
        playerNear();
    }
    /**
     * adds the info attribute to the world, and prepares this object 
     * for different player interactions.
     */
    public void playerNear()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            getWorld().addObject(this.info,getX(),getY() - 50);
            info.setOnScreen();
            playerDeposits((Player)p);
            wantToLeave();
        }
        else
        {
            getWorld().removeObject(this.info);
            info.setOnScreen();
        }
    }
    /**
     * if a player presses the key "e", the money that the player is holding is added
     * to the money this ship is holding, leaving the player with 0 money, also
     * removes the weight the player is holding.
     */
    public void playerDeposits(Player p)
    {
        if(Greenfoot.isKeyDown("e"))
        {
            this.currMoney += p.getMoney();
            p.setMoney(-1*p.getMoney());
            p.setWeight(-1*p.getCurrentWeight());
            info.setTextInfoShip(this);
            if(!this.isEnough)
            {
                compareMoney();
            }
        }
    }
    /**
     * if the money this ship is currently holding is greater than, or equal to 
     * the cuota, sets the value of isEnough to true.
     */
    public void compareMoney()
    {
        if(this.currMoney >= this.quota)
        {
            this.isEnough = true;
        }
    }
    /**
     * if a player presses "f", returns the player to the main menu and
     * prepares the SelectMoon world for the next level.
     */
    public void wantToLeave()
    {        
        if(Greenfoot.isKeyDown("f"))
        {
            World w = getWorld();
            int nextLvl = ((ScrollMap) w).getLevel() + 1;
            SelectMoon.setLevel(nextLvl);
            SelectMoon.setMoney(this.currMoney);
            Greenfoot.setWorld(new LevelCompleted());
        }
    }
    /**
     * @return True if this object is touching an enemy, otherwise, return false.
     */
    public boolean badSpawn()
    {
        if(isTouching(Enemy.class) || isTouching(Lava.class))
        {
            return true;
        }
        else 
        return false;
    }
    
    public int getQuota()
    {
        return this.quota;
    }
    public int getCurrMoney()
    {
        return this.currMoney;
    }
    public boolean getEnough()
    {
        return this.isEnough;
    }
}
