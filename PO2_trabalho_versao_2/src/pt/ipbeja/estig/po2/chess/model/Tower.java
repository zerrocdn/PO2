package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {
    public Tower(Board board, Position position, boolean isWhite) {
        super(board, position, isWhite);
        pieceName = 'T';
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
        for (int line = myLine + 1; line <= this.board.BOARD_SIZE; line++){
            System.out.println(line);

            if (!friendlyPosition(this.board.boardPositions[myCol - 97][8 - line])) {
                moves.add(this.board.boardPositions[myCol-97][8 - line]);

            } else {
                break;
            }
        }
        //Bottom check
        for (int line = myLine - 1; line > 0; line--){
            System.out.println(line);

            if (!friendlyPosition(this.board.boardPositions[myCol - 97][8 - line])) {
                moves.add(this.board.boardPositions[myCol-97][8 - line]);

            } else {
                break;
            }
        }


        return moves;
    }

    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        List<Position> takes = new ArrayList<>();
        for (Position m : moves) {
            if (enemyPosition(m)){
                takes.add(m);
                break;
            }
        }
        return takes;
    }


}
