import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Virna Lissi Martínez Sánchez
 * @version FINAL
 */

public class MainScreen extends World
{
    /**
     * Constructor for objects of class MainScreen.
     * 
     */
    private static World Menu;
    private static Music music;
    
    private Button startButton;
    public MainScreen()
    {    
        super(820, 462, 1); 
        Menu = this;
        startButton = new Button("StartButton.png", new Welcome());
        addObject(startButton, 410, 375);
        
        music = new Music();
        music.getImage().clear();
        addObject(music,0,0);
    }
    /**
     * Method getWorld
     *
     * @return The current instance of this world.
     */
    public static World getWorld(){
        return Menu;
    }
    /**
     * Method getMusic
     *
     * @return returns the static variable of Music of this world.
     */
    public static Music getMusic()
    {
        return music;
    }
}
