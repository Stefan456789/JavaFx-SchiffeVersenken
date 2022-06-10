package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connector {
    private final Socket s;
    private final PrintWriter p;
    private final BufferedReader r;


    public Connector(Socket s) throws IOException {
        this.s = s;
        this.p = new PrintWriter(s.getOutputStream(), true);
        this.r = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public String recieve(){
        try {
            return r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void send(String msg){
        p.println(msg);
    }
}
