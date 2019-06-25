package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public abstract class Piece {

    protected BoardModel boardModel;
    protected Position position;
    protected boolean isWhite;
    protected char pieceName;

    public Piece(BoardModel boardModel, Position position, boolean isWhite) {
        this.boardModel = boardModel;
        this.position = position;
        this.isWhite = isWhite;
    }

    protected String isWhiteToString(){
        return isWhite ? "B":"P";
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return  isWhiteToString() + "  " + pieceName;

    }

    protected abstract List<Position> possibleMoves();
    protected abstract List<Position> possibleTakes();
}
