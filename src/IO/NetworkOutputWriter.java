package IO;

import java.io.DataOutputStream;
import java.io.IOException;

public class NetworkOutputWriter implements OutputWriter{
    private DataOutputStream dataOutputStream;
    public NetworkOutputWriter(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void print(String s) {
        try {
            this.dataOutputStream.writeUTF(s);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String s) {
        this.print(s + "\n");
    }
}
