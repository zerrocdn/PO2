package pt.ipbeja.po2.connectfour;

import pt.ipbeja.po2.connectfour.model.Cell;
import pt.ipbeja.po2.connectfour.model.HistoryVictory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface View {
    /**
     * This method makes a update in board, putting the respective images of the players int their coordenates.
     * @param cell is a type of possible player
     * @param line line clicked
     * @param col col clicked
     */
    void update(Cell cell, int line, int col);

    /**
     * This method clear the board, putting the GridPane again with all buttons empty
     */
    void clearBoard();

    /**
     * Informs the board that the respective player(Cell) has won
     * @param cell the player that has won the game
     */
    void playerWin(Cell cell, int score, ArrayList listBestPlays);

    /**
     * Input name of player
     * @return the name of player
     */
    String winAlert();

    /**
     * informs the board that the game has ended tied
     */
    void draw();

}
