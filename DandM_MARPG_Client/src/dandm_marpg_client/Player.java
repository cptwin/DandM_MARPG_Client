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
    
    private int x;
    private int y;
    private int currentHealth;
    private static Socket socket;
    private boolean loggedIn;
    public javax.swing.JButton button;
    public boolean addedButton;
    
    public Player(String name, javax.swing.JButton pButton)
    {
        super(name);
        button = pButton;
        button.setVisible(true);
        addedButton = false;
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
    
    public void setLoggedIn(boolean bool)
    {
        loggedIn = bool;
    }
    
    public boolean isPlayerLoggedIn()
    {
        return loggedIn;
    }
    
}
