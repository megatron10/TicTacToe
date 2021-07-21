package Checkers;
import org.jetbrains.annotations.NotNull;
import Game.GameState;

public abstract class WinChecker {
    protected int gridSize, streakSize;
    protected StreakManager streakManager;
    abstract public int check(@NotNull GameState state);

    WinChecker(int gridSize, int streakSize) {
        this.gridSize = gridSize;
        this.streakSize = streakSize;
        this.streakManager = new StreakManager(streakSize);
    }
}
