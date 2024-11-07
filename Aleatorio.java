import greenfoot.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Aleatorio here.
 * 
 * @author Josué Alejandro Cedillo Gámez
 * @version FINAL
 */
public class Aleatorio  
{
    private ArrayList<String> trash;
    /**
     * Constructor for objects of class Aleatorio
     */
    public Aleatorio()
    {
        this.trash = new ArrayList<>();
        readTrashNames();
    }
    /**
     * @return A random number between the given parameters.
     */
    public int obtenNumeroAleatorio(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    /**
     * @return A random chance of spawing a wanderer.
     */
    public int spawnWanderer(){
        return obtenNumeroAleatorio(1, 20);
    }
    /**
     * @return A random direction for the wanderer.
     */
    public int getWandererView(){
        return obtenNumeroAleatorio(1, 4);
    }
    /**
     * @return A random X coordinate.
     */
    public int getRandomX(World currWorld)
    {
        // put your code here
        return obtenNumeroAleatorio(0, currWorld.getWidth());
    }
    /**
     * @return A random Y coordinate.
     */
    public int getRandomY(World currWorld)
    {
        // put your code here
        return obtenNumeroAleatorio(0, currWorld.getHeight());
    }
    /**
     * @return A random direction for the FireSnail.
     */    
    public int getAxis(){
        return obtenNumeroAleatorio(1, 3);
    }
    /**
     * @return A random X coordinate of the world.
     */
    public int getRandomWorldX()
    {
        // put your code here
        return obtenNumeroAleatorio(150, ScrollMap.getWWIDTH() - 200);
    }
    /**
     * @return A random Y coordinate of the world.
     */
    public int getRandomWorldY()
    {
        // put your code here
        return obtenNumeroAleatorio(150, ScrollMap.getWHEIGHT() - 200);
    }
    /**
     * reads all the name of the trash on the file trash.txt
     */    
    public void readTrashNames()
    {
        try
        {
            File archivo = new File("trash.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String trashImgName;
            try
            {
                while((trashImgName = br.readLine()) != null)
                {
                    this.trash.add(trashImgName);
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @return A random name of the trash array.
     */
    public String getRandomCrap()
    {
        return this.trash.get(obtenNumeroAleatorio(0,this.trash.size()));
    }
}
