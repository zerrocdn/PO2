package pt.ipbeja.estig.po2.chess.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GUI board = new GUI();
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
