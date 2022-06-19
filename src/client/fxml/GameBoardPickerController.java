package client.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardPickerController {
    @FXML private GridPane spielerPane;



    private Stage stage;
    private Parent root;


    public void changeTile(int x, int y, boolean color){
        x++;
        y++;
        for (Node n : spielerPane.getChildren()){
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x){
                    if (color)
                        n.setStyle("-fx-background-color: #000;");
                    else
                        n.setStyle("-fx-background-color: #FFF;");

            }
        }
    }


    public void getTile(int x, int y, boolean color){
        x++;
        y++;
        for (Node n : spielerPane.getChildren()){
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x){
                    if (color)
                        n.getStyle().contains("-fx-background-color: #000;");
                    else
                        n.setStyle("-fx-background-color: #FFF;");
                }
        }
    }

    @FXML
    private void onGo(ActionEvent event){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) loader.load();
            GameController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(root, ((AnchorPane) root).getPrefHeight(), ((AnchorPane) root).getPrefWidth()));
            stage.setX(0);
            stage.setY(0);

            stage.setWidth(((AnchorPane) root).getPrefWidth());
            stage.setHeight(((AnchorPane) root).getPrefHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
