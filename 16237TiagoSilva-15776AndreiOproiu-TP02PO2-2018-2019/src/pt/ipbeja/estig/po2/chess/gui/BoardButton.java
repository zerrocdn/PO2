package pt.ipbeja.estig.po2.chess.gui;

import javafx.scene.control.Button;

public class BoardButton extends Button {

    private int line;
    private int col;

    public BoardButton(int line, int col) {
        this.line = line;
        this.col = col;

        this.setPrefSize(50, 50);

        if ((line+col) % 2 == 0){
            this.setStyle("-fx-background-color: #a37e58");
        }
        else {
            this.setStyle("-fx-background-color: #fad6b1");
        }
    }

    public BoardButton(String text, int line, int col) {
        super(text);
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setAsMark(){
        this.setStyle("-fx-background-color: #cff296");
    }

    public void setAsTake(){
        this.setStyle("-fx-background-color: #f2a596");
    }

    public void resetbackground(){
        if ((line+col) % 2 == 0){
            this.setStyle("-fx-background-color: #a37e58");
        }
        else {
            this.setStyle("-fx-background-color: #fad6b1");
        }
    }
}
