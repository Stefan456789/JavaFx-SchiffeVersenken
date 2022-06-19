package client;

import client.fxml.GameController;

import java.util.ArrayList;
import java.util.List;

public final class Game {

    public static final List<String> leaderboard = new ArrayList<>();


    public static void start(Player player, GameController gameController){


        leaderboard.add(player.name);
    }
}
