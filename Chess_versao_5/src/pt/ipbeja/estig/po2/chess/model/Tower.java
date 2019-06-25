package pt.ipbeja.estig.po2.chess.model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {

    private List<Position> movesWithTakes = new ArrayList<>();

    public Tower(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'T';
    }

    @Override
    protected List<Position> possibleMoves() {

        List<Position> moves = new ArrayList<>();

        int myLine = this.position.getTranslatedLine();
        int myCol = this.position.getTranslatedCol();

        moves.addAll(verticalCheckMoves(myLine,myCol));
        moves.addAll(horizontalCheckMoves(myLine,myCol));

        return moves;
    }

    @Override
    protected List<Position> possibleTakes() {
        List<Position> takes = new ArrayList<>();


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

    private List<Position> verticalCheckMoves(int myLine, int myCol) {
        boolean up = true;
        boolean down = true;

        List<Position> moves = new ArrayList<>();

        for (int i = 1; i < this.boardModel.BOARD_SIZE; i++) {
            if (this.boardModel.isFree(myLine - i, myCol) && up) {
                moves.add(new Position(myLine - i, myCol));
                this.movesWithTakes.add(new Position(myLine - i, myCol));

            } else if (this.boardModel.isEnemy(myLine - i, myCol, isWhite) && up) {
                this.movesWithTakes.add(new Position(myLine - i, myCol));
                up = false;
            } else {
                up = false;
            }

            if (this.boardModel.isFree(myLine + i, myCol) && down) {
                moves.add(new Position(myLine + i, myCol));
                this.movesWithTakes.add(new Position(myLine + i, myCol));
            } else if (this.boardModel.isEnemy(myLine + i, myCol, isWhite) && down) {
                this.movesWithTakes.add(new Position(myLine + i, myCol));
                down = false;
            } else {
                down = false;
            }
        }

        return moves;
    }

    private List<Position> horizontalCheckMoves(int myLine, int myCol) {
        boolean right = true;
        boolean left = true;

        List<Position> moves = new ArrayList<>();

        for (int i = 1; i < this.boardModel.BOARD_SIZE; i++) {
            if (this.boardModel.isFree(myLine, myCol - i) && left) {

                moves.add(new Position(myLine, myCol - i));
                this.movesWithTakes.add(new Position(myLine, myCol - i));

            } else if (this.boardModel.isEnemy(myLine, myCol - i, isWhite) && left) {
                this.movesWithTakes.add(new Position(myLine, myCol - i));
                left = false;
            } else {
                left = false;
            }

            if (this.boardModel.isFree(myLine, myCol + i) && right) {

                moves.add(new Position(myLine, myCol + i));
                this.movesWithTakes.add(new Position(myLine, myCol + i));

            } else if (this.boardModel.isEnemy(myLine, myCol + i, isWhite) && right) {
                this.movesWithTakes.add(new Position(myLine, myCol + i));
                right = false;
            } else {
                right = false;
            }
        }

        return moves;
    }
}
