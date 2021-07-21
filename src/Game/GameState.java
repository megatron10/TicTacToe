package Game;

public class GameState {
    private int moves = 0;
    private int[][] board;
    private int gridSize, streakSize;
    final private char[] symbols = {'X', 'O', 'Q', 'W', 'E', 'R', 'T', 'Y'};


    GameState(int gridSize, int streakSize) {
        this.gridSize = gridSize;
        this.streakSize = streakSize;
        this.board = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                this.board[i][j] = -1;
    }

    private int getRow(int pos) {
        return gridSize - 1 - (pos - 1) / gridSize;
    }

    private int getCol(int pos) {
        return (pos - 1) % gridSize;
    }

    private int getPos(int row, int col) {
        return (gridSize - 1 - row) * gridSize + col + 1;
    }

    public int getBoardState(int row, int col) { return this.board[row][col]; }

    char getCharForIdx(int idx) { return symbols[idx]; }

    //tries to set board state, returns if state was set.
    boolean setBoardState(int pos, int playerIdx) {
        int lastMoveRow = getRow(pos);
        int lastMoveCol = getCol(pos);
        if (board[lastMoveRow][lastMoveCol] == -1) {
            board[lastMoveRow][lastMoveCol] = playerIdx;
            moves++;
            return true;
        }
        return false;
    }

    boolean isBoardFull() {
        return moves == gridSize * gridSize;
    }


    void print() {
        System.out.println("-------");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (j != 0)
                    System.out.print("|  ");

                if (board[i][j] == -1) {
                    int pos = getPos(i, j);
                    System.out.print(pos);
                    for (int k = 0; k < 3 - (new Integer(pos)).toString().length(); k++)
                        System.out.print(" ");
                }
                else System.out.print(symbols[board[i][j]] + "  ");
            }
            System.out.println();
        }
    }

    String printString() {
        String gen = "----------\n";
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (j != 0)
                    gen += "|  ";

                if (board[i][j] == -1) {
                    int pos = getPos(i, j);
                    gen += pos;
                    for (int k = 0; k < 3 - (new Integer(pos)).toString().length(); k++)
                        gen += " ";
                }
                else gen += (symbols[board[i][j]] + "  ");
            }
            gen += "\n";
        }
        return gen;
    }
}
