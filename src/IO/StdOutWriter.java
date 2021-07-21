package IO;
import java.io.PrintStream;

public class StdOutWriter implements OutputWriter{
    private PrintStream sop;
    public StdOutWriter(){
        this.sop = System.out;
    }

    public void print(String s) {
        this.sop.print(s);
    }

    public void println(String s) {
        this.sop.println(s);
    }
}
