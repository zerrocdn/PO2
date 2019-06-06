package pt.ipbeja.po2.connectfour.gui;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class CellButton extends Button {
    private static final Image PLAY_EMPTY = new Image("/resources/noplayer.png");
    private static final Image PLAY_1 = new Image("/resources/player1.png");
    private static final Image PLAY_2 = new Image("/resources/player2.png");

    private int col, line;

    private final ImageView imageView;

    /**
     * The class constructor, instanciate a new CellButton
     * @param line, selected button line
     * @param col, selected button col
     */
    public CellButton(int line, int col) {
        this.col = col;
        this.line = line;
        this.imageView = new ImageView(PLAY_EMPTY);
        this.setGraphic(imageView);
    }

    /**
     * Sets Tic image ('Player 1')
     */
    public void setTic() {
        this.imageView.setImage(PLAY_1);
    }

    /**
     * Sets Tac image ('Player 2')
     */
    public void setTac() {
        this.imageView.setImage(PLAY_2);
    }

    /**
     * Sets Empty image ('No Player')
     */
    public void setEmpty() {
        this.imageView.setImage(PLAY_EMPTY);
    }

    /**
     * Gets the selected button col
     * @return (int) the selected button col
     */
    public int getCol(){return this.col;}

    /**
     * Gets the selected button line
     * @return (int) the selected button line
     */
    public int getLine(){return this.line;}

}
