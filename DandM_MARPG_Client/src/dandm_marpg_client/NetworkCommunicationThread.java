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
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Dajne Win
 */
public class NetworkCommunicationThread implements Runnable {
    
    private String commandOutToServer;
    public String resultFromServer = "";
    private MainJFrame mainJFrame;
    private String serverIp = "";
    
    public NetworkCommunicationThread(String commandToSend, MainJFrame mainFrame)
    {
        this.mainJFrame = mainFrame;
        this.commandOutToServer = commandToSend;
    }

    @Override
    public void run() {
        serverIp = mainJFrame.jTxtServerIp.getText();
            try (Socket clientSocket = new Socket(serverIp, 6789)) {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.writeBytes(commandOutToServer + '\n');
                if(!commandOutToServer.startsWith("move"))
                {
                    resultFromServer = inFromServer.readLine();
                }
                switch (resultFromServer) {
                    case "You are now logged in!":
                        String[] str_array = commandOutToServer.split(" ");
                        mainJFrame.player = new Player(str_array[1], new JButton(str_array[1]));
                        mainJFrame.add(mainJFrame.player.button);
                        mainJFrame.entities.add(mainJFrame.player);
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
                if(resultFromServer.startsWith("movingentities"))
                {
                    resultFromServer = resultFromServer.replaceFirst("movingentities", "");
                    if(!resultFromServer.equals(""))
                    {
                        String[] allMovingEntities = resultFromServer.split(" ");
                        HashSet<Entity> entities = new HashSet<>();
                        for(int i = 0; i < allMovingEntities.length; i++)
                        {
                            boolean addPlayerToSet = true;
                            String[] entityArray = allMovingEntities[i].split("/");

                            Entity[] ents = new Entity[mainJFrame.entities.size()];
                            mainJFrame.entities.toArray(ents);
                            for(Entity e : ents)
                            {
                                if(e.getName().equalsIgnoreCase(entityArray[0]))
                                {
                                    addPlayerToSet = false;
                                    Player player = (Player)e;
                                    player.setXCoOrd(Integer.parseInt(entityArray[1]));
                                    player.setYCoOrd(Integer.parseInt(entityArray[2]));
                                }
                            }
                            if(addPlayerToSet)
                            {
                                Player oPlayer = new Player(entityArray[0], new JButton(entityArray[0]));
                                oPlayer.setXCoOrd(Integer.parseInt(entityArray[1]));
                                oPlayer.setYCoOrd(Integer.parseInt(entityArray[2]));
                                entities.add(oPlayer);
                            }
                        }
                        mainJFrame.entities.addAll(entities);
                    }
                    mainJFrame.drawPanel.repaint();
                    mainJFrame.drawPanel.revalidate();
                }
                if(resultFromServer.startsWith("rchat"))
                {
                    System.out.println(resultFromServer);
                    resultFromServer = resultFromServer.replaceFirst("rchat", "");
                    System.out.println(resultFromServer);
                    mainJFrame.chatLog.add(resultFromServer);
                    mainJFrame.drawPanel.repaint();
                    mainJFrame.drawPanel.revalidate();
                }
            }
            catch (UnknownHostException ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
    }
    
}
