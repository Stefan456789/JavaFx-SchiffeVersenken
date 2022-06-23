package client;

import java.util.*;

public final class Game {

    public static final List<String> leaderboard = new ArrayList<>();
    public Connector c = Main.c;
    public Player player;
    public Player opponent;

    public void start(Player player, Player opponent){
        this.player = player;
        this.opponent = opponent;


        leaderboard.add(player.name);
    }

    public Player queue(String name) {
        c.send("queue;;" + name);
        return null;
    }

    public Move fire(Move m){

        return opponent.fire(m);
    }

    public Set<Player> recieveAllWaitingPlayers(){

        if (c == null || opponent == null || opponent.bot)
            return new HashSet<>();

        Set<Player> s = new HashSet<>();
        for (String name : c.send("getAllWaitingPlayers").split(";;")[1].split(";")){
            s.add(new Player(false, name));
        }
        return s;
    }
}
