/**
 * 
 * @author Fernando Vieyra
 * @version FINAL
 */
public class TrashType  
{
    private Aleatorio a;
    private int level;
    /**
     * Constructor for objects of class TrashType
     * @param level The level of trash.
     */
    public TrashType(int level)
    {
     a = new Aleatorio(); 
     this.level = level;
    }
    /**
     * Randomly creates all the parameters to create an instance of the class Trash.
     * @return An instance of the class Trash
    */
    public Trash makeTrash()
    {
        Trash trash;
        String imageName = a.getRandomCrap();
        String name = imageName.replace(".png","");
        
        int value = getRandomValue();
        int weight = getRandomWeight();
        int type = createType();
        
        trash = new Trash(imageName,value,weight,type,name);     
        
        return trash;
    }
    /**
     * sets the type of a trash given arbitrary odds.
     * @return the type of trash.
     */
    public int createType()
    {
        int type;
        int prob = a.obtenNumeroAleatorio(0,100);
        if(prob < 15)
        {
            type = 3;
        }
        else
        {
            if(prob < 33)
            {
                type = 2;
            }
            else
            {
                type = 1;
            }
        }
        return type;
    }
    /**
     * randomly sets the value of a trash to 0 if the trash is of type 2
     * @return True if the trash broke, false if not.
     */
    public static boolean breakChances()
    {
        int prob = 30;
        Aleatorio ale = new Aleatorio();
        if(ale.obtenNumeroAleatorio(0,100) <= prob)
        {
            return true;
        }
        else
            return false;
    }
    /**
     * gives the Trash a random value depending on the level.
     * @return the value of trash.
     */
    private int getRandomValue()
    {
        int value=0;
        switch(this.level)
        {
            case 1:
                value = a.obtenNumeroAleatorio(1,25);
            break;
            case 2:
                value = a.obtenNumeroAleatorio(5,30);
            break;
            case 3:
                value = a.obtenNumeroAleatorio(15,30);
            break;
        }
        return value;
    }
    /**
     * gives the Trash a random weight depending on the level.
     * @return the weight of trash.
     */
    private int getRandomWeight()
    {
        int value=0;
        switch(this.level)
        {
            case 1:
                value = a.obtenNumeroAleatorio(0,10);
            break;
            case 2:
                value = a.obtenNumeroAleatorio(2,12);
            break;
            case 3:
                value = a.obtenNumeroAleatorio(5,15);
            break;
        }
        return value;
    }
}
