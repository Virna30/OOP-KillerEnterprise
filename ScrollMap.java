import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class ScrollMap extends World
{
    private int xOffset = 0;
    private int yOffset = 0;
    private final static int SWIDTH = 600;
    private final static int SHEIGHT = 400;
    private final static int WWIDTH = 2400;
    private final static int WHEIGHT = 2400;
    private GreenfootImage bimg;
    private Aleatorio aleatorio;
    private int level;
    
    private Player player;
    private Item playerItem;
    
    private Timer countdown;
    
    
    /**
     * ScrollMap Constructor
     *
     * @param plItem The item that will be asigned to the player.
     * @param level the level of difficulty of the world to be created.
     */
    public ScrollMap(Item plItem, int level)
    {    
        super(SWIDTH,SHEIGHT,1,false);
        aleatorio = new Aleatorio();
        
        setWorldImage(level);
        shiftWorld(0,0);
        
        this.playerItem = plItem;
        this.level = level;
        
        switch(this.level)
        {
            case 1: 
                prepareWorld1();
            break;
            case 2:
                prepareWorld2();
            break;
            case 3:
                prepareWorld3();
            break;
        }
        
        //addObject(new Counter(), 540, 375);
    }
    
    /**
     * Method shiftWorld
     *
     * @param dx coordinate x to shift.
     * @param dy coordinate y to shift.
     */
    public void shiftWorld(int dx,int dy)
    {
        if( (xOffset + dx) <= 0 && (xOffset + dx) >= SWIDTH - WWIDTH) 
        {
         xOffset = xOffset + dx;
         shiftWorldBackground(dx, 0);
         shiftWorldActors(dx, 0);
        }
        if( (yOffset + dy) <= 0 && (yOffset + dy) >= SHEIGHT - WHEIGHT) 
        {
         yOffset = yOffset + dy;
         shiftWorldBackground(0, dy);
         shiftWorldActors(0, dy);
         }
    }
    /**
     * Method shiftWorldBackGround
     *
     * @param dx coordinate x to shift.
     * @param dy coordinate y to shift.
     */
    private void shiftWorldBackground(int dx,int dy)
    {
        GreenfootImage bkgd = new GreenfootImage(SWIDTH, SHEIGHT);
         bkgd.drawImage(bimg, xOffset, yOffset);
         setBackground(bkgd);
    }
    /**
     * Method shiftWorldActors
     *
     * @param dx coordinate x to shift.
     * @param dy coordinate y to shift.
     */
    private void shiftWorldActors(int dx,int dy)
    {
        List<ScrollingActor> saList = getObjects(ScrollingActor.class);
        for(ScrollingActor a : saList)
        {
            a.setAbsoluteLocation(dx,dy);
        }
    }
    
    /**
     * Method prepareWorld1
     * Creates and adds all the actors needed for the level to work.
     */
    private void prepareWorld1()
    {
        bimg = new GreenfootImage("floor2.png");
        bimg.scale(2400,2400);
        
        countdown = new Timer(10, 0, 0);
        addObject(countdown, 540, 375);
        
        setPlayer(1);
        
        if(this.aleatorio.obtenNumeroAleatorio(0,100) < 20)
        {
            FireSnail snail = spawnSnail();
            snail.setPlayer(this.player);   
        }
        setPlayerLocation();
        
        setTrash(30);
        
        setStaticObjects(this.player,2);
        
        spawnShip(150);
        
        spawnSecondHandObjects();
    }
    /**
     * Method prepareWorld2
     * Creates and adds all the actors needed for the level to work.
     */
    private void prepareWorld2()
    {
        bimg = new GreenfootImage("cementeryFloor.png");
        bimg.scale(2400,2400);    
        
        countdown = new Timer(7, 0, 0);
        addObject(countdown, 540, 375);
        
        setPlayer(1.5);
        
        if(this.aleatorio.obtenNumeroAleatorio(0,100) < 50)
        {
            FireSnail snail = spawnSnail();
            snail.setPlayer(this.player);   
        }
        
        setPlayerLocation();
        
        setTrash(20);
        
        setStaticObjects(this.player,4);
        
        spawnShip(250);
        
        spawnSecondHandObjects();
    }
    /**
     * Method prepareWorld3
     * Creates and adds all the actors needed for the level to work.
     */
    private void prepareWorld3()
    {
        bimg = new GreenfootImage("underworld.png");
        bimg.scale(2400,2400);    
        
        countdown = new Timer(5, 0, 0);
        addObject(countdown, 540, 375);
        
        setPlayer(2);
        
        FireSnail snail = spawnSnail();
        snail.setPlayer(this.player);  
        
        setPlayerLocation();
        
        setTrash(15);
        
        setStaticObjects(this.player,6);
        
        spawnShip(350);
        
        spawnSecondHandObjects();
    }
    
    /**
     * Method setPlayer
     * Instantiates an object of Player and adds it to the world
     *
     * @param stepsInc the increment of steps that determines the spawn rate of 
     * wanderers.
     */
    private void setPlayer(double stepsInc)
    {
        this.player = new Player(this.playerItem,stepsInc);
        Screen screen = new Screen(this.player);
        this.player.setInfo(screen);
        
        addObject(screen,60,50);
    }
    
    /**
     * Method setPlayerLocation
     * sets randomly the initial location of the player.
     */
    private void setPlayerLocation()
    {
        addObject(this.player,0,0);
        
        this.player.setLocation( aleatorio.getRandomWorldX() + 50,aleatorio.getRandomWorldY()+50);
        setItemImage(this.player);
    }
    /**
     * Method spawnShip
     * Sets the location of the ship, checking that the ship does not overlap with
     * any enemy.
     * @param quota The amount of money that the player needs to collect.
     */
    public void spawnShip(int quota)
    {
        Ship s = new Ship(quota);
        addObject(s,0,0);
        
        boolean shipSet = false;
        int coordX,coordY;
        while(!shipSet)
        {
            coordX = aleatorio.getRandomWorldX();
            coordY = aleatorio.getRandomWorldY();
            s.setLocation(coordX,coordY);
            shipSet = !s.badSpawn();
        }
    }
    /**
     * sets the limited use items, only one can spawn depending of the main item
     * of the player.
     *
     */
    private void spawnSecondHandObjects()
    {
        if(this.playerItem.getName() == "flashlight")
        {
            spawnPlasticBoots();
        }
        if(this.playerItem.getName() == "boots")
        {
            spawnBadFlashLight();
        }
        if(this.playerItem.getName() == "box")
        {
            if(aleatorio.obtenNumeroAleatorio(0,100) < 50)
            {
                spawnPlasticBoots();
            }
            else
            {
                spawnBadFlashLight();
            }
        }
    }
    /**
     * Spawns the limit use item: BadFlaslight.
     * 
     */
    private void spawnBadFlashLight()
    {
        BadFlashlight bfl = new BadFlashlight();
        addObject(bfl,0,0);
        bfl.setLocation(aleatorio.getRandomWorldX(), aleatorio.getRandomWorldY());
    }
    /**
     * Spawns the limit use item: PlasticBoots.
     * 
     */
    private void spawnPlasticBoots()
    {
        PlasticBoots pb = new PlasticBoots(this.player);
        addObject(pb,0,0);
        pb.setLocation(this.player.getX(),this.player.getY());
    }
        
    /**
     * sets the image of the main item of the player.
     *
     * @param p An instance of Player.
     */
    public void setItemImage(Player p)
    {
        Item it = p.getItem();
        Screen item = new Screen(it.getName() + ".png");
        addObject(item,60,120);
        if(it.getName() == "flashlight")
        {
            ((Flashlight)it).givePlayer(p);
            p.getWorld().addObject(it,p.getX(),p.getY()-50); 
        }
    }
    /**
     * Sets the image of the limited use item of the player once the player grabs it.
     *
     * @param name the name of the item
     */
    public void setSecondItemImage(String name)
    {
        Screen sItem = new Screen(name + ".png");
        addObject(sItem,60,160);
    }
    
    /**
     * sets the initial location of a Wanderer.
     *
     */
    public void spawnWanderer(){
        if (aleatorio.spawnWanderer() <= 3){
            Wanderer wanderer = new Wanderer(aleatorio, this,this.countdown);
            addObject(wanderer, 0, 0);
        }
    }
    /**
     * Creates an instance of the class FireSnail.
     *
     * @return The object of FireSnail.
     */
    public FireSnail spawnSnail()
    {
        int axis = aleatorio.getAxis();
        int staticCoord;
        FireSnail snail1 = new FireSnail(axis);
        if(axis == 1)
        {
            staticCoord = aleatorio.getRandomWorldY();
            addLava(axis,staticCoord);
            addObject(snail1,0,staticCoord);
        }
        else
        {
            staticCoord = aleatorio.getRandomWorldX();
            addLava(axis,staticCoord);
            addObject(snail1,staticCoord,0);
        }
        return snail1;
    }
    /**
     *  Adds a line of lava on the world.
     *
     * @param axis sets the rotation of the objects.
     * @param staticCoord a coordinate that will stay the same.
     */
    public void addLava(int axis,int staticCoord)
    {
        int i;
        int sCoord = staticCoord;
        Lava lava;
        if(axis == 1)
        {
            for(i = 0;i <= getWHEIGHT();i += 62)
            {
                lava = new Lava(axis);
                addObject(lava,0,0);
                lava.setAbsoluteLocation(i,sCoord);
            }
        }
        else
        {
            for(i = 0;i <= getWWIDTH();i += 62)
            {
                lava = new Lava(axis);
                addObject(lava,0,0);
                lava.setAbsoluteLocation(sCoord,i);
            }
        }
    }
    /**
     * Sets an amount of objects of the class Bush and BadBush.
     *
     * @param p An instance of the class Player.
     * @param amount the amount of objects that will be added.
     */
    private void setStaticObjects(Player p,int amount)
    {
        int i;
        Bush b;
        for(i = 0; i < amount; i++)
        {
            b = new Bush(p);
            addObject(b,0,0);
            b.setAbsoluteLocation(aleatorio.getRandomWorldX(),aleatorio.getRandomWorldY());
        }
        
        BadBush bb;
        for(i = 0; i < amount; i++)
        {
            bb = new BadBush();
            addObject(bb,0,0);
            bb.setAbsoluteLocation(aleatorio.getRandomWorldX(),aleatorio.getRandomWorldY());
            bb.setHitbox();
        }
    }
    /**
     * Creates an amount of instances of the class Trash.
     *
     * @param amount the quantity of objects to create.
     */
    public void setTrash(int amount)
    {
        Trash.setTrash();
        int i,xCoords,yCoords;
        Trash t;
        TrashType ttype = new TrashType(this.level);
        for(i = 0;i < amount;i++)
        {
            t = ttype.makeTrash();
            
            addObject(t,0,0);
            spawnTrash(t);
        }
    }
    /**
     * adds to the world an object of the class Trash,
     * checking that is not touching an enemy.
     *
     * @param t The object to add to the world.
     */
    public void spawnTrash(Trash t)
    {
        int x,y;
        boolean isInWorld = false;
        while(!isInWorld)
        {
            x = aleatorio.getRandomWorldX();
            y = aleatorio.getRandomWorldY();
            t.setAbsoluteLocation(x,y);
            isInWorld = !t.badSpawn();
        }
    }
    /**
     * Sets the background image of the world.
     *
     * @param level the current level of difficulty of the world.
     */
    private void setWorldImage(int level)
    {
        switch(level)
        {
            case 1:
                this.bimg = new GreenfootImage("floor2.png");
            case 2:
                this.bimg = new GreenfootImage("cementeryFloor.png");
            case 3:
                this.bimg = new GreenfootImage("underworld.png");
        }
        this.bimg.scale(2400,2400);
    }
    /**
     * 
     *
     * @return The current level of the world.
     */
    public int getLevel()
    {
        return this.level;
    }
    
    /**
     * Method getWWIDTH
     *
     * @return The worlds width.
     */
    public static int getWWIDTH()
    {
        return WWIDTH;
    }
    /**
     * Method getWHEIGHT
     *
     * @return The worlds height.
     */
    public static int getWHEIGHT()
    {
        return WHEIGHT;
    }
    
}
