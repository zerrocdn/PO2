package pt.ipbeja.po2.tictactoe.gui;
/**
 * Created by DiogoPM on 14/03/2018.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeStart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TicTacToeGUI board = new TicTacToeGUI();
        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
