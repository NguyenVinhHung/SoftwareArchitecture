/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.map;

import server.SocketCommunicator;

import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author HungHandsome
 */
public class MatchPanel extends JPanel {
    
    private GameMap map;
    private JTabbedPane chatTabs;
    private JTextArea globalChat;
    private JTextArea teamChat;
    private JTextArea chatTyper;
    //    private Socket chatSocket;
    private SocketCommunicator chatCommunicator;
    
    public MatchPanel(GameMap m) {
        map = m;
    }

    public MatchPanel(int[][] mapArr) {
        map = new GameMap(this, mapArr);

        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        map.draw(g);
    }
       
}
