package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {


    public King(BoardModel boardModel, Position position, boolean isWhite) {
        super(boardModel, position, isWhite);
        pieceName = 'K';
    }

    @Override
    public List<Position> possibleMoves() {

        int myCol = this.position.getTranslatedCol();
        System.out.println(myCol+ " " + this.position.getCol());
        int myLine = this.position.getTranslatedLine();
        System.out.println(myLine+ " " + this.position.getLine());


        List<Position> moves = new ArrayList<>();
        for (int line = myLine - 1; line <= myLine + 1; line++) {
            for (int col = myCol - 1; col <= myCol + 1; col++) {
                if (line >= 7 && line <= 0 && col >= 7 && col <= 0){
                    moves.add(this.boardModel.boardPositions[8-line][myCol- col]);
                }
            }
        }

        return moves;
    }

    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        List<Position> takes = new ArrayList<>();
        List<Piece> pieces = (this.isWhite()? this.boardModel.blackPieces : this.boardModel.whitePieces);
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
