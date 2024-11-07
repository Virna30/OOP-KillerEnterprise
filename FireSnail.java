import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireSnail here.
 * 
 * @author Josué Alejandro Cedillo Gámez
 * @version FINAL
 */
public class FireSnail extends Enemy
{
    private int speed = 1;
    private int XorY;
    private int distWalked;
    private Spike spike;
    private Player pl;
    
    private GreenfootImage image;
    /**
     * @param xory The direction the snail will move.
     */
    public FireSnail(int xory){
        super("Fire Snail");
        this.XorY = xory;
        this.distWalked = 0;
        
        image = new GreenfootImage("FireSnail.png");
        image.scale(60,44);
        setImage(image);
        
        if(XorY == 2)
        {
            setRotation(90);
        }
    }
    public void act()
    {
        killPlayer();
        move(speed);
        if(this.pl != null)
            {checkPlayer();}
        if(getRotation() == 0 || getRotation() == 90)
        {
            this.distWalked++;
        }
        else
        {
            this.distWalked--;
        }
        if (XorY == 1)
            if (iAmInXEdge()){
                switchDirection();
            }
        if (XorY == 2)
            if (iAmInYEdge()){
                switchDirection();
            }
    }
    
    protected boolean iAmInXEdge(){
        int x = this.distWalked;
        
        return (x <= 0 || x >= ScrollMap.getWWIDTH());
    }
    
    protected boolean iAmInYEdge(){
        int y = this.distWalked;
        
        return (y <= 0 || y >= ScrollMap.getWHEIGHT());
    }
    
    private void switchDirection(){
        setRotation(getRotation() + 180); // Gira 180 grados
    }
    /**
     * If either the coordinate x or y of the player equals the ones of this
     * object, create and add an instance of the class Spike.
     */
    public void checkPlayer()
    {
        int pos;
        this.spike = new Spike();
        if(this.XorY == 1 && this.getX() == this.pl.getX())
        {
            pos = this.getY() - this.pl.getY();
            if(pos > 0)
            {
                spike.setRotation(270);
            }
            else
            {
                spike.setRotation(90);
            }
            getWorld().addObject(this.spike,getX(),getY());
        }
        if(this.XorY == 2 && this.getY() == this.pl.getY())
        {
                pos = this.getX() - this.pl.getX();
                if(pos > 0)
                {
                    spike.setRotation(180);
                }
                else
                {
                    //nada que hacer...
                }
                getWorld().addObject(this.spike,getX(),getY());
        }
    }
    /**
     * @return The value of the attribute XorY
     */
    public int getXorY()
    {
        return this.XorY;
    }
    /**
     * @param p the instace of Player that will be linked to this object.
     */
    public void setPlayer(Player p)
    {
        this.pl = p;
    }
    
}
