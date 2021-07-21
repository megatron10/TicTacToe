package Checkers;
import Game.GameState;
import org.jetbrains.annotations.NotNull;

public class ForwardDiagonalChecker extends WinChecker{
    final private int colIdx; // id of the column on the top row of \ shaped diagonal
    ForwardDiagonalChecker(int colIdx, int gridSize, int streakSize) {
        super(gridSize, streakSize);
        this.colIdx = colIdx;
    }

    public int check(@NotNull GameState state) {
        for (int rowIdx = Math.max(0, -colIdx); colIdx + rowIdx < this.gridSize && rowIdx < this.gridSize; rowIdx++) {
            int player = state.getBoardState(rowIdx, colIdx + rowIdx);
            int winner = this.streakManager.updateStreak(player);
            if (winner != -1)
                return winner;
        }
        return -1;
    }
}
