/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

/**
 * @version 0.1 Alpha
 * @author Dajne Win
 */
public class Main {

    /**
     * The main class that gets called when the jar file is started
     * @param args the command line arguments
     * @author Dajne Win
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Core core = new Core(); //Creates a new Core client class that does the main guts of the processing and threading
        MainJFrame mainJFrame = new MainJFrame();               
        mainJFrame.setVisible(true);
        
    }
}
