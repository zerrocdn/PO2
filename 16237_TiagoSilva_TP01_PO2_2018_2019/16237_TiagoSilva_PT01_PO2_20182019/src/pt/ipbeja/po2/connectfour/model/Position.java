package pt.ipbeja.po2.connectfour.model;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class Position {
    private final int row;
    private final int col;

    /**
     * Class constructor.
     * @param row   the row on the board
     * @param col   the column on the board
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the row number.
     * @return the row number
     */
    public int getLine() {
        return this.row;
    }

    /**
     * Gets the column number.
     * @return the column number
     */
    public int getCol() {
        return this.col;
    }
}
