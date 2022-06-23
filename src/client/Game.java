package client;

import client.fxml.GameController;

import java.util.*;

public final class Game {

    public static final List<String> leaderboard = new ArrayList<>();
    public Connector c = Main.c;

    public static void start(Player player, GameController gameController){



        leaderboard.add(player.name);
    }

    public void fire(int x, int y){

    }

    public Set<Player> recieveAllWaitingPlayers(){

        Set<Player> s = new HashSet<>();
        c.send("getAllWaitingPlayers");
        for (String name : c.recieve().split(";;")[1].split(";")){
            s.add(new Player(false, name));
        }
        return s;
    }
}
