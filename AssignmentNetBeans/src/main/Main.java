package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Player;
import server.Services;
import server.SocketCommunicator;
import utility.DatabaseUtil;
import view.panel.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Stack;

/**
 *
 *
 */
public class Main extends JFrame {

    public static final int FRAME_W = 1024;
    public static final int FRAME_H = 768;

//    private static ApplicationContext serverCtx;
    private static Main frame;
    private static SocketCommunicator communicator;
    
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
//        serverCtx = new ClassPathXmlApplicationContext("client_beans.xml");
//        frame = (Main)serverCtx.getBean("main");

        frame = (Main)ClientSpring.getBean("main");

//        TimeCounter t = new TimeCounter();
//        t.startCounting();
    }

    public void init() {
        System.out.println("init");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Client closing");
                logout();
            }

//            @Override
//            public void windowClosed(WindowEvent e) {
//                System.out.println("Server closed");
//                logout();
//            }
        });

        add(currPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_W, FRAME_H);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void logout() {
        if(communicator != null) {
            communicator.sendRequestHeader(Services.LOGOUT);
            communicator.close();
        }
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
        pushPanel((RegisterView) ClientSpring.getBean("registerPanel"));
    }

    public void toLoginView() {
        pushPanel((LoginView) ClientSpring.getBean("loginPanel"));
    }

    public void toStartGame(Socket s, ObjectOutputStream output, ObjectInputStream input) {
        try {
           communicator = new SocketCommunicator(s, output, input, (Player)input.readObject());
        } catch(Exception ex) {
        }
        setCurrPanel(new GameStartView());
    }

    public void pushPanel(JPanel newPanel) {
//        tempPanels = currPanel;
        tempPanels.push(currPanel);
        setCurrPanel(newPanel);
    }

    public void clearPanelStack() {
        tempPanels.clear();
    }

    public void popPanel() {
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

    public static SocketCommunicator getCommunicator() {
        return communicator;
    }
}
