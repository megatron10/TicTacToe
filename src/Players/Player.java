package Players;

import IO.InputReader;

public interface Player {
    int play();
    String getName();
    void showRecord();
    void registerWin();
    void registerLoss();
    void registerDraw();
    boolean setInputReader(InputReader inputReader);
}
