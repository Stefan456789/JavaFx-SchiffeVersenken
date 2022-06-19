package client;

import client.fxml.GameController;

import java.util.ArrayList;
import java.util.List;

public final class Game {

    public static final List<String> leaderboard = new ArrayList<>();


    public static void start(Player player, GameController gameController){
        gameController.changeTile(0,0,true);
        gameController.changeTile(1,1,true);
        gameController.changeTile(2,2,true);
        gameController.changeTile(3,3,true);


        leaderboard.add(player.name);
    }
}
