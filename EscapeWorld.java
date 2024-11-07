import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * @author Fernando Vieyra
 * @version FINAL
 */
public class EscapeWorld extends World
{
    private static World thisWorld;
    
    private GreenfootImage image;
    public EscapeWorld()
    {    
        super(820,462, 1); 
        thisWorld = this;
        
        image = new GreenfootImage("youWon.png");
        image.scale(820,462);
        resetGame();
        Button retry = new Button("backB.png",new MainScreen());
        addObject(retry,400,330);
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
