package server.battlehandler;

import model.pokemon.PokeInBattleRequest;
import model.pokemon.PokeMoveRequest;
import server.Room;
import server.Services;
import server.SocketCommunicator;
import utility.Move;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 16/04/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class BattleThread implements Runnable {

    private SocketCommunicator communicator;
    private BattleServer server;

    public BattleThread(SocketCommunicator com, BattleServer s) {
        communicator = com;
        server = s;
    }

    @Override
    public void run() {
        System.out.println("BattleThread running");

        try {
            int service;

            while (!communicator.isClosed()) {
                service = communicator.readInt();

                System.out.println("BattleThread service: " + service);
                switch (service) {
                    case Services.BATTLE_INITIALIZATION: {
                        initBattle();
                        break;
                    }
                    case Services.BATTLE_ATK: {
                        attack();
                        break;
                    }
                    case Services.BATTLE_MOVE: {
                        move();
                        break;
                    }
                    case Services.BATTLE_END_TURN: {
                        endTurn();
                        break;
                    }
                }

                if(service>=Services.BATTLE_MOVE && service<=Services.BATTLE_ATK) {
                    server.calculateActionPoint();
                }
            }

//        } catch (EOFException eof) {
////            logout();
//            System.out.println("Close Socket Communicator due to EOFException");
//        } catch (SocketException eof) {
////            logout();
//            System.out.println("Close Socket Communicator due to SocketException");
        } catch (Exception ex) {
            System.out.println("OTHER EXCEPTION");
            ex.printStackTrace();
            return;
        }
    }

    private void initBattle() {
        server.initBattle();
    }

    private void attack() {
        System.out.println("Attacking");
        PokeInBattleRequest request = (PokeInBattleRequest)communicator.read();
        server.attack(request);
//        System.out.println(request);
    }

    private void move() {
        System.out.println("Moving");
        PokeMoveRequest rq = (PokeMoveRequest)communicator.read();

//        server.notifyPokeInBattleToPlayers(Services.BATTLE_MOVE, rq.getPokeModels1(), rq.getPokeModels2());
        server.notifyPlayers(Services.BATTLE_MOVE, rq);
    }

    private void endTurn() {
        server.endTurn();
    }

    public void stopThread() {
        communicator.close();
    }

    public SocketCommunicator getCommunicator() {
        return communicator;
    }
}
