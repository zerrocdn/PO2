package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'R';
    }

    public King(BoardModel boardModel, boolean isWhite) {
        super(boardModel, isWhite);
        pieceName = 'R';
    }

    @Override
    protected List<Position> possibleMoves() {
        List<Position> moves = new ArrayList<Position>();

        int myLine = this.position.getTranslatedLine();
        int myCol = this.position.getTranslatedCol();

        for (int line = myLine - 1; line <= myLine + 1; line++) {
            for (int col = myCol - 1; col <= myCol + 1; col++) {
                if(this.boardModel.isFree(line, col)){
                    moves.add(new Position(line, col));
                }
            }
        }

        return moves;
    }

    @Override
    protected List<Position> possibleTakes() {
        List<Position> takes = new ArrayList<Position>();

        int myLine = this.position.getTranslatedLine();
        int myCol = this.position.getTranslatedCol();

        for (int line = myLine - 1; line <= myLine + 1; line++) {
            for (int col = myCol - 1; col <= myCol + 1; col++) {
                if(this.boardModel.isEnemy(line, col,isWhite)){
                    takes.add(new Position(line, col));
                }
            }
        }

        return takes;
    }
}
