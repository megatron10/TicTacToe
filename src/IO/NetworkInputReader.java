package IO;

import java.io.DataInputStream;

public class NetworkInputReader implements InputReader{
    private DataInputStream inStream;
    public NetworkInputReader(DataInputStream inStream){
        this.inStream = inStream;
    }

    public String getName() {
        return getString();
    }

    public String getString() {
        try {
            return this.inStream.readUTF();
        } catch (Exception e) {
            return "invalid input";
        }
    }

    public int getPos() {
        String position;
        try {
            position = this.inStream.readUTF();
            return Integer.parseInt(position);
        }
        catch (Exception e) {
            System.out.println("couldn't parese integer position");
            return -1;
        }
    }
}
