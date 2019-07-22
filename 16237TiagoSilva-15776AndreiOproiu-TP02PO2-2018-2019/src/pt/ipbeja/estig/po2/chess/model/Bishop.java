package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private List<Position> movesWithTakes = new ArrayList<>();
    
    public Bishop(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'B';
    }

    public Bishop(BoardModel boardModel, boolean isWhite) {
        super(boardModel, isWhite);
        pieceName = 'B';
    }

    @Override
    protected List<Position> possibleMoves() {

        List<Position> moves = new ArrayList<>();

        this.movesWithTakes.clear();

        int myLine = this.position.getTranslatedLine();
        int myCol = this.position.getTranslatedCol();

        moves.addAll(leftDiagonalCheckMoves(myLine,myCol));
        moves.addAll(pairDiagonalCheckMoves(myLine,myCol));


        return moves;
    }

    @Override
    protected List<Position> possibleTakes() {
        List<Position> takes = new ArrayList<>();

        if (this.movesWithTakes.isEmpty()){
            possibleMoves();
        }

        boolean up = true;

        int myLine = this.position.getTranslatedLine();
        int myCol = this.position.getTranslatedCol();

        for (Position pos : movesWithTakes) {
            if (this.boardModel.isEnemy(pos.getTranslatedLine(), pos.getTranslatedCol(), isWhite)) {
                takes.add(pos);
            }
        }

        return takes;
    }

    private List<Position> pairDiagonalCheckMoves(int myLine, int myCol) {
        boolean up = true;
        boolean down = true;

        List<Position> moves = new ArrayList<>();

        for (int i = 1; i < this.boardModel.BOARD_SIZE; i++) {
            if (this.boardModel.isFree(myLine - i, myCol + i) && up) {
                moves.add(new Position(myLine - i, myCol + i));
                this.movesWithTakes.add(new Position(myLine - i, myCol + i));

            } else if (this.boardModel.isEnemy(myLine - i, myCol + i, isWhite)  && up) {
                this.movesWithTakes.add(new Position(myLine - i, myCol + i));
                up = false;
            } else {
                up = false;
            }

            if (this.boardModel.isFree(myLine + i, myCol - i) && down) {
                moves.add(new Position(myLine + i, myCol - i));
                this.movesWithTakes.add(new Position(myLine + i, myCol - i));
            } else if (this.boardModel.isEnemy(myLine + i, myCol - i, isWhite) && down) {
                this.movesWithTakes.add(new Position(myLine + i, myCol - i));
                down = false;
            } else {
                down = false;
            }
        }

        return moves;
    }

    private List<Position> leftDiagonalCheckMoves(int myLine, int myCol) {
        boolean right = true;
        boolean left = true;

        List<Position> moves = new ArrayList<>();

        for (int i = 1; i < this.boardModel.BOARD_SIZE; i++) {
            if (this.boardModel.isFree(myLine-i, myCol - i) && left) {

                moves.add(new Position(myLine - i, myCol - i));
                this.movesWithTakes.add(new Position(myLine - i, myCol - i));

            } else if (this.boardModel.isEnemy(myLine - i, myCol - i, isWhite) && left) {
                this.movesWithTakes.add(new Position(myLine - i, myCol - i));
                left = false;
            } else {
                left = false;
            }

            if (this.boardModel.isFree(myLine + i, myCol + i) && right) {

                moves.add(new Position(myLine + i, myCol + i));
                this.movesWithTakes.add(new Position(myLine + i, myCol + i));

            } else if (this.boardModel.isEnemy(myLine + i, myCol + i, isWhite) && right) {
                this.movesWithTakes.add(new Position(myLine + i, myCol + i));
                right = false;
            } else {
                right = false;
            }
        }

        return moves;
    }
}
