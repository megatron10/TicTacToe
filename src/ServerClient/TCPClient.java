package ServerClient;

import java.net.*;
import java.io.*;
import IO.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        try{
            Socket socket = new Socket("127.0.0.1",8888);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

            InputReader networkInputReader = new NetworkInputReader(inStream);
            OutputWriter networkOutputWriter = new NetworkOutputWriter(outStream);

            InputReader stdInputReader = new StdInReader();
            OutputWriter stdOutWriter = new StdOutWriter();

            for (int i = 1; i <= 2; i++) {
                stdOutWriter.print("Enter name of player " + i + " : ");
                String playerName = stdInputReader.getName();
                networkOutputWriter.print(playerName);
            }

            while (true) {
                String prompt = networkInputReader.getString();
                stdOutWriter.print(prompt);
                if (prompt.endsWith("!\n"))
                    break;
                int pos = stdInputReader.getPos();
                networkOutputWriter.print((pos)+"");
            }

            outStream.close();
            inStream.close();
            socket.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}

