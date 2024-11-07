import greenfoot.*;
/**
 * 
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class SelectMoon extends World
{
    private Screen moon;
    private Button launchB;
    private static Item item;
    private static int level = 1;
    private static int money = 0;
    
    private Screen moneyScreen;
    
    public SelectMoon()
    {    
        super(820, 462, 1,false); 
        
        setMoonName();

        Button itemFl = new Button("flashlight.png",new Flashlight());
        itemFl.resize(64,64);
        addObject(itemFl,50,260);

        Button itemBo = new Button("boots.png", new Item("boots"));
        itemBo.resize(64,64);
        addObject(itemBo,200,260);

        Button itemBox = new Button("box.png",new Item("box"));
        itemBox.resize(64,64);
        addObject(itemBox,350,260);
        
        
        String text = "Money:\n" + money;
        Screen moneyCol = new Screen(text,100,Color.WHITE,null);
        addObject(moneyCol,190,115);
        
        setEnding();
    }
    /**
     * sets the text box with the name of the next moon.
     *
     */
    private void setMoonName()
    {
        Screen name;
        switch(level)
        {
            case 1:
                this.moon = new Screen("excaB.png");
                name = new Screen("Excavation",80,Color.WHITE,null);
            break;
            case 2:
                this.moon = new Screen("cementeryB.png");
                name = new Screen("Cementery",80,Color.WHITE,null);
            break;
            case 3:
                this.moon = new Screen("underB.png");
                name = new Screen("Underworld",80,Color.WHITE,null);
            break;
            default:
                this.moon = new Screen("underB.png");
                name = new Screen("???",25,Color.WHITE,null);
        }
        addObject(this.moon,612,112);
        addObject(name,612,112);
    }

    /**
     * Creates an object of ScrollMap with the item that the player selected.
     *
     * @param item the item the player selected.
     */
    public void setItem(Item item)
    {
        this.item = item;
        removeObject(this.launchB);
        setLaunchButton();
    }
    /**
     * Creates the next moon to play or the ending.
     *
     */
    private void setLaunchButton()
    {
        switch(level)
        {
            case 1: 
                launchB = new Button("launchButton.png",new ScrollMap(this.item,1));
            break;
            case 2:
                launchB = new Button("launchButton.png",new ScrollMap(this.item,2));
            break;
            case 3:
                launchB = new Button("launchButton.png",new ScrollMap(this.item,3));
            break;
            default:
                if(money >= 750)
                {
                    launchB = new Button("launchButton.png", new GameDone("The Company"));
                }
                else
                {
                    launchB = new Button("launchButton.png", new FireWorld());
                }
            break;
        }
        launchB.resize(200,210);
        addObject(launchB,630,325);
    }
    /**
     * Creates an ending world of the game depending on the actions of the player.
     *
     */
    public void setEnding()
    {   
        if(money < 1000)
        {
            this.moneyScreen = new Screen("lockedB.png");
            addObject(this.moneyScreen,200,350);
        }
        else
        {
            Button escape = new Button("escapeB.png", new EscapeWorld());
            addObject(escape,200,350);
        }
    }
    /**
     *
     * @return The current level of the world to play
     */
    public static int getLevel()
    {
        return level;
    }
    /**
     *
     * @param lvl the next level
     */
    public static void setLevel(int lvl)
    {
        level = lvl;
    }
    /**
     *
     * @return the amount of money collected
     */
    public static int getMoney()
    {
        return money;
    }
    /**
     *
     * @param collected the amount of money the player collected.
     */
    public static void setMoney(int collected)
    {
        money += collected;
    }
}
