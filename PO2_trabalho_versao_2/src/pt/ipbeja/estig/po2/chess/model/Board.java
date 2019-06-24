package pt.ipbeja.estig.po2.chess.model;

import pt.ipbeja.estig.po2.chess.View;

import java.util.ArrayList;
import java.util.List;


public class Board {

    private View view;

    public int BOARD_SIZE = 8;
    public Position[][]boardPositions;
    public List<Piece> whitePieces, blackPieces;
    private Piece selectedPiece;
    private boolean whitesTurn = true;
    private boolean firstClick = true;
    private List<Position> moves, takes;


    public Board(View view) {
        this.view = view;
        this.boardPositions = new Position[BOARD_SIZE][BOARD_SIZE];
        this.whitePieces = new ArrayList<Piece>();
        this.blackPieces = new ArrayList<Piece>();
        this.moves = new ArrayList<>();
        this.takes = new ArrayList<>();
        createBoard();
    }

    public void createBoard(){

        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(this.boardPositions[col][line] = new Position((char)('a'+col),BOARD_SIZE-line));
            }
            System.out.println();
        }
        setInicialPositions();






    }

    public void setInicialPositions(){
        whitePieces.add(new King(this, this.boardPositions[4][7],true));
        blackPieces.add(new King(this, this.boardPositions[4][0],false));
        whitePieces.add(new Tower(this, this.boardPositions[7][7],true));
        whitePieces.add(new Tower(this, this.boardPositions[0][7],true));
        blackPieces.add(new Tower(this, this.boardPositions[7][0],false));
        blackPieces.add(new Tower(this, this.boardPositions[0][0],false));
        whitePieces.add(new Bishop(this, this.boardPositions[5][7],true));
        whitePieces.add(new Bishop(this, this.boardPositions[2][7],true));
        blackPieces.add(new Bishop(this, this.boardPositions[2][0],false));
        blackPieces.add(new Bishop(this, this.boardPositions[5][0],false));

    }

    public void placeSelected(int col, int line){
        if(this.firstClick){
            System.out.println(col + " " + line);
            List<Piece> pieces = (this.whitesTurn ? this.whitePieces : this.blackPieces);
            Position selectedPos = this.boardPositions[line][col];
            for (Piece p: pieces) {
                if(p.getPosition().equals(selectedPos)){
                    this.selectedPiece = p;
                    break;
                }
            }

            if(selectedPiece != null){
                this.moves = this.selectedPiece.possibleMoves();
                this.view.possibleMoves(moves);
                this.takes = this.selectedPiece.possibleTakes(moves);
                this.view.possibleTakes(takes);
                firstClick = false;
            }

        }else {
            Position selectedPos = this.boardPositions[line][col];
            for (Position pos: moves) {
                if(selectedPos.equals(pos)){
                    selectedPiece.setPosition(selectedPos);
                    System.out.println(selectedPiece.getPosition() + " " + this.whitePieces);


                    for (Position take : takes){
                        if (selectedPos.equals(take)){
                            List<Piece> enemies = (this.whitesTurn ? this.blackPieces : this.whitePieces);
                            for (Piece e : enemies) {
                                if (selectedPos.equals(e.getPosition())){
                                    enemies.remove(e);
                                    break;
                                }
                            }
                        }
                    }
                    this.view.resetBoard();
                    selectedPiece = null;
                    firstClick = true;
                    this.whitesTurn = !whitesTurn;
                }
            }
        }

    }

}
