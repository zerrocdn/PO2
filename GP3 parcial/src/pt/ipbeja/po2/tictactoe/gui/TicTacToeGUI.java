package pt.ipbeja.po2.tictactoe.gui;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.ipbeja.po2.tictactoe.View;
import pt.ipbeja.po2.tictactoe.model.Place;
import pt.ipbeja.po2.tictactoe.model.TicTacToeGame;

/**
 * @author Sascha Geng, Diogo Pina Manique
 * @version 24-05-2018
 */

public class TicTacToeGUI extends GridPane implements View {

    private final TicTacToeGame GAME_MODEL;

    public TicTacToeGUI() {
        this.GAME_MODEL = new TicTacToeGame(this);
        this.createBoard();
    }

    /**
     * Creates the game board with a grid of TicTacToeButtons
     */
    private void createBoard() {

        TicTacToeButtonHandler handler = new TicTacToeButtonHandler();

        for (int i = 0; i < this.GAME_MODEL.SIZE; i++) {
            for (int j = 0; j < this.GAME_MODEL.SIZE; j++) {
                TicTacToeButton btn = new TicTacToeButton();
                btn.setOnAction(handler);
                this.add(btn, j, i);
            }
        }
    }

    /**
     * Shows a message stating that the game ended in a draw.
     */
    @Override
    public void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Draw!");
        alert.show();
    }

    /**
     * Shows a message stating that a player has won the game.
     * @param player The winner
     */
    @Override
    public void playerWins(int player) {
        String s = player % 2 == 0 ? "X" : "O"; // Se 'player' for par, 's' toma o valor de "X", caso contrário "O"
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Player '" + s + "' won!");
        alert.show();
    }

    /**
     * Click handler for the TicTacToeButtons
     */
    class TicTacToeButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            TicTacToeButton button = (TicTacToeButton) event.getSource();

            // Métodos do GridPane para obter a linha e coluna do Node (neste caso o botão) na grelha
            int line = TicTacToeGUI.this.getRowIndex(button);
            int col = TicTacToeGUI.this.getColumnIndex(button);

            if (GAME_MODEL.isFree(line, col)) { // Perguntamos ao model se o lugar está livre

                GAME_MODEL.placeSelected(line, col); // Se sim, podemos jogar nesta posição
                Place place = GAME_MODEL.getPlace(line, col); // Obtemos o valor do Place nessa posição (ver enum Place)

                if(place == Place.X) {
                    button.setTic(); // Se o Place for X, pedimos ao botão para colocar a imagem X (Tic)
                }
                else {
                    button.setTac(); // Caso contrário, Place será O e pedimos ao botão para colocar a imagem O (Tac)
                }
            }

        }
    }
}














