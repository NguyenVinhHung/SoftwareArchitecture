package server.waitingroomhandler;

import model.Player;
import model.pokemon.SelectedPokeInfo;
import server.Services;
import server.SocketCommunicator;
import view.panel.RoomView;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 24/04/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class WaitingRoomListenerThread extends Thread {

    private SocketCommunicator communicator;
    private RoomView roomView;

    public WaitingRoomListenerThread(RoomView room, Socket socket, ObjectOutputStream objectOutputStream,
                              ObjectInputStream objectInputStream, Player p) {
        roomView = room;
        communicator = new SocketCommunicator(socket, objectOutputStream, objectInputStream, p);
    }

    public WaitingRoomListenerThread(RoomView room, SocketCommunicator sc) {
        roomView = room;
        communicator = sc;
    }

    @Override
    public void run() {
//        try {
        int service;
        while (!communicator.isClosed()) {
            service = communicator.readInt();

            switch (service) {
                case Services.IN_ROOM_NOTIFY_SELECTED_POK: {
                    getSelectedPokes();
                    break;
                }
                case Services.IN_ROOM_STOP_WAITING: {
                    stopThread();
                    return;
                }
            }
        }
//        } catch (IOException e) {
//
//        }
    }

    private void getSelectedPokes() {
        SelectedPokeInfo[] team1 = (SelectedPokeInfo[])communicator.read();
        SelectedPokeInfo[] team2 = (SelectedPokeInfo[])communicator.read();

        roomView.reinitSelectedPokeView(team1, team2);
    }

    public void stopThread() {
        communicator.close();
    }
}
