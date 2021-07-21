package Game;
import Players.Player;
import java.util.HashMap;

public class GameConfig {
    public int numPlayers = 2, gridSize = 3, streakSize = 3;
    public final HashMap<String, Player> playerHashMap = new HashMap<>();
    public GameConfig(int numPlayers, int gridSize, int streakSize) {
        this.numPlayers = numPlayers;
        this.gridSize = gridSize;
        this.streakSize = streakSize;
    }
}
