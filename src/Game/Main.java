package Game;

import IO.InputReader;
import IO.StdInReader;
import IO.StdOutWriter;
import Players.*;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InputReader stdInputReader = new StdInReader();
        HashMap<String, Player> playerHashMap = new HashMap<>();
        int games = 3, numPlayers = 2, gridSize = 3, streakSize = 3;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        LinkedList<GameManager> managers = new LinkedList<>();
        LinkedList<Future<Boolean>> futures = new LinkedList<>();

        for (int g = 1; g <= games; g++) {
            System.out.println("Enter usernames for game " + g);
            ArrayList<Player> players = new ArrayList<>();

            for (int i = 1; i <= numPlayers; i++) {
                System.out.print("Enter username of player " + i + ": ");
                String playerName = stdInputReader.getName();
                Player nextPlayer = null;

                if (playerHashMap.containsKey(playerName))
                    nextPlayer = playerHashMap.get(playerName);
                else {
                    nextPlayer = new HumanPlayer(playerName, stdInputReader);
                    playerHashMap.put(playerName, nextPlayer);
                }

                players.add(nextPlayer);
            }
            System.out.println("Configuring game " + g);
            managers.add(new GameManager(players, gridSize, streakSize, new StdOutWriter()));
            futures.add(null);
            System.out.println("\n");
        }

        while (!managers.isEmpty()) {
            ListIterator<GameManager> managerItr = managers.listIterator();
            ListIterator<Future<Boolean>> futuresItr = futures.listIterator();
            while (managerItr.hasNext()) {
                GameManager manager = managerItr.next();
                Future<Boolean> future = futuresItr.next();

                //submit task for first move
                if (future == null) {
                    futuresItr.set(executorService.submit(manager));
                }

                //check if old submitted task is completed
                else if (future.isDone()) {
                    //game has terminated
                    if (future.get() == false) {
                        managerItr.remove();
                        futuresItr.remove();
                    }

                    //game is on, submit task for next move
                    else {
                        futuresItr.set(executorService.submit(manager));
                    }
                }
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        System.out.println("\n\n\nPrinting Player records");
        playerHashMap.forEach((k, v) -> v.showRecord());
    }
}
