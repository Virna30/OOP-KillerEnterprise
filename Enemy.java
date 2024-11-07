import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @author Josué Alejandro Cedillo Gámez 
 * @version FINAL
 */
public class Enemy extends ScrollingActor
{
    private String name;
    /**
     * @param name The name of the enemy.
     */
    public Enemy(String name){
        this.name = name;
    }
    
    public void act()
    {
        // Add your action code here.
    }
    /**
     * @return the name of the enemy.
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * if one player is touching this object, create and set an instance of the world
     * GameDone.
     */
    public void killPlayer()
    {
        Actor p = getOneIntersectingObject(Player.class);
        if(p != null)
        {
            if(!((Player)p).isImmune())
            {
                Greenfoot.setWorld(new GameDone(this.getName()));
            }
        }
    }
}
