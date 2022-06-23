package client.fxml;

import client.Game;
import client.Main;
import client.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage = Main.stage;

    @FXML
    public Text textError;
    @FXML
    private TextField nameField;
    @FXML
    public ListView<Player> playerList;

    private Player p;
    private Player opponent;


    @FXML
    private void onGegnerSuchen(ActionEvent event) {
        if (nameField.getText().length() < 3) {

            System.out.println("error");
            textError.setVisible(true);

            return;
        }
        nameField.setDisable(true);
        textError.setVisible(false);

        p = new Player(false, nameField.getText());
        if (playerList.getSelectionModel().getSelectedIndices().isEmpty()){

            if (playerList.getItems().contains(p))
                return;
            playerList.getItems().add(p);
            opponent = Main.g.queue(nameField.getText());

            return;
        }

        if (opponent == null)
            Main.g.start(p, playerList.getSelectionModel().getSelectedItem());
        else
            Main.g.start(p, opponent);

        initGame();
    }

    @FXML
    private void onGegenComputer(ActionEvent event) {
        if (nameField.getText().length() < 3) {

            System.out.println("error");
            textError.setVisible(true);
            return;
        }
        Main.g.start(new Player(false, nameField.getText()) ,new Player(true));
        initGame();
    }

    @FXML
    public void initialize() {
        playerList.getItems().addAll(Main.g.recieveAllWaitingPlayers());
    }

    private void initGame() {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));

/*
            GameController controller = loader.getController();
            controller.setStage(stage);
            Parent root = (Parent) loader.load();
            stage.setScene(new Scene(root, ((AnchorPane) root).getPrefHeight(), ((AnchorPane) root).getPrefWidth()));
*/
            Parent root = (Parent) loader.load();
            GameController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(root, ((AnchorPane) root).getPrefHeight(), ((AnchorPane) root).getPrefWidth()));
            stage.setX(0);
            stage.setY(0);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void setStage() {
        this.stage = Main.stage;
    }
}
