package pt.ipbeja.po2.connectfour.model;

import pt.ipbeja.po2.connectfour.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class TestView implements View {

    /**
     * This method makes a update in board, putting the respective images of the players int their coordenates.
     * @param cell is a type of possible player
     * @param line line clicked
     * @param col col clicked
     */
    @Override
    public void update(Cell cell, int line, int col) {

    }

    /**
     * This method clear the board, putting the GridPane again with all buttons empty
     */
    @Override
    public void clearBoard() {

    }

    /**
     * Informs the board that the respective player(Cell) has won
     * @param cell the player that has won the game
     */
    @Override
    public void playerWin(Cell cell, int score, ArrayList listBestPlays) {

    }

    /**
     * Input name of player
     * @return the name of player
     */
    @Override
    public String winAlert() {
        return null;
    }

    /**
     * informs the board that the game has ended tied
     */
    @Override
    public void draw() {

    }
}
