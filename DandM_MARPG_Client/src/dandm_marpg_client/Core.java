/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

/**
 *
 * @author Dajne Win
 */
public class Core {
    
    /**
    * Constructor for the Core class
    * @author Dajne Win
    */
    public Core()
    {
        //startNetwork();
    }
    
    /*private void startNetwork()
    {
        NetworkCommunicationThread netThread = new NetworkCommunicationThread("");
        Thread thread = new Thread(netThread);
        thread.start();
    }*/
    
    /**
    * Loop that listens for user input from the keyboard and then sends this to the server for processing
    * @author Dajne Win
    */
    /*private void sendServerCommands()
    {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        try (Socket clientSocket = new Socket("127.0.0.1", 6789)) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendServerCommands();
    }*/
    
}
