/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

import java.net.Socket;

/**
 *
 * @author dajnewin
 */
public class Player extends Entity {
    
    private static int x;
    private static int y;
    private static int currentHealth;
    private static Socket socket;
    
    public Player(String name)
    {
        super(name);
        x = 0;
        y = 0;
        currentHealth = 100;
    }
    
    public void setXCoOrd(int setX)
    {
        x = setX;
    }
    
    public int getXCoOrd()
    {
        return x;
    }
    
    public void setYCoOrd(int setY)
    {
        y = setY;
    }
    
    public int getYCoOrd()
    {
        return y;
    }
    
    public void setCurrentHealth(int setHealth)
    {
        currentHealth = setHealth;
    }
    
    public int getCurrentHealth()
    {
        return currentHealth;
    }
    
}
