package pt.ipbeja.estig.po2.chess.model;

public class BoardModel {

    private Piece[][] piecesBoard;
    public final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        new BoardModel();
    }

    public BoardModel() {
        createBoard();
        printBoard();

//        System.out.println(this.piecesBoard[3][1].isWhiteToString() + "" + this.piecesBoard[3][1].pieceName + " " + this.piecesBoard[3][1].possibleMoves());
//        System.out.println(this.piecesBoard[3][1].isWhiteToString() + "" + this.piecesBoard[3][1].pieceName + " " + this.piecesBoard[3][1].possibleTakes());
//
//
//        System.out.println(this.piecesBoard[6][1].isWhiteToString() + "" + this.piecesBoard[6][1].pieceName + " " + this.piecesBoard[6][1].possibleMoves());
//        System.out.println(this.piecesBoard[6][1].isWhiteToString() + "" + this.piecesBoard[6][1].pieceName + " " + this.piecesBoard[6][1].possibleTakes());
//
//
//        System.out.println(this.piecesBoard[3][3].isWhiteToString() + "" + this.piecesBoard[3][3].pieceName + " " + this.piecesBoard[3][3].possibleMoves());
//        System.out.println(this.piecesBoard[3][3].isWhiteToString() + "" + this.piecesBoard[3][3].pieceName + " " + this.piecesBoard[3][3].possibleTakes());
        System.out.println();

        movePiece(this.piecesBoard[0][1].getPosition(), new Position(8, 'a'));

        printBoard();
    }

    private void createBoard(){

        this.piecesBoard = new Piece[BOARD_SIZE][BOARD_SIZE];

        this.piecesBoard[3][3]= new Bishop(this, new Position(3, 3), true);
        this.piecesBoard[7][6]= new King(this, new Position(7, 6), true);
        this.piecesBoard[6][1] = new Tower(this, new Position(6, 1), true);
        this.piecesBoard[1][1]= new Tower(this, new Position(1, 1), true);
        this.piecesBoard[0][1]= new King(this, new Position(0, 1), false);
        this.piecesBoard[1][5]= new Tower(this, new Position(5, 5), false);
        this.piecesBoard[3][1] = new Queen(this, new Position(3, 1), false);
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

       // System.out.println( this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()].position.getCol()+ " " + this.piecesBoard[start.getTranslatedLine()][start.getTranslatedCol()].position.getLine());
    }


}
