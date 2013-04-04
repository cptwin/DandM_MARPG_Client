/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dajne Win
 */
public class NetworkCommunicationThread implements Runnable {
    
    private String commandOutToServer;
    public String resultFromServer = "";
    private MainJFrame mainJFrame;
    
    public NetworkCommunicationThread(String commandToSend, MainJFrame mainFrame)
    {
        this.mainJFrame = mainFrame;
        this.commandOutToServer = commandToSend;
    }

    @Override
    public void run() {
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        try (Socket clientSocket = new Socket("127.0.0.1", 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(commandOutToServer + '\n');
            resultFromServer = inFromServer.readLine();
            switch (resultFromServer) {
                case "You are now logged in!":
                    String[] str_array = commandOutToServer.split(" ");
                    mainJFrame.player = new Player(str_array[1]);
                    mainJFrame.player.setLoggedIn(true);
                    mainJFrame.setupLoginForm(false);
                    mainJFrame.setupPlayerForm(true);
                    break;
                
                case "Timed Out!":
                    mainJFrame.player.setLoggedIn(false);
                    mainJFrame.setupLoginForm(true);
                    mainJFrame.setupPlayerForm(false);
                    break;
            }
            if(resultFromServer.startsWith("otherplayers"))
            {
                String str = resultFromServer.replace("otherplayers", "");
                String[] str_array = str.split(" ");
                for (int i = 0; i < str_array.length; i++)
                {
                    String[] player_array = str_array[i].split("/");
                    Player player = new Player(player_array[0]);
                    player.setXCoOrd(Integer.parseInt(player_array[1]));
                    player.setYCoOrd(Integer.parseInt(player_array[2]));
                    mainJFrame.entities.add(player);
                    mainJFrame.createPlayerButtons();
                }
            }
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
