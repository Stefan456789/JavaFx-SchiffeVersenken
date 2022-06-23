package client.fxml;

import client.Connector;
import client.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    @FXML
    private GridPane spielerPane;
    @FXML
    private GridPane gegnerPane;


    public List<Integer> possibleShips = Arrays.asList(4,3,2,1);
    public List<Integer> existingShips = new ArrayList<>();

    int numbPossibleShips = 20;
    int numbExistingShips = 0;


    private Stage stage;
    private Parent root;

    public boolean started = false;
    public boolean myTurn = false;

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


    public boolean getTile(int x, int y) {
        for (Node n : spielerPane.getChildren()) {
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x) {
                    return n.getStyle().contains("-fx-background-color: #000;");

                }
        }
        return false;
    }

    @FXML
    public void setStage(Stage stage) {
        this.stage = Main.stage;
    }

/*
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

*/
    @FXML
    public void initialize() {

        initPlayer();
        initOpponent();


    }

    private void initOpponent(){
        for (Node n : gegnerPane.getChildren()) {
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
                n.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (!started || !myTurn)
                            return;

                        int x = GridPane.getRowIndex(n);
                        int y = GridPane.getColumnIndex(n);

                        Main.c.send("fire;x;y");



                    }
                });
        }
    }


    private void initPlayer(){
        for (Node n : spielerPane.getChildren()) {
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (started)
                        return;

                    int x = GridPane.getRowIndex(n);
                    int y = GridPane.getColumnIndex(n);



                    if (getTile(x - 1, y - 1) ||
                            getTile(x - 1, y + 1) ||
                            getTile(x + 1, y - 1) ||
                            getTile(x + 1, y + 1)
                    ) return;

                    int length = 1;

                    while (true) {
                        if (getTile(x + length, y)) {
                            ++length;
                            continue;
                        } else if (getTile(x - length, y)) {
                            ++length;
                            continue;
                        } else if (getTile(x, y + length)) {
                            ++length;
                            continue;
                        } else if (getTile(x, y - length)) {
                            ++length;
                            continue;
                        }

                        break;
                    }
/*
                    int allowedships = possibleShips.get(length-1);

                    for (int ship : existingShips){
                        if (ship == length) {
                            allowedships = allowedships - 1;
                            System.out.println("Trying to add "+ length + " long ship, there is one already");
                        }
                    }

                    if (allowedships <= 0) return;

                    System.out.println(length);

                    if (length == 1 && n.getStyle().contains("-fx-background-color: #000;"))
                        existingShips.remove(existingShips.indexOf(1));
                    else {
                        if (existingShips.contains(length - 1))
                            existingShips.remove(existingShips.indexOf(length - 1));
                        existingShips.add(length);
                    }



                    for (int ships : existingShips)
                        System.out.println("There is a " + ships + " long ship");
*/
                    if (n.getStyle().contains("-fx-background-color: #000;"))
                        numbExistingShips--;
                    else {
                        if (numbExistingShips + 1 > numbPossibleShips || length > 4)
                            return;
                        numbExistingShips++;
                    }


                    changeTile(x - 1, y - 1, !getTile(x, y), spielerPane);


                }
            });
        }
    }
}
