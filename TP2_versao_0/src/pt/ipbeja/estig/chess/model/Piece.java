package pt.ipbeja.estig.chess.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import pt.ipbeja.estig.chess.gui.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece extends Button{

    private Board board;
    private List<ArrayList> position;
    public Piece() {
        super();
        super.setMaxSize(50,50);
        super.setAlignment(Pos.CENTER);
        super.setShape(new Circle(1));

    }

    public List<ArrayList> possibleMoves(){
       this.position = new ArrayList<>();
       return position;
    }

    public List<ArrayList> possibleTakes(){
        this.position = new ArrayList<>();
        return position;
    }

}
