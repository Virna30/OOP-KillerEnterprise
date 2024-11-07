import greenfoot.*;
/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class Item extends ScrollingActor  
{
    private int value;
    private String name;
    private int weight;
    private GreenfootImage image;
    /**
     * Constructor for objects of class Item
     * @param val The value of this item.
     * @param weight The weight of this item.
     * @param name The name of this item.
     */
    public Item(int val,int weight, String name)
    {
        this.value = val;
        this.weight = weight;
        this.name = name;
    }
    /**
     * Constructor for objects of class Item
     * @param name The name of this item.
     */
    public Item(String name)
    {
        this.name = name;
        this.value = 0;
        this.weight = 0;
        image = new GreenfootImage(this.name+".png");
        image.scale(64,64);
    }
    /**
     * @return The value of this item.
     */
    public int getValue()
    {
        return this.value;
    }
    /**
     * @return The weight of this item.
     */
    public int getWeight()
    {
        return this.weight;
    }
    /**
     * @return The name of this item.
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * sets a new value to this item.
     * @param value The new value of the item.
     */
    public void setValue(int value)
    {
        this.value = value;
    }
    /**
     * sets a new weight to this item.
     * @param w The new weight of the item.
     */
    public void setWeight(int w)
    {
        this.weight = w;
    }
}
