package pt.ipbeja.po2.tictactoe.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Sascha Geng, Diogo Pina Manique
 * @version 24-05-2018
 */

public class TicTacToeButton extends Button {

    private static final Image PLAY_EMPTY = new Image("/resources/noplayer.png");
    private static final Image PLAY_X = new Image("/resources/player1.png");
    private static final Image PLAY_O = new Image("/resources/player2.png");

    private final ImageView imageView;

    public TicTacToeButton() {
        this.imageView = new ImageView(PLAY_EMPTY);
        this.setGraphic(imageView);
    }

    /**
     * Sets Tic image ('X')
     */
    public void setTic() {
        this.imageView.setImage(PLAY_X);
    }

    /**
     * Sets Tac image ('O')
     */
    public void setTac() {
        this.imageView.setImage(PLAY_O);
    }
}