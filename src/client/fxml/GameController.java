package client.fxml;

import client.Main;
import client.Move;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {
    @FXML
    public Button startButton;
    @FXML
    public Text wonText;
    @FXML
    private GridPane spielerPane;
    @FXML
    private GridPane gegnerPane;


    public List<Integer> possibleShips = Arrays.asList(4, 3, 2, 1);
    public List<Integer> existingShips = new ArrayList<>();

    int numbPossibleShips = 20;
    int numbExistingShips = 0;
    int numbHitShips = 0;
    int numbDeadShips = 0;

    private Stage stage;
    private Parent root;

    public boolean started = false;
    public boolean won = false;

    public void changeTile(int x, int y, String color, GridPane grid) {
        x++;
        y++;
        for (Node n : grid.getChildren()) {
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x) {

                    n.setStyle("-fx-background-color: #" + color + ";");
                    ;
                }

        }
    }


    public boolean getTile(int x, int y, GridPane grid) {
        for (Node n : grid.getChildren()) {
            if (n instanceof Pane)
                if (GridPane.getColumnIndex(n) == y && GridPane.getRowIndex(n) == x) {

                    return n.getStyle().contains("-fx-background-color: #000;") || n.getStyle().contains("-fx-background-color: #F00;");

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
        stage = Main.primaryStage;

    }

    private void initOpponent() {
        for (Node n : gegnerPane.getChildren()) {
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (!started || won)
                        return;

                    int x = GridPane.getRowIndex(n);
                    int y = GridPane.getColumnIndex(n);


                    if (/*getTile(x - 1, y - 1, gegnerPane) ||
                                getTile(x - 1, y + 1, gegnerPane) ||
                                getTile(x + 1, y - 1, gegnerPane) ||
                                getTile(x + 1, y + 1, gegnerPane) ||*/
                            n.getStyle().contains("-fx-background-color: #F00") ||
                                    n.getStyle().contains("-fx-background-color: #AAA")
                    ) return;


                    Move m = Main.g.fire(new Move(x, y, false));
                    if (getTile(m.getX(), m.getY(), spielerPane)) {
                        changeTile(m.getX() - 1, m.getY() - 1, "F00", spielerPane);
                        numbDeadShips++;
                        if (numbDeadShips > 20) {
                            wonText.setText("You lost!");
                            goToMain();
                        }
                    }

                    if (m.wasLastShotHit()) {
                        changeTile(x - 1, y - 1, "F00", gegnerPane);
                        numbHitShips++;
                        if (numbHitShips > 20) {
                            goToMain();
                        }
                    } else
                        changeTile(x - 1, y - 1, "AAA", gegnerPane);
                }

                private void goToMain() {
                    won = true;
                    wonText.setVisible(true);
                    System.out.println("Sleeping");
                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(event -> new Runnable() {
                        @Override
                        public void run() {

                            System.out.println("Slept");
                            wonText.setVisible(false);
                            FXMLLoader loader = new FXMLLoader(getClass().
                                    getResource("Menu.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage = Main.primaryStage;

                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                        }
                    }.run());
                    new Thread(sleeper).start();


                }
            });
        }
    }


    private void initPlayer() {
        startButton.setDisable(true);
        for (Node n : spielerPane.getChildren()) {
            if (n instanceof Pane)

                n.setStyle("-fx-background-color: #FFF;");
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (started || won)
                        return;

                    int x = GridPane.getRowIndex(n);
                    int y = GridPane.getColumnIndex(n);


                    if (getTile(x - 1, y - 1, spielerPane) ||
                            getTile(x - 1, y + 1, spielerPane) ||
                            getTile(x + 1, y - 1, spielerPane) ||
                            getTile(x + 1, y + 1, spielerPane)
                    ) return;

                    int length = 1;

                    while (true) {
                        if (getTile(x + length, y, spielerPane)) {
                            ++length;
                            continue;
                        } else if (getTile(x - length, y, spielerPane)) {
                            ++length;
                            continue;
                        } else if (getTile(x, y + length, spielerPane)) {
                            ++length;
                            continue;
                        } else if (getTile(x, y - length, spielerPane)) {
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
                    if (n.getStyle().contains("-fx-background-color: #000;")) {
                        startButton.setDisable(true);
                        numbExistingShips--;
                    }else {
                        if (numbExistingShips + 1 > numbPossibleShips || length > 4)
                            return;
                        if (numbExistingShips + 2 > numbPossibleShips){
                            startButton.setDisable(false);
                        }
                        numbExistingShips++;
                    }


                    if (getTile(x, y, spielerPane))
                        changeTile(x - 1, y - 1, "FFF", spielerPane);
                    else
                        changeTile(x - 1, y - 1, "000", spielerPane);


                }
            });
        }
    }

    public void startGame(ActionEvent actionEvent) {

        started = true;
        startButton.setDisable(true);
    }

    public void openLeaderBoard(ActionEvent actionEvent) {
    }
}
