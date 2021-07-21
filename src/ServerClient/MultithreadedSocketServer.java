package ServerClient;

import Game.GameConfig;
import Game.GameManager;
import IO.InputReader;
import IO.NetworkOutputWriter;
import IO.OutputWriter;
import Players.HumanPlayer;
import IO.NetworkInputReader;
import Players.Player;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MultithreadedSocketServer {
    public static void main(String[] args) throws Exception {
        GameConfig config = new GameConfig(2, 3, 3);

        try{
            ServerSocket server = new ServerSocket(8888);
            int counter = 0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket serverClient = server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(serverClient, counter, config); //send  the request to a separate thread
                sct.start();
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}


class ServerClientThread extends Thread {
    private Socket serverClient;
    private int clientNo;
    private GameConfig config;

    ServerClientThread(Socket inSocket, int counter, GameConfig config){
        this.serverClient = inSocket;
        this.clientNo = counter;
        this.config = config;
    }
    public void run(){
        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            InputReader networkInputReader = new NetworkInputReader(inStream);
            OutputWriter networkOutputWriter = new NetworkOutputWriter(outStream);

            String clientMessage="", serverMessage="";
            HashMap<String, Player> playerHashMap = config.playerHashMap;

            ArrayList<Player> players = new ArrayList<>();

            for (int i = 1; i <= config.numPlayers; i++) {
                Player nextPlayer;
                String playerName = networkInputReader.getName();

                //TODO modify stream of existing player
                if (playerHashMap.containsKey(playerName)) {
                    nextPlayer = playerHashMap.get(playerName);
                    nextPlayer.setInputReader(networkInputReader);
                }
                else {
                    nextPlayer = new HumanPlayer(playerName, networkInputReader);
                    playerHashMap.put(playerName, nextPlayer);
                }
                players.add(nextPlayer);
            }
            System.out.println("Configuring game");

            GameManager manager = new GameManager(players, config.gridSize, config.streakSize, networkOutputWriter);

            while(manager.manageOneMove()) {
                System.out.println("running");
            }

            inStream.close();
            outStream.close();
            serverClient.close();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
