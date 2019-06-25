package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public class Tower extends Piece{
    public Tower(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'T';
    }

    @Override
    public List<Position> possibleMoves() {
        return null;
    }

    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        return null;
    }
}
