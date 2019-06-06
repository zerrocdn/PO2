package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Board board, char pieceName, Position position, boolean isWhite) {
        super(board, pieceName, position, isWhite);
    }



    @Override
    public List<Position> possibleMoves() {
        List<Position> moves = new ArrayList<>();

        char myCol = this.position.getCol();
        int myLine = this.position.getLine();
        for (char col = (char) (myCol - 1); col <= myCol + 1 ; col++) {
            for (int line = myLine - 1; line <=  myLine + 1; line++) {
                if (col >= 'a' && col <= 'h' && line >= 1 && line <= 8 && (line != myLine || col != myCol)){
                    moves.add(this.board.getPosition(col, line));
                }
            }
        }

        return moves;
    }


    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        List<Position> takes = new ArrayList<>();
        List<Piece> pieces = (this.isWhite()? this.board.blacks : this.board.whites);
        for (Position m : moves) {
            for (Piece b : pieces) {
                if (m.equals(b.getPosition())){
                    takes.add(b.getPosition());
                }
            }
        }
        return takes;
    }
}
