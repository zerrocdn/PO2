package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Board board, Position position, boolean isWhite) {
        super(board, position, isWhite);
        pieceName = 'Q';
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> moves = new ArrayList<>();
        char myCol = this.position.getCol();
        int myLine = this.position.getLine();
        //Left check
        for (char col = (char) (myCol + 1); col <= 'h'; col++) {
            if (!friendlyPosition(this.board.boardPositions[col - 97][this.board.BOARD_SIZE - myLine])) {
                moves.add(this.board.boardPositions[col - 97][this.board.BOARD_SIZE - myLine]);
            } else {
                break;
            }
        }
        //Right check
        for (char col = (char) (myCol - 1); col >= 'a'; col--) {
            if (!friendlyPosition(this.board.boardPositions[col - 97][this.board.BOARD_SIZE - myLine])) {
                moves.add(this.board.boardPositions[col - 97][this.board.BOARD_SIZE - myLine]);
            } else {
                break;
            }
        }
        //Upper check
        for (int line = myLine + 1; line <= this.board.BOARD_SIZE; line++) {
            System.out.println(line);

            if (!friendlyPosition(this.board.boardPositions[myCol - 97][8 - line])) {
                moves.add(this.board.boardPositions[myCol - 97][8 - line]);

            } else {
                break;
            }
        }
        //Bottom check
        for (int line = myLine - 1; line > 0; line--) {
            System.out.println(line);

            if (!friendlyPosition(this.board.boardPositions[myCol - 97][8 - line])) {
                moves.add(this.board.boardPositions[myCol - 97][8 - line]);
            } else {
                break;
            }
        }

        for (int line = myLine - 1; line > 0; line--) {
            for (int col = (char)(myCol - 97); col < 8; col++) {

            }

            if (!friendlyPosition(this.board.boardPositions[myCol - 97][8 - line])) {
                moves.add(this.board.boardPositions[myCol - 97][8 - line]);
            } else {
                break;
            }
        }

        return moves;
    }


    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        return null;
    }
}
