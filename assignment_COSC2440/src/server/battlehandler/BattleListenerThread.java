package server.battlehandler;

import main.Main;
import model.Player;
import model.pokemon.PokeAttackResponse;
import model.pokemon.PokeInBattleInfo;
import model.pokemon.PokeInBattleRequest;
import model.pokemon.PokeMoveRequest;
import server.Services;
import server.SocketCommunicator;
import utility.Move;
import view.map.MatchPanel;
import view.panel.AttackAnimPanel;

import javax.swing.*;
import java.awt.*;
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
                    System.out.println("client - BATTLE_INITIALIZATION");
                    update();
                    break;
                }
                case Services.BATTLE_ATK: {
                    System.out.println("client - BATTLE_ATK");
                    attack();
                    break;
                }
                case Services.BATTLE_MOVE: {
                    System.out.println("client - BATTLE_MOVE");
                    move();
                    break;
                }
                case Services.BATTLE_END_TURN: {
                    System.out.println("client - BATTLE_END_TURN");
                    endTurn();
                    break;
                }
                case Services.BATTLE_FINISH: {
                    System.out.println("client - BATTLE_FINISH");
                    finishBattle();
                    break;
                }
            }
        }
//        } catch (IOException e) {
//
//        }
    }

//    private void initBattle() {
//        update();
//    }

    private void attack() {
        matchPanel.attackPokemon((PokeAttackResponse)communicator.read());
    }

    private void move() {
//        update();
//        String currPlayerName = (String)communicator.read();
        PokeMoveRequest response = (PokeMoveRequest)communicator.read();

        matchPanel.movePokemon(response);
    }

    private void update() {
        String currPlayerName = (String)communicator.read();
        PokeInBattleInfo[] pokeInBattleInfos1 = (PokeInBattleInfo[])communicator.read();
        PokeInBattleInfo[] pokeInBattleInfos2 = (PokeInBattleInfo[])communicator.read();
        matchPanel.initPokeOnMap(currPlayerName, pokeInBattleInfos1, pokeInBattleInfos2);

//        for(PokeInBattleInfo p : pokeInBattleInfos1) {
//            System.out.println(p);
//        }
//        for(PokeInBattleInfo p : pokeInBattleInfos2) {
//            System.out.println(p);
//        }
    }

    private void endTurn() {
        matchPanel.setCurrPlayerName((String)communicator.read());

    }

    private void finishBattle() {
        int winTeam = (Integer)communicator.read();
        matchPanel.finishBattle(winTeam);
    }
}