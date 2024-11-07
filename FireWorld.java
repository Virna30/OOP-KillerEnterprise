import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireWorld here.
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class FireWorld extends World
{
    private static World thisWorld;
    private GreenfootImage image;
    public FireWorld()
    {    
        super(820,462, 1); 
        thisWorld = this;
        
        resetGame();
        
        image = new GreenfootImage("youDed.png");
        image.scale(820,462);
        
        GifActor fired = new GifActor("fired.gif");
        addObject(fired,410,250);
        
        Button retry = new Button("retryButton.png",new MainScreen());
        addObject(retry,410,410);
    }
    /**
     * Method resetGame
     * When an instance of this world is created, the game has completed
     * this resets the game to the initial state.
     */
    private void resetGame()
    {
        SelectMoon.setLevel(1);
        SelectMoon.setMoney(SelectMoon.getMoney()*-1);
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
