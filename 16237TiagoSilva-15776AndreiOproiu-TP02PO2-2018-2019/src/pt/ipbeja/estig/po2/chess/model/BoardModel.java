package pt.ipbeja.estig.po2.chess.model;

import pt.ipbeja.estig.po2.chess.gui.View;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {

    private View view;

    private Piece[][] piecesBoard;
    public final int BOARD_SIZE = 8;

    private TypeWritter typeWritter;

    private boolean firstClick;
    private int plays;
    private Position start;

    private List<Position> moves;
    private List<Position> takes;
    private boolean turnWhite;


    public BoardModel(View view) {
        this.view = view;

        this.plays = 0;
        this.firstClick = false;
        this.turnWhite = true;

        this.moves = new ArrayList<>();
        this.takes = new ArrayList<>();

        this.typeWritter = new TypeWritter("plays.txt");

        createBoard();
    }

    private void createBoard(){

        this.piecesBoard = new Piece[BOARD_SIZE][BOARD_SIZE];

        addPicesToBoard();

    }

    public void resetBoard(){
        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.piecesBoard[line][col]=null;
            }
        }
    }

    private void printBoard(){
        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(this.piecesBoard[line][col] + " ");
            }
            System.out.println();
        }
    }

    private void addPicesToBoard(){
        List<String> rawData = this.typeWritter.readInitialPlaces();

        for (String l : rawData) {
            String[] spl = l.split(" ");

            for (int i = 0; i < spl.length; i++){
                boolean isWhite = (i == 0);

                String pRaw = spl[i];
                int line = pRaw.charAt(2) - 48;
                char col = pRaw.charAt(1);

                Position p = new Position(line, col);

                if (pRaw.charAt(0) == 'R'){
                    this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()] = new King(this, p, isWhite);
                }
                else if (pRaw.charAt(0) == 'B'){
                    this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()] = new Bishop(this, p, isWhite);
                }
                else if (pRaw.charAt(0) == 'T'){
                    this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()] = new Tower(this, p, isWhite);
                }
                else if (pRaw.charAt(0) == 'D'){
                    this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()] = new Queen(this, p, isWhite);
                }
            }
        }
    }

    public boolean isFree (int line, int col){

        if(line >= 0 && line <8 && col >= 0 && col < 8){
            return this.piecesBoard[line][col] == null;
        }else{
            return false;
        }
    }

    public boolean isEnemy (int line, int col, boolean color){

        if(line >= 0 && line <8 && col >= 0 && col < 8){

            return !this.isFree(line, col) && this.piecesBoard[line][col].isWhite != color;

        }else{

            return false;

        }

    }

    public void movePiece(Position start, Position end){
        Piece p = this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()];
        p.setPosition(end);

        this.piecesBoard[end.getTranslatedLine()][end.getTranslatedCol()] = p;
        this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()] = null;

        this.typeWritter.writePlay(p, end, this.plays/2 + 1);
        this.view.updateHistory(this.typeWritter.readPlays());
       // System.out.println( this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()].position.getCol()+ " " + this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()].position.getLine());
    }

    public void setPieceOnBoard(Piece piece, int line, char col){
        Position p = new Position(line, col);
        piece.setPosition(p);
        this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()] = piece;
    }

    public Piece getPiece(int line, int col){
        return this.piecesBoard[line][col];
    }

    public Piece getPiece(Position p){
        return this.piecesBoard[p.getTranslatedLine()][p.getTranslatedCol()];
    }

    public void placeSelected(int line, int col){
        if (!this.firstClick){
            if(this.piecesBoard[line][col] != null && this.piecesBoard[line][col].isWhite == this.turnWhite){
                Piece selected = this.piecesBoard[line][col];

                start = selected.getPosition();

                moves = selected.possibleMoves();
                takes = selected.possibleTakes();

                this.view.markPossibleMoves(moves);
                this.view.markPossibleTakes(takes);

                firstClick = true;
            }
        }else {
            Position end = new Position(line, col);
            if (moves.contains(end) || takes.contains(end) ){
                movePiece(start, end);
                plays++;
                this.turnWhite = !this.turnWhite;
            }

            this.view.resetButtonsBackground();
            firstClick = false;
        }
    }

}
