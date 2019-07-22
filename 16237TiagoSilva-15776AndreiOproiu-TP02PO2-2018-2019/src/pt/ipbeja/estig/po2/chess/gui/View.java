package pt.ipbeja.estig.po2.chess.gui;

import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;

public interface View {

    void markPossibleMoves(List<Position> moves);
    void markPossibleTakes(List<Position> takes);

    void resetButtonsBackground();

    void updateHistory(String text);
}


