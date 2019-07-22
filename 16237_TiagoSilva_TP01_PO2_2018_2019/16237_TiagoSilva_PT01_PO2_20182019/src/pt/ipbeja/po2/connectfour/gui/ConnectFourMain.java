package pt.ipbeja.po2.connectfour.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class ConnectFourMain extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Create a new Board and a new Scene
     * We added to the scene a last board created
     * Final added to the Stage a scene
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Board board = new Board();
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}