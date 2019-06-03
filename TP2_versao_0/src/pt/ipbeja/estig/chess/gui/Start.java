package pt.ipbeja.estig.chess.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    private Board board;

    public static void main(String[] args){Application.launch(args);}

    @Override
    public void start(Stage primaryStage) {
        this.board = new Board();
        Scene scene = new Scene(this.board);
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
