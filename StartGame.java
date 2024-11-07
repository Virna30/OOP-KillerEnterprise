import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Virna Lissi Martínez Sánchez  
 * @version FINAL
 */
public class StartGame extends World
{
    private static World Menu;

    Button playButton;
    Button welcomeButton;
    /**
     * Constructor for objects of class StartGame.
     * 
     */
    public StartGame()
    {    
        super(820, 462, 1);
        Menu = this;
        
        World welcome = Welcome.getWorld();
        this.playButton = new Button("PlayButton.png", new SelectMoon());
        addObject(playButton, 410, 100);
        
        this.welcomeButton = new Button("WelcomeButton.png", welcome);
        addObject(welcomeButton, 410, 410);
    }
    
    /**
     * Returns the last instance of this world.
     */
    public static World getWorld(){
        return Menu;
    }
}
