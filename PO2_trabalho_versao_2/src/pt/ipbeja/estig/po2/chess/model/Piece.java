package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public abstract class Piece {

    protected Board board;
    protected Position position;
    protected char pieceName;
    private boolean isWhite;

    public Piece(Board board, Position position, boolean isWhite) {
        this.board = board;
        this.position = position;
        this.isWhite = isWhite;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;


    }
    public boolean isWhite() {
        return isWhite;
    }

    public char getPieceName() {
        return pieceName;
    }

    public abstract List<Position> possibleMoves();
    public abstract List<Position> possibleTakes(List<Position> moves);
}
