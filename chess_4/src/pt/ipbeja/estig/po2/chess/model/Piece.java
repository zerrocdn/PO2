package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public abstract class Piece {

    protected BoardModel boardModel;
    protected Position position;
    protected char pieceName;
    protected boolean isWhite;

    public Piece(BoardModel boardModel, Position position, boolean isWhite) {
        this.boardModel = boardModel;
        this.position = position;
        this.isWhite = isWhite;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) { this.position = position; }



    public char getPieceName() {
        return pieceName;
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public String toString() {
        return  ""+ pieceName;

    }

    public abstract List<Position> possibleMoves();
    public abstract List<Position> possibleTakes(List<Position> moves);

}
