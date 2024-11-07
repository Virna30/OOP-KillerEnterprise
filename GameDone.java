import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameDone here.
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class GameDone extends World
{
    private static World gd;
    
    private GreenfootImage image;
    private Button retry;
    private Screen killedBy;
    private String enemyName;
   
    public GameDone(String enemyName)
    {   
        super(820, 462, 1); 
        gd = this;
        this.enemyName = enemyName;
        image = new GreenfootImage("youDed.png");
        image.scale(820,462);
        if(this.enemyName == "The Company")
        {
            resetGame();
        }
        retry = new Button("retryButton.png",new MainScreen());
        addObject(retry,410,350);

        setEnemy(this.enemyName);
        
        GreenfootSound sound = new GreenfootSound("deathSound.wav");
        sound.play();
    }
    
    /**
     * Method resetGame
     * When an instance of this world is created with the enemyName "The company",
     * the game has completed, this resets the game to the initial state.
     */
    private void resetGame()
    {
        SelectMoon.setLevel(1);
        SelectMoon.setMoney(SelectMoon.getMoney()*-1);
    }
    
    /**
     * Method setEnemy
     * sets the name of the enemy.
     * @param name the name of the enemy who killed the player
     */
    public void setEnemy(String name)
    {
        String text = "You were killed by: " + name;
        killedBy = new Screen(text,25,Color.WHITE,null);
        addObject(killedBy,410,250);
    }
    /**
     * Method getWorld
     *
     * @return The current instance of this world
     */
    public static World getWorld(){
        return gd;
    }
}
