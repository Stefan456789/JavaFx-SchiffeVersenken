package client.fxml;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private void onGegnerSuchen(ActionEvent event) {
        try {
            if (nameField.getText().length() < 3) {
                System.out.println("error");
                textError.setVisible(true);
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
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
    private void onGegenComputer(ActionEvent event) {
        try {
            if (nameField.getText().length() < 3) {
                textError.setVisible(true);
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            GameController controller = loader.getController();
            controller.setStage(stage);
            Parent root = (Parent) loader.load();
            stage.setScene(new Scene(root, ((AnchorPane) root).getPrefHeight(), ((AnchorPane) root).getPrefWidth()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void setStage() {
        this.stage = Main.stage;
    }
}
