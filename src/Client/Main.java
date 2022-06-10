package Client;

import Client.fxml.GameController;
import Client.fxml.LeaderboardController;
import Client.fxml.MenuController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    public static Stage stage;
    private static int port = 5555;
    private static String ip = "127.0.0.1";

    public static Connector c;

    @Override
    public void start(Stage primaryStage) {
        MenuController controller;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("fxml/Menu.fxml"));
            Parent root = (Parent) loader.load();

            stage = primaryStage;

            controller = loader.getController();
            controller.setStage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if (args.length == 1){
            ip = args[0];
        } else if (args.length == 2){
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            c = new Connector(new Socket(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}