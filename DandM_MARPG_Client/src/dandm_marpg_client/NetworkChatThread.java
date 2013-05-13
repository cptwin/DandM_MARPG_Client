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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dajne Win
 */
public class NetworkChatThread implements Runnable {
    
    private String commandOutToServer;
    public String resultFromServer = "";
    private MainJFrame mainJFrame;
    
    public NetworkChatThread(String commandToSend, MainJFrame mainFrame)
    {
        this.mainJFrame = mainFrame;
        this.commandOutToServer = commandToSend;
    }

    @Override
    public void run() {
        try (Socket clientChatSocket = new Socket(mainJFrame.jTxtServerIp.getText(), 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientChatSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientChatSocket.getInputStream()));
            outToServer.writeBytes(commandOutToServer + '\n');
            while(true)
            {
                resultFromServer = inFromServer.readLine();
                if(resultFromServer != null)
                {
                    resultFromServer = resultFromServer.replaceFirst("rchat", "");
                    if(!"".equals(resultFromServer))
                    {
                        mainJFrame.chatLog.add(resultFromServer);
                        mainJFrame.drawPanel.repaint();
                        mainJFrame.drawPanel.revalidate();
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkChatThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
