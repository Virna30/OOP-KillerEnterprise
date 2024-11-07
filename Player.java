import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Player extends Actor
    {
    private int speedX = 0;
    private int speedY = 0;
    private int SPEED = 4;
    private static final int BOUNDARY = 120;
    private boolean immunity;
    private int immuneTime = 300;
    
    private double steps;
    private double h;
    private String up;
    private String right;
    private String down;
    private String left;
    
    private int switchTimer;
    
    private int maxWeight = 25;
    private int currWeight;
    private int money;
    private Item item;
    private boolean hasPB;
    
    private GreenfootImage image;
    private Screen info;
    /**
     * Player Constructor
     *
     * @param it The main item of the player.
     * @param stepsInc the increment that the attribute h will increase.
     */
    public Player(Item it,double stepsInc)
    {
        image = new GreenfootImage("player.png");
        image.scale(50,50);
        setImage(image);
        
        currWeight = 0;
        money = 0;
        this.steps = 0;
        this.h = stepsInc;
        this.item = it;
        setMaxWeight();
        
        this.up = "w";
        this.right = "d";
        this.down = "s";
        this.left = "a";
        this.immunity = true;
        this.hasPB = false;
    }
    
    public void act()
    {      
        handleKeyPresses();
        boundedMove();
        if (this.steps >= 75.0){
            World world= this.getWorld();
            ((ScrollMap)world).spawnWanderer();
            this.steps = 0;
        }
        if(this.switchTimer > 0)
        {
            this.switchTimer--;
        }
        else if(this.up != "w")
        {
            switchKeys(0);
        }
        if(this.immunity)
        {
            setImmunityTime();
        }
    }
    
    private void handleKeyPresses()
    {
        handleArrowKey(this.left,-SPEED,0);
        handleArrowKey(this.right,SPEED,0);
        handleArrowKey(this.up,0,-SPEED);
        handleArrowKey(this.down,0,SPEED);
    }
    
    private void handleArrowKey(String k,int spdX,int spdY)
    {
        if(Greenfoot.isKeyDown(k))
        {
            this.steps += this.h;
            speedX = spdX;
            speedY = spdY;
            if(speedY == 0)
            {
                if(speedX < 0)
                    setRotation(270);//izquierda
                else
                    setRotation(90);//derecha
            }
            else
            {
                if(speedY < 0)
                    setRotation(0);//arriba
                else
                    setRotation(180);//abajo
            }
        }
    }
    
    private void boundedMove()
    {
        if( speedX+getX() <= BOUNDARY ) 
        {
         setLocation(BOUNDARY, getY());
         ((ScrollMap)getWorld()).shiftWorld(-speedX, 0);
        } else if( speedX+getX() >= getWorld().getWidth()-BOUNDARY ) 
        {
         setLocation(getWorld().getWidth()-BOUNDARY, getY());
         ((ScrollMap)getWorld()).shiftWorld(-speedX, 0);
        } else {
         setLocation(getX()+speedX, getY());
        }
        
        if( speedY+getY() <= BOUNDARY ) 
        {
         setLocation(getX(), BOUNDARY);
         ((ScrollMap)getWorld()).shiftWorld(0, -speedY);
        } else if( speedY+getY() >= getWorld().getHeight()-BOUNDARY ) {
         setLocation(getX(), getWorld().getHeight()-BOUNDARY);
         ((ScrollMap)getWorld()).shiftWorld(0, -speedY);
        } else {
         setLocation(getX(), getY()+speedY);
        }
        speedX = 0;
        speedY = 0;
    }
    
    private void tooHeavy()
    {
        if(currWeight >= this.maxWeight)
            SPEED = 3;
        else
            SPEED = 4;        
    }
    /**
     * updates the values of currWeight and money
     * @param value The amount of money to add to money
     * @param weight the amount of weight to add to weight
     */
    public void grabbingTrash(int value,int weight)
    {
        this.currWeight += weight;
        this.money += value;
        this.info.setTextInfoPlayer(currWeight,money);
        tooHeavy();
    }
    /**
     * Switches the orientation of the keys the player uses to move.
     * @param time The current time left to reset the keys.
     */
    public void switchKeys(int time)
    {
        this.switchTimer = time;
        if(time != 0)
        {
            this.left = "d";
            this.up = "s";
            this.right = "a";
            this.down = "w";
        }
        else
        {
            this.left = "a";
            this.up = "w";
            this.right = "d";
            this.down = "s";
        }
    }
    
    private void setImmunityTime()
    {
        if(this.immuneTime > 0)
        {
            this.immuneTime--;
        }
        else
        {
            this.immunity = false;
        }
    }
    /**
     * @return If the player is touching an instance of the class Lava, returns true,
     * otherwise, returns false.
     */
    public boolean isInLava()
    {
        return isTouching(Lava.class);
    }
    /**
     * Checks if the main item of the player is the box, if so, then
     * sets the value of maxWeight to 40.
     */
    public void setMaxWeight()
    {
        if(getItem().getName() == "box")
        {
            this.maxWeight = 40;
        }
    }
    /**
     * Sets the value of immunity to false.
     */
    public void setImmunity()
    {
        this.immunity = false;
    }
    /**
     * @return The current weight the player is holding.
     */
    public int getCurrentWeight()
    {
         return this.currWeight;   
    }
    public void setWeight(int w)
    {
        this.currWeight += w;
        tooHeavy();
    }
    /**
     * @return The amount of money the player is currently holding.
     */
    public int getMoney()
    {
        return this.money;
    }
    /**
     * adds an amount of money to the money the player currently has, and updates
     * the information shown in the attribute info.
     */
    public void setMoney(int money)
    {
        this.money += money;
        info.setTextInfoPlayer(this.currWeight,this.money);
    }
    /**
     * sets the value of hasPB to a given stage.
     * @param stage the boolean value to give to hasPB.
     */
    public void setPlasticBoots(boolean stage)
    {
        this.hasPB = stage;
    }
    /**
     * @return the attribute info of this object.
     */
    public Screen getInfo()
    {
        return this.info;
    }
    /**
     * Sets the attribute of this object info to an instance of the class Screen.
     */
    public void setInfo(Screen info)
    {
        this.info = info;
    }
    /**
     * @return The main item of the player.
     */
    public Item getItem()
    {
        return this.item;
    }
    /**
     * @return the current value of the attribute immunity.
     */
    public boolean isImmune()
    {
        return this.immunity;
    }
    /**
     * 
     *
     * @return The return value
     * if the player has the item Plastic Boots, returns true,
     * otherwise, returns false
     */
    public boolean hasPlasticBoots()
    {
        
        return this.hasPB;
    }
    /**
     * Sets the value of the attribute to true
     *
     */
    public void setPlasticBoots()
    {
        this.hasPB = true;
    }
}
