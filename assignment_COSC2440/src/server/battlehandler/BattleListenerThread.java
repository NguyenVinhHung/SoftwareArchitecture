package server.battlehandler;

import model.Player;
import model.pokemon.PokeInBattleInfo;
import server.Services;
import server.SocketCommunicator;
import view.map.MatchPanel;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 4/05/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class BattleListenerThread extends Thread {

    private SocketCommunicator communicator;
    private MatchPanel matchPanel;

    public BattleListenerThread(Socket socket, ObjectOutputStream objectOutputStream,
                              ObjectInputStream objectInputStream, Player p, MatchPanel m) {
        communicator = new SocketCommunicator(socket, objectOutputStream, objectInputStream, p);
        matchPanel = m;
    }

    public BattleListenerThread(SocketCommunicator sc, MatchPanel m) {
        communicator = sc;
        matchPanel = m;
    }

    @Override
    public void run() {
//        try {
        while (true) {
            int response = communicator.readInt();

            switch (response) {
                case Services.BATTLE_INITIALIZATION: {
                    initBattle();
                    break;
                }
            }
        }
//        } catch (IOException e) {
//
//        }
    }

    private void initBattle() {
        PokeInBattleInfo[] pokeInBattleInfos1 = (PokeInBattleInfo[])communicator.read();
        PokeInBattleInfo[] pokeInBattleInfos2 = (PokeInBattleInfo[])communicator.read();

        if(pokeInBattleInfos1 == null) {
            System.out.println("pokeInBattleInfos1 null");
        }
        if(pokeInBattleInfos2 == null) {
            System.out.println("pokeInBattleInfos2 null");
        }

        matchPanel.initPokeOnMap(pokeInBattleInfos1, pokeInBattleInfos2);
    }
}