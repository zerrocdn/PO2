package pt.ipbeja.estig.po2.chess.model;

import pt.ipbeja.estig.po2.chess.View;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {

    private View view;

    public int BOARD_SIZE = 8;
    public Position[][] boardPositions;
    public List<Piece> whitePieces, blackPieces;
    private List<Position> moves, takes;

    public BoardModel(View view) {
        this.view = view;
        this.boardPositions = new Position[BOARD_SIZE][BOARD_SIZE];
        this.whitePieces = new ArrayList<Piece>();
        this.blackPieces = new ArrayList<Piece>();
        createBoard();

    }

    public void createBoard() {

        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.boardPositions[line][col] = new Position((char) ('a' + col), BOARD_SIZE - line);
                System.out.print(this.boardPositions[line][col].toString());
            }
            System.out.println();
        }


        setInicialState();

        System.out.println(this.whitePieces.get(2).possibleMoves());


    }

    public void setInicialState() {
        whitePieces.add(new Tower(this, this.boardPositions[7][0], true));
        whitePieces.add(new Bishop(this, this.boardPositions[7][2], true));
        whitePieces.add(new King(this, this.boardPositions[7][4], true));
        whitePieces.add(new Queen(this, this.boardPositions[7][3], true));
        whitePieces.add(new Bishop(this, this.boardPositions[7][5], true));
        whitePieces.add(new Tower(this, this.boardPositions[7][7], true));
        blackPieces.add(new Tower(this, this.boardPositions[0][0], false));
        blackPieces.add(new Bishop(this, this.boardPositions[0][2], false));
        blackPieces.add(new Tower(this, this.boardPositions[0][7], false));
        blackPieces.add(new Bishop(this, this.boardPositions[0][5], false));
        blackPieces.add(new King(this, this.boardPositions[0][4], false));
        blackPieces.add(new Queen(this, this.boardPositions[0][3], false));
    }

    public void placeSelected(int line, int col){

    }


}
