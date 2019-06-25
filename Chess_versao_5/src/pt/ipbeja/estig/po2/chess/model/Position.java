package pt.ipbeja.estig.po2.chess.model;

public class Position {

    private char col;
    private int line;

    public Position(int line, char col) {
        this.col = col;
        this.line = line;
    }

    public char getCol() {
        return col;
    }

    public void setCol(char col) {
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Position(int line, int col) {

        this.line = 8 - line;
        this.col = (char) (col + 97);
    }


    public void setTranslatedCol(int col) {
        this.col = (char) (col + 97);
    }

    public int getTranslatedCol() {
        return col - 97;
    }

    public int getTranslatedLine() {
        return 8 - line;
    }

    public void setTranslatedLine(int line) {
        this.line = 8 - line;
    }

    @Override
    public String toString() {
        return " " + col + line;

    }
}
