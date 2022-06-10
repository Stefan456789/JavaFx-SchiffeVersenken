package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable{

    public Worker(Socket socket) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter w = new PrintWriter(socket.getOutputStream(), true);
            Main.connectedClients.put(r.readLine(), w);





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
