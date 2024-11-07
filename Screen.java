import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra 
 * @version FINAL
 */
public class Screen extends Actor
{
    private GreenfootImage image;
    private String text;
    private boolean onScreen;
    /**
     * Screen Constructor for player information.
     * @param p an instance of player linked to the information to show.
     */
    public Screen(Player p)
    {
        text = "Weight: "+ p.getCurrentWeight() + "\n";
        text += "Money collected: " + p.getMoney();
        
        image = new GreenfootImage(text,15,Color.BLACK,Color.WHITE);
        setImage(image);
        onScreen = false;
    }
    /**
     * Screen Constructor for trash information.
     * @param t an instance of trash linked to the information to show.
     */
    public Screen(Trash t)
    {
        text = t.getName() + "\nWeight: " + t.getWeight() + "\n";
        text += "Value: " + t.getValue() + "\n";
        image = new GreenfootImage(text,15,Color.BLACK,null);
        setImage(image);
        onScreen = false;
    }
    /**
     * Screen constructor that only shows an image.
     * @param name The name of the image file.
     */
    public Screen(String name)
    {
        image = new GreenfootImage(name);
        setImage(image);
    }
    /**
     * Screen constructor that only shows a text
     */
    public Screen(String text,int size,Color color1,Color color2)
    {
        image = new GreenfootImage(text,size,color1,color2);
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    /**
     * Updates the player information shown.
     */
    public void setTextInfoPlayer(int weight,int money)
    {
        GreenfootImage img = getImage();
        img.clear();
        text = "Weight: "+ weight + "\n";
        text += "Money collected: " + money;
        image = new GreenfootImage(text,15,Color.BLACK,Color.WHITE);
        setImage(image);
        this.onScreen = false;
    }
    /**
     * Updates the trash information shown.
     */
    public void setTextInfoTrash(Trash t)
    {
        GreenfootImage img = getImage();
        img.clear();  
        text = t.getName() + "\nWeight: " + t.getWeight() + "\n";
        text += "Value: " + t.getValue() + "\n";
        image = new GreenfootImage(text,15,Color.BLACK,null);
        setImage(image);
    }
    /**
     * Sets or updates the ships information shown.
     */
    public void setTextInfoShip(Ship s)
    {
        GreenfootImage img = getImage();
        img.clear();
        String text = s.getCurrMoney() + "/" + s.getQuota();
        if(!s.getEnough())
        {
            image = new GreenfootImage(text,15,Color.RED,Color.WHITE);
        }
        else
        {
            image = new GreenfootImage(text,15,Color.GREEN,Color.WHITE);
        }
        setImage(image);
    }
    /**
     * Updates the information of the uses left on a limit use item.
     * @param time The amount of uses left.
     */
    public void setTimeLeft(int time)
    {
        this.image = new GreenfootImage(time + " uses left",15,Color.BLACK,Color.WHITE);
        setImage(this.image);
    }
    /**
     * @return The value of onScreen.
     */
    public boolean isOnScreen()
    {
        return this.onScreen;
    }
    /**
     * switches the polarity of the onScreen boolean attribute.
     */
    public void setOnScreen()
    {
        this.onScreen = !this.onScreen;
    }
}
