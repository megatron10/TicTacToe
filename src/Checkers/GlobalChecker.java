package Checkers;

import org.jetbrains.annotations.NotNull;
import Game.GameState;

import java.util.ArrayList;

public class GlobalChecker extends WinChecker{
    ArrayList<WinChecker> checkers;
    public GlobalChecker(int gridSize, int streakSize) {
        super(gridSize, streakSize);
        checkers = new ArrayList<>();

        for (int i = 0; i < gridSize; i++)
            checkers.add(new RowChecker(i, gridSize, streakSize));

        for (int i = 0; i < gridSize; i++)
            checkers.add(new ColChecker(i, gridSize, streakSize));

        for (int i = -(gridSize - streakSize); i <= gridSize - streakSize; i++)
            checkers.add(new ForwardDiagonalChecker(i, gridSize, streakSize));

        for (int i = gridSize - 1 + (gridSize - streakSize); i + 1 - streakSize >= 0; i--)
           checkers.add(new BackwardDiagonalChecker(i, gridSize, streakSize));
    }

    public int check(@NotNull GameState state) {
        for (WinChecker checker: checkers) {
            int winner = checker.check(state);
            if (winner != -1)
                return winner;
        }
        return -1;
    }
}
