package pt.ipbeja.po2.connectfour.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import pt.ipbeja.po2.connectfour.View;
import pt.ipbeja.po2.connectfour.model.Cell;
import pt.ipbeja.po2.connectfour.model.ConnectFourModel;
import pt.ipbeja.po2.connectfour.model.Position;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class Board extends BorderPane implements View {

    private ConnectFourModel model;
    private GridPane gridPane;
    private CellButton[][] buttons;

    /**
     * The class constructor
     * Initialize elements and is the initiator of game
     */
    public Board() {
        this.gridPane = new GridPane();
        this.model = new ConnectFourModel(this);
        this.menu();
        this.createBoard();
    }

    /**
     * Create a menu where it is possible to ply again and cancel the last move
     */
    public void menu(){
        Menu menu = new Menu("Menu");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        MenuItem newGame = new MenuItem("New Game");
        MenuItem undo = new MenuItem("Undo");
        menu.getItems().addAll(newGame, undo);

        newGame.setOnAction(e -> {
            model.newGame();
        });
        undo.setOnAction(e -> {
            Position lastTurnPosition = model.undoLastTurn();
            if (lastTurnPosition != null) {
                getButtonFromPosition(lastTurnPosition).setEmpty();
            }
        });

        this.setTop(menuBar);
    }

    /**
     * Obtains a button in a given position.
     * @param p the position
     * @return the button
     */
    private CellButton getButtonFromPosition(Position p) {
        ObservableList<Node> children = this.gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == p.getLine() &&
                    GridPane.getColumnIndex(node) == p.getCol()
            ) {
                return (CellButton) node;
            }
        }
        return null;
    }

    /**
     * Create a board with empty buttons and add the GridPane to the BoardPane
     */
    private void createBoard() {

        ButtonHandler handler = new ButtonHandler();

        this.buttons = new CellButton[this.model.SIZE_LINE][this.model.SIZE_COL];

        for (int line = 0; line < this.model.SIZE_LINE; line++) {
            for (int col = 0; col < this.model.SIZE_COL; col++) {
                CellButton button = new CellButton(line, col);
                button.setOnAction(handler);
                this.gridPane.add(button, col, line);
                this.buttons[line][col] = button;
            }
        }

        this.setCenter(this.gridPane);
    }

    /**
     * Make a update in board, putting the respective images of the players int their coordenates.
     * @param cell the player slected
     * @param line is one of the coordenates of button select
     * @param col is another of the coordenates of button select
     */
    @Override
    public void update(Cell cell, int line, int col) {

        CellButton button = buttons[line][col];
        if (cell == Cell.PLAYER1) {
            button.setTic();
        } else {
            button.setTac();
        }

    }

    /**
     * Clear the board, putting the GridPane again with all buttons empty
     */
    @Override
    public void clearBoard() {
        for (Node node : this.gridPane.getChildren()) {
            CellButton button = (CellButton) node;
            button.setEmpty();
        }
    }

    /**
     * Informs the board through a alert that the respective player(Cell) has won
     * @param cell the player that has won the game
     */
    @Override
    public void playerWin(Cell cell, int score, ArrayList listBestPlays) {

        TextInputDialog playerWin =  new TextInputDialog();
        playerWin.getDefaultValue();

        System.out.println(listBestPlays.size() + "LEGYGYGFYU");

        Alert alert = new Alert(Alert.AlertType.INFORMATION, cell.toString()
                + " WON\n" + "Score: " + score + "\n"
                + "1º " + listBestPlays.get(0).toString() + "\n"
                + "2º " + (listBestPlays.size() > 1 ? listBestPlays.get(1).toString() : "") + "\n"
                + "3º " + (listBestPlays.size() > 2 ? listBestPlays.get(2).toString() : "") + "\n");
        alert.show();
    }

    /**
     * Informs the board through a alert that the game has ended tied
     */
    @Override
    public void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "DRAW");
        alert.show();
    }

    /**
     * Ask the user to input the name and returns the same name
     * @return the input text
     */
    public String winAlert(){
        TextInputDialog dialog = new TextInputDialog("Your Name");
        dialog.setTitle("Winner Winner Chicken Dinner");
        dialog.setHeaderText("You Won the Game");
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();

        String answer = result.get();

        while (answer.length() > 8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Input Text");
            alert.setHeaderText("Name to long");
            alert.setContentText("Your name can not be 8 or more characters long");

            alert.showAndWait();
            answer = dialog.showAndWait().get();
        }
        return answer;
    }

    /**
     * Action that button have in order to be clicked
     */
    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            CellButton button = (CellButton) event.getSource();
            int line = button.getLine();
            int col = button.getCol();

            model.placeSelected(line, col);
        }
    }
}
