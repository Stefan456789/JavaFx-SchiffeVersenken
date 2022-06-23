package client;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Player {
 String name;
    public final boolean bot;
    private int botSaidNo = 0;
    private Set<Move> pastMoves = new HashSet<>();


    public Player(boolean bot) {
        this.bot = bot;
        if (bot){
            name = "Bob";
        }
    }

    public Player(boolean bot, String name) {
        this.bot = bot;
    }

    public Move fire(Move m){
        if (bot){
            boolean wasHit = (int) (Math.random() * 5) == 0;
            if (!wasHit){
                botSaidNo++;
                if (botSaidNo >= 79)
                    wasHit = true;
            } else
                botSaidNo--;
            Move nextMove = null;
            while (nextMove == null || !pastMoves.add(nextMove))
                nextMove = new Move((int) (Math.random() * 10),(int) (Math.random() * 10), wasHit);
            return nextMove;
        }
        String response = Main.c.send("shoot;;" + m.x + ";" + m.y);
        return new Move(response.split(";;")[1].split(";")[0], response.split(";;")[1].split(";")[1], response.split(";;")[1].split(";")[2]);
    }

    @Override
    public String toString() {
        return name;
    }
}
