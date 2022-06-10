package Client.fxml;

import Client.Game;
import Client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Scanner;

public class LeaderboardController {

    @FXML
    private ListView<String> nameListView;

    private Stage stage;

    @FXML
    public void onOkButtonClicked(ActionEvent event) {
        //String name = nameListView.getText();
        //nameListView.getItems().add(name);
    }
    public void Leaderboard(){

    }

    @FXML
    public void setStage() {
        this.stage = Main.stage;
        nameListView.getItems().addAll(Game.leaderboard);
    }
}
