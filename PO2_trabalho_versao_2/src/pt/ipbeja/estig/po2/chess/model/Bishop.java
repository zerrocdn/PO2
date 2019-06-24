package pt.ipbeja.estig.po2.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Board board, Position position, boolean isWhite) {
        super(board, position, isWhite);
        pieceName = 'B';
    }

    @Override
    public List<Position> possibleMoves() {
        List<Position> moves = new ArrayList<>();
        char myCol = this.position.getCol();
        int myLine = this.position.getLine();


        return moves;
    }

    @Override
    public List<Position> possibleTakes(List<Position> moves) {
        List<Position> takes = new ArrayList<>();
        List<Piece> pieces = (this.isWhite()? this.board.blackPieces : this.board.whitePieces);
        for (Position m : moves) {
            for (Piece b : pieces) {
                if (m.equals(b.getPosition())){
                    takes.add(b.getPosition());
                    System.out.println("TESTE");
                }
            }
        }
        return takes;
    }

}
