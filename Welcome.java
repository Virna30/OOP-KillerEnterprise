import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Welcome here.
 * 
 * @author Virna Lissi Martínez Sánchez
 * @version FINAL
 */
public class Welcome extends World
{
    private static World wel;
    
    Button returnButton;
    /**
     * Constructor for objects of class Welcome.
     * 
     */
    public Welcome()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(820, 462, 1);
        wel = this;
        World menu = StartGame.getWorld();
        this.returnButton = new Button("ReturnButton.png", new StartGame());
        addObject(returnButton, 200, 440);
    }
    /**
     * Returns the last instance of this world.
     */
    public static World getWorld(){
        return wel;
    }
}
