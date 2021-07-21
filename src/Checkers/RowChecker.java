package Checkers;
import org.jetbrains.annotations.NotNull;
import Game.GameState;

public class RowChecker extends WinChecker {
    final private int rowIdx;
    RowChecker(int rowIdx, int gridSize, int streakSize) {
        super(gridSize, streakSize);
        this.rowIdx = rowIdx;
    }


    public int check(@NotNull GameState state) {
        for (int colIdx = 0; colIdx < this.gridSize; colIdx++) {
            int player = state.getBoardState(rowIdx, colIdx);
            int winner = this.streakManager.updateStreak(player);
            if (winner != -1)
                return winner;
        }
        return -1;
    }
}
