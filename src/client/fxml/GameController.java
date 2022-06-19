package client.fxml;

import client.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController {
    @FXML private GridPane spielerPane;
    @FXML private GridPane gegnerPane;



    private Stage stage;
    private Parent root;


    public void changeTile(int x, int y, boolean color, GridPane grid){
        x++;
        y++;
        for (Node n : grid.getChildren()){
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x){
                    if (color){

                        n.setStyle("-fx-background-color: #000;");
                    }
                    else
                        n.setStyle("-fx-background-color: #FFF;");
            }
        }
    }


    public boolean getTile(int x, int y){
        x++;
        y++;
        for (Node n : spielerPane.getChildren()){
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x){
                        return n.getStyle().contains("-fx-background-color: #000;");
                }
        }
        return false;
    }

    @FXML
    public void setStage(Stage stage) {
        this.stage = Main.stage;
    }

    @FXML
    public void initialize(){
        for (Node n : spielerPane.getChildren()){
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
                n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        changeTile(GridPane.getRowIndex(n)-1,GridPane.getColumnIndex(n)-1, !getTile(GridPane.getRowIndex(n)-1,GridPane.getColumnIndex(n)-1), spielerPane);
                    }
                });
        }




        for (Node n : gegnerPane.getChildren()){
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    changeTile(GridPane.getRowIndex(n)-1,GridPane.getColumnIndex(n)-1, !getTile(GridPane.getRowIndex(n)-1,GridPane.getColumnIndex(n)-1), gegnerPane);
                }
            });
        }
    }
}
