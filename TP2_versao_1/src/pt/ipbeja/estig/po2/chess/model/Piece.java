package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public abstract class Piece {

    private char pieceName;
    protected Board board;
    protected Position position;
    private boolean isWhite;

    public Piece(Board board, Character pieceName, Position position, boolean isWhite) {
        this.pieceName = pieceName;
        this.board = board;
        this.position = position;
        this.isWhite = isWhite;
    }

    public char getPieceName() {
        return pieceName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return " " + pieceName + " ";
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract List<Position> possibleMoves();
    public abstract List<Position> possibleTakes(List<Position> moves);
}
