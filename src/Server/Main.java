package Server;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;

public class Main {
    private static ServerSocket s = null;
    private static int port = 5555;
    public static HashMap<String, PrintWriter> connectedClients = new HashMap();
    public static void main(String[] args){

        try {
            if (args.length == 1){
                port = Integer.parseInt(args[0]);
            }
            s = new ServerSocket(5555);

            while (true){
                new Thread(new Worker(s.accept())).start();
                System.out.println("New Player joined.");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
