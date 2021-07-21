package Checkers;

public class StreakManager {
    private int prev = -1;
    private int streak = 0;
    private int streakSize;

    StreakManager(int streakSize) {
        this.streakSize = streakSize;
    }

    //updates streak and returns winner
    int updateStreak(int player) {
        if (player == prev) {
            streak++;
            if (streak >= this.streakSize && player != -1)
                return player;
        } else {
            prev = player;
            streak = 1;
        }
        return -1;
    }
}