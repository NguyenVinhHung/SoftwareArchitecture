/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import view.map.GameMap;
import view.map.MapUtil;
import view.map.MatchPanel;

/**
 *
 * @author HungHandsome
 */
public class Main extends JFrame {

    public static final int FRAME_W = 1024;
    public static final int FRAME_H = 768;
    
    private static Main frame;
    
    private JPanel currPanel;
    private JPanel tempPanel;
    
    public Main() {
        super("");
        
//        currPanel = new WelcomeView();
        
        GameMap map = new GameMap(MapUtil.MAP_ARRAYS[0]);
        currPanel = new MatchPanel(map);
        
        
        add(currPanel);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_W, FRAME_H);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new Main();
        
        TimeCounter t = new TimeCounter();
//        t.startCounting();
    }
    
    /**
     * Returns the singleton instance of the Main class.
     * @return the singleton instance
     */
    public static Main getInstance() {
        if(frame == null) {
            frame = new Main();
        }
        return frame;        
    }
    
//    public void switchPanel(JPanel newPanel) {
//        tempPanel = currPanel;
//        currPanel = newPanel;
//        currPanel.updateUI();
//    }
    
    public void switchPanel(JPanel newPanel) {
        tempPanel = currPanel;
        setCurrPanel(newPanel);
    }

    public void switchBack() {
        setCurrPanel(tempPanel);
    }

    public void setCurrPanel(JPanel curr) {
        remove(currPanel);
        currPanel = curr;
        add(currPanel);
        validate();
        currPanel.updateUI();
    }

    public JPanel getCurrPanel() {
        return currPanel;
    }
    
}
