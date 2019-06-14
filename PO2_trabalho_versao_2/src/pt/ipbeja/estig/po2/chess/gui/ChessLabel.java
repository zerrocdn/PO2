package pt.ipbeja.estig.po2.chess.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ChessLabel extends Label {

    public ChessLabel(String string) {
        super(string);
        this.setAlignment(Pos.CENTER);
        this.setMinSize(50,50);
        this.setMaxSize(50,50);
    }
}
