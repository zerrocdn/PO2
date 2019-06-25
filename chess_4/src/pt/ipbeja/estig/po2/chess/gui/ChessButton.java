package pt.ipbeja.estig.po2.chess.gui;

import javafx.scene.control.Button;

public class ChessButton extends Button {

    private int line, col;

    public ChessButton(int line, int col) {
        this.line = line;
        this.col = col;

        this.setMinSize(50,50);
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }
}
