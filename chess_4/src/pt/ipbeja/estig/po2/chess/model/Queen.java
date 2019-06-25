package pt.ipbeja.estig.po2.chess.model;

import java.util.List;

public class Queen extends Piece{
    public Queen(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'Q';
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
