package Checkers;
import Game.GameState;
import org.jetbrains.annotations.NotNull;

public class ColChecker extends WinChecker{
    final private int colIdx;
    ColChecker(int colIdx, int gridSize, int streakSize) {
        super(gridSize, streakSize);
        this.colIdx = colIdx;
    }

    public int check(@NotNull GameState state) {
        int prev = -1;
        int streak = 0;
        for (int rowIdx = 0; rowIdx < this.gridSize; rowIdx++) {
            int player = state.getBoardState(rowIdx, colIdx);
            int winner = this.streakManager.updateStreak(player);
            if (winner != -1)
                return winner;
        }
        return -1;
    }
}
