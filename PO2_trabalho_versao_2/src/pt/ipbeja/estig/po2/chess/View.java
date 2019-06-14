package pt.ipbeja.estig.po2.chess;

import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;

public interface View {
    void possibleMoves(List<Position> moves);
    void possibleTakes(List<Position> takes);
    void resetBoard();
}
