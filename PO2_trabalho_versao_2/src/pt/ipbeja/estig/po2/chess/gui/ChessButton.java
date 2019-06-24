package pt.ipbeja.estig.po2.chess.gui;



import javafx.scene.control.Button;

public class ChessButton extends Button {

    private int col, line;

    public ChessButton(int col, int line) {
        this.col = col;
        this.line = line;
        this.setMinSize(50,50);
    }

    public int getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }
}
