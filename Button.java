import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Virna Lissi Martínez Sánchez 
 * @version FINAL
 */
public class Button extends Actor
{
    private World link;
    private Item item;
    /**
     * @param image The name of the image file
     * @param link The world that is created when clicking this button.
     */
    public Button(String image, World link){
        setImage(new GreenfootImage(image)); 
        this.link = link;
        this.item = null;
    }
    /**
     * @param image The name of the image file
     * @param link The item that is created when clicking this button.
     */
    public Button(String image,Item it)
    {
        setImage(new GreenfootImage(image));
        this.link = null;
        this.item = it;
    }
    
    public void act()
    {
        // Add your action code here.
        if (this.link != null){
            worldButton();
        }
        if(this.item != null){
            itemButton();
        }
    }
    private void worldButton()
    {
        if (Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(this.link);
            Greenfoot.playSound("Click.mp3");
            if(this.link instanceof ScrollMap)
            {
                musicStop();
            }
        }
    }
    private void itemButton()
    {
        if(Greenfoot.mouseClicked(this))
        {
            ((SelectMoon)getWorld()).setItem(item); 
            Greenfoot.playSound("Click.mp3");
        }
    }
    
    private void removeButton() {
        getWorld().removeObject(this);
    }
    /**
     * scales the image of this button.
     */
    public void resize(int w, int h)
    {
        getImage().scale(w,h);
    }
    /**
     * if the world that is linked to this button is an instance of ScrollMap,
     * stops the music in MainScreen.
     */
    public void musicStop()
    {
        if(MainScreen.getMusic() != null)
        {
            MainScreen.getMusic().stopMusic();
        }
    }
}
