package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.SpringUtil;
import view.panel.LoginView;
import view.panel.RegisterView;

import java.util.Stack;

/**
 *
 *
 */
public class Main extends JFrame {

    public static final int FRAME_W = 1024;
    public static final int FRAME_H = 768;

//    private static ApplicationContext ctx;
    private static Main frame;
    
    private JPanel currPanel;
    private Stack<JPanel> tempPanels;
    
//    public Main() {
//        super("");
//
//        currPanel = new WelcomeView();
////        currPanel = new RegisterView();
//
////        GameMap map = new GameMap(MapUtil.MAP_ARRS[0]);
////        currPanel = new MatchPanel(MapUtil.MAP_ARRS[0]);
//
//
//        add(currPanel);
//
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(FRAME_W, FRAME_H);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ctx = new ClassPathXmlApplicationContext("client_beans.xml");
//        frame = (Main)ctx.getBean("main");

        frame = (Main)SpringUtil.getBean("main");

//        TimeCounter t = new TimeCounter();
//        t.startCounting();
    }

    public void init() {
        System.out.println("init");

        add(currPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_W, FRAME_H);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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

    public void toRegisterView() {
        switchPanel((RegisterView)SpringUtil.getBean("registerPanel"));
    }

    public void toLoginView() {
        switchPanel((LoginView)SpringUtil.getBean("loginPanel"));
    }

    public void switchPanel(JPanel newPanel) {
//        tempPanels = currPanel;
        tempPanels.push(currPanel);
        setCurrPanel(newPanel);
    }

    public void switchBack() {
        setCurrPanel(tempPanels.pop());
    }

    public void setCurrPanel(JPanel curr) {
        if(currPanel == null) {
            currPanel = curr;
            return;
        }

        remove(currPanel);
        currPanel = curr;
        add(currPanel);
        validate();
        currPanel.updateUI();
    }

    public JPanel getCurrPanel() {
        return currPanel;
    }

    public void setTempPanels(Stack<JPanel> tempPanels) {
        this.tempPanels = tempPanels;
    }
}
