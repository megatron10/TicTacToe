package Players;

import IO.InputReader;

public class HumanPlayer implements Player{
    private String name;
    private int games = 0, wins = 0, losses = 0, draws = 0;
    private InputReader inputReader;

    public HumanPlayer(String name, InputReader inputReader) {
        this.name = name;
        this.inputReader = inputReader;
    }

    @Override
    public int play() {
//        System.out.print(name+"'s turn : ");
        return inputReader.getPos();
    }

    @Override
    public void registerWin() {
        games++;
        wins++;
    }

    @Override
    public void registerLoss() {
        games++;
        losses++;
    }

    @Override
    public void registerDraw() {
        games++;
        draws++;
    }

    @Override
    public void showRecord() {
        System.out.println("----------------------");
        System.out.println("Record for player " + name);
        System.out.println("Games played " + games);
        System.out.println("Games won " + wins);
        System.out.println("Games lost " + losses);
        System.out.println("Games drawn " + draws);
        System.out.println("----------------------");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
        return true;
    }
}