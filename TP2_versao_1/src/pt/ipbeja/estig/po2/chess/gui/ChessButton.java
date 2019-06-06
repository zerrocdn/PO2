package pt.ipbeja.estig.po2.chess.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import pt.ipbeja.estig.po2.chess.model.Piece;

public class ChessButton extends Button {

    private int line, col;
    private Piece piece;
    private char name;

    public ChessButton(int line, int col, char name) {
        this.line = line;
        this.col = col;
        this.name = name;
        super.setText(piece.getPieceName()+"");
        super.setMinSize(50, 50);
        super.setAlignment(Pos.CENTER);
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

}
