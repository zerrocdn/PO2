package pt.ipbeja.estig.po2.chess.model;

import javafx.scene.layout.BorderPane;

public class Position {

    private char col;
    private int line;

    public Position(char col, int line) {
        this.col = col;
        this.line= line;
    }

    public char getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }

    public int getTranslatedCol(){
        return col-97;
    }
    public int getTranslatedLine(){ return 8 -line; }


    @Override
    public String toString() {
        return  (""+ " "+ col + line + " ");

    }
}
