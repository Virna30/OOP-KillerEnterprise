import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * @author Fernando Vieyra
 * @version FINAL
 */
public class LevelCompleted extends World
{
    private static World thisWorld;
    private GreenfootImage image;
    
    private Button retry;
    public LevelCompleted()
    {    
        super(820,462, 1); 
        thisWorld = this;
        
        image = new GreenfootImage("youWon.png");
        image.scale(820,462);
        
        retry = new Button("backB.png",new MainScreen());
        addObject(retry,400,330);
    }
    /**
     * Method getWorld
     *
     * @return The current instance of this world
     */
    private static World getWorld()
    {
        return thisWorld;
    }
}
