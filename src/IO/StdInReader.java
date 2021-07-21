package IO;

import java.util.Scanner;
public class StdInReader implements InputReader{
    private Scanner sc;
    public StdInReader(){
        this.sc = new Scanner(System.in);
    }

    public String getString() {
        return this.sc.nextLine();
    }
    public String getName() { return this.getString(); }
    public int getPos() {
        int pos = this.sc.nextInt();
        this.sc.nextLine(); //eat new line
        return pos;
    }
}
