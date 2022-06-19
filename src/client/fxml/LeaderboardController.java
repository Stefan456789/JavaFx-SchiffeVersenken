package client.fxml;

import client.Game;
import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
