package pt.ipbeja.estig.po2.chess.model;

public class BoardModel {

    private Piece[][] piecesBoard;
    public final int BOARD_SIZE = 8;

    private TypeWritter typeWritter;

    public static void main(String[] args) {
        new BoardModel();
    }

    public BoardModel() {
        createBoard();
        this.typeWritter = new TypeWritter("plays.txt");

        movePiece(this.piecesBoard[0][0].getPosition(), new Position(2, 2));
        movePiece(this.piecesBoard[7][7].getPosition(), new Position(7, 6));
    }

    private void createBoard(){

        this.piecesBoard = new Piece[BOARD_SIZE][BOARD_SIZE];

        this.piecesBoard[0][0] = new Bishop(this, new Position(0, 0), true);
        this.piecesBoard[7][7] = new King(this, new Position(7, 7), false);

    }

    private void printBoard(){
        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(this.piecesBoard[line][col] + " ");
            }
            System.out.println();
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

        this.typeWritter.writePlay(p, end, 1);
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



}
