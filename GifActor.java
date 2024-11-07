import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class GifActor extends Actor
{
    GifImage image;
    public GifActor(String name)
    {
        this.image = new GifImage("fired.gif");
        setImage(image.getCurrentImage());
    }
    public void act()
    {
        setImage(image.getCurrentImage());
    }
}
