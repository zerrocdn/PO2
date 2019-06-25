package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'B';
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
