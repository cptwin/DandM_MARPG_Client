/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 *
 * @author Dajne Win
 */
public class MainJFrame extends javax.swing.JFrame implements ActionListener, KeyListener {

    public Player player;
    public HashSet<Entity> entities;
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        entities = new HashSet<>();
        this.setExtendedState(this.getExtendedState()|java.awt.Frame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        resultLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        resultLabel.setText("Result");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextField)
                            .addComponent(passwordTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resultLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                        .addComponent(registerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(registerButton)
                    .addComponent(resultLabel))
                .addContainerGap(214, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        String user = usernameTextField.getText();
        String pass = new String(passwordTextField.getPassword());
        NetworkCommunicationThread netThread = new NetworkCommunicationThread("login " + user + " " + pass, this);
        Thread thread = new Thread(netThread);
        thread.start();
        while(thread.isAlive())
        {
            Thread.yield();
        }
        resultLabel.setText(netThread.resultFromServer);
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed

    public synchronized void createPlayerButtons()
    {
        for(Entity e : entities)
        {
            if (e instanceof Player)
            {
                Player playerz = (Player)e;
                System.out.println(playerz.getName() + " X: " + playerz.getXCoOrd() + " Y: " + playerz.getYCoOrd());
                playerz.button.setBounds(playerz.getXCoOrd(), playerz.getYCoOrd(), 50, 50);
                if(playerz.addedButton == false)
                {
                    add(playerz.button);
                    playerz.button.setVisible(true);
                    playerz.addedButton = true;
                }
                playerz.button.revalidate();
                playerz.button.repaint();
            }
        }
    }
    
    public void setupLoginForm(boolean bool)
    {
        usernameTextField.setVisible(bool);
        passwordTextField.setVisible(bool);
        loginButton.setVisible(bool);
        registerButton.setVisible(bool);
        resultLabel.setVisible(bool);
        jLabel1.setVisible(bool);
        jLabel2.setVisible(bool);
    }
    
    public void setupPlayerForm(boolean bool)
    {
        if (bool)
        {
            NetworkCommunicationThread netThread = new NetworkCommunicationThread("move " + player.getName() + " " + player.getXCoOrd() + " " + player.getYCoOrd(), this);
            Thread thread = new Thread(netThread);
            thread.start();
            while(thread.isAlive())
            {
                Thread.yield();
            }
            createPlayerButtons();
            //playerButton = new JButton("P");
            //player.button.setBounds(player.getXCoOrd(), player.getYCoOrd(), 50, 50);
            //player.button.setVisible(true);
            //this.addKeyListener(this);
            player.button.addKeyListener(this);
        }
        else
        {
            player.button.setVisible(false);
            player.button = null;
        }
    }
    
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        String user = usernameTextField.getText();
        String pass = new String(passwordTextField.getPassword());
        NetworkCommunicationThread netThread = new NetworkCommunicationThread("register " + user + " " + pass, this);
        Thread thread = new Thread(netThread);
        thread.start();
        while(thread.isAlive())
        {
            Thread.yield();
        }
        resultLabel.setText(netThread.resultFromServer);
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });   
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == moveRightButton)
        {
            if ((x + 100) <= this.getSize().width)
            {
                x += 50;
                playerButton.setBounds(x, y, 50, 50);
            }
        }*/
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ((player.getYCoOrd() - 50) >= 0)
            {
                int set = player.getYCoOrd() - 50;
                player.setYCoOrd(set);
                NetworkCommunicationThread netThread = new NetworkCommunicationThread("move " + player.getName() + " " + player.getXCoOrd() + " " + player.getYCoOrd(), this);
                Thread thread = new Thread(netThread);
                thread.start();
                while(thread.isAlive())
                {
                    Thread.yield();
                }
                /*NetworkCommunicationThread netThreadOtherPlayers = new NetworkCommunicationThread("otherplayers", this);
                Thread threadOtherPlayers = new Thread(netThreadOtherPlayers);
                threadOtherPlayers.start();*/
                if (!player.isPlayerLoggedIn())
                {
                    setupLoginForm(true);
                }
                //playerButton.setBounds(player.getXCoOrd(), player.getYCoOrd(), 50, 50);
            }            
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ((player.getYCoOrd() + 100) <= this.getSize().height)
            {
                int set = player.getYCoOrd() + 50;
                player.setYCoOrd(set);
                NetworkCommunicationThread netThread = new NetworkCommunicationThread("move " + player.getName() + " " + player.getXCoOrd() + " " + player.getYCoOrd(), this);
                Thread thread = new Thread(netThread);
                thread.start();
                while(thread.isAlive())
                {
                    Thread.yield();
                }
                /*NetworkCommunicationThread netThreadOtherPlayers = new NetworkCommunicationThread("otherplayers", this);
                Thread threadOtherPlayers = new Thread(netThreadOtherPlayers);
                threadOtherPlayers.start();*/
                if (!player.isPlayerLoggedIn())
                {
                    setupLoginForm(true);
                }
                //playerButton.setBounds(player.getXCoOrd(), player.getYCoOrd(), 50, 50);
            }            
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if ((player.getXCoOrd() - 50) >= 0)
            {
                int set = player.getXCoOrd() - 50;
                player.setXCoOrd(set);
                NetworkCommunicationThread netThread = new NetworkCommunicationThread("move " + player.getName() + " " + player.getXCoOrd() + " " + player.getYCoOrd(), this);
                Thread thread = new Thread(netThread);
                thread.start();
                while(thread.isAlive())
                {
                    Thread.yield();
                }
                /*NetworkCommunicationThread netThreadOtherPlayers = new NetworkCommunicationThread("otherplayers", this);
                Thread threadOtherPlayers = new Thread(netThreadOtherPlayers);
                threadOtherPlayers.start();*/
                if (!player.isPlayerLoggedIn())
                {
                    setupLoginForm(true);
                }
                //playerButton.setBounds(player.getXCoOrd(), player.getYCoOrd(), 50, 50);
            }            
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if ((player.getXCoOrd() + 100) <= this.getSize().width)
            {
                int set = player.getXCoOrd() + 50;
                player.setXCoOrd(set);
                NetworkCommunicationThread netThread = new NetworkCommunicationThread("move " + player.getName() + " " + player.getXCoOrd() + " " + player.getYCoOrd(), this);
                Thread thread = new Thread(netThread);
                thread.start();
                while(thread.isAlive())
                {
                    Thread.yield();
                }
                /*NetworkCommunicationThread netThreadOtherPlayers = new NetworkCommunicationThread("otherplayers", this);
                Thread threadOtherPlayers = new Thread(netThreadOtherPlayers);
                threadOtherPlayers.start();*/
                if (!player.isPlayerLoggedIn())
                {
                    setupLoginForm(true);
                }
                //playerButton.setBounds(player.getXCoOrd(), player.getYCoOrd(), 50, 50);
            }            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
