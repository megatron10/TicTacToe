package Game;

import Checkers.GlobalChecker;
import Checkers.WinChecker;
import Players.*;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import IO.OutputWriter;

public class GameManager implements Callable<Boolean> {
    private static int runningGames = 0;
    private OutputWriter outputWriter;
    private enum Status {
        ONGOING, COMPLETED
    };

    private Status gameStatus;
    private GameState state;
    private WinChecker checker;
    private ArrayList<Player> players;
    private int gameId = -1;
    private int lastPlayerIdx = -1;

    public GameManager(ArrayList<Player> players, int gridSize, int streakSize, OutputWriter outputWriter) {
        this.players = players;
        this.gameId = ++runningGames;
        this.state = new GameState(gridSize, streakSize);
        this.checker = new GlobalChecker(gridSize, streakSize);
        this.gameStatus = Status.ONGOING;
        this.outputWriter = outputWriter;
    }

    @Override
    public Boolean call() {
        System.out.println("\n\n\n");
        manageOneMove();
        return manageOneMove();
    }

    public Boolean manageOneMove() {
        if (gameStatus == Status.COMPLETED)
            return Boolean.FALSE;

        //play one move
        playOneMove();

        //run checkers
        int winnerIdx = checker.check(state);

        //check for Win
        if (winnerIdx != -1) {
            state.print();

            for (int i = 0; i < players.size(); i++) {
                if (i == winnerIdx) {
                    players.get(i).registerWin();
                    outputWriter.println(players.get(i).getName() + " wins!");
                }
                else
                    players.get(i).registerLoss();
            }

            gameStatus = Status.COMPLETED;
        }

        //check for Draw
        else if (state.isBoardFull()) {
            state.print();

            for (int i = 0; i < players.size(); i++)
                players.get(i).registerDraw();

            outputWriter.println("Game drawn!");
            gameStatus = Status.COMPLETED;
        }

        return Boolean.valueOf(gameStatus != Status.COMPLETED);
    }

//    private void playOneMove() {
//        System.out.println("GAME #"+gameId);
//        state.print();
//        int nextPlayer = (lastPlayerIdx + 1) % players.size();
//        System.out.println("Symbol " + state.getCharForIdx(nextPlayer));
//
//        int pos = players.get(nextPlayer).play();
//        if (state.setBoardState(pos, nextPlayer)) {
//            lastPlayerIdx = nextPlayer;
//        }
//        else {
//            System.out.println("Illegal input, please retry");
//            playOneMove();
//        }
//    }

    private void playOneMove() {
        String msg = "GAME #" + gameId + "\n" + state.printString();
        int nextPlayer = (lastPlayerIdx + 1) % players.size();
        msg += ("Symbol " + state.getCharForIdx(nextPlayer) + "\n");
        msg += players.get(nextPlayer).getName() + "'s turn : ";
        try {
            this.outputWriter.print(msg);
            int pos = players.get(nextPlayer).play();
            if (state.setBoardState(pos, nextPlayer)) {
                lastPlayerIdx = nextPlayer;
            }
            else {
                this.outputWriter.print("Illegal Input, Please Retry");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
