package pt.ipbeja.estig.po2.chess.model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Board extends Application {



    public static void main(String[] args) {
        Application.launch(args);
    }

    private final int BOARD_SIZE = 8;
    private Piece[][] boardArray;
    private Position[][]boardPositions;

    public List<Piece> whites;
    public List<Piece> blacks;

    private boolean turnWhite;
    private int play;
    private Piece selected;
    private List<Position> moves, takes;

    public Board() {
        this.boardArray = new Piece[BOARD_SIZE][BOARD_SIZE];
        this.boardPositions = new Position[BOARD_SIZE][BOARD_SIZE];
        this.whites = new ArrayList<Piece>();
        this.blacks = new ArrayList<Piece>();
        this.turnWhite = true;
        this.play = 0;
    }

    @Override
    public void start(Stage primaryStage) {
        createBoard();
        positionSelected(4, 0);
        positionSelected(4,1 );
        positionSelected(4,2 );
        positionSelected(4,1 );
        System.out.println(whites);
        System.out.println(blacks);
    }

    public void createBoard(){
        /*this.boardArray[0][4]= new King(this, 'R', new Position('e', 8));
        this.boardArray[7][4]= new King(this, 'R', new Position('e', 1));*/

        for (int line = 0; line < BOARD_SIZE; line++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                //System.out.print(this.boardArray[col][line]);
                System.out.print(this.boardPositions[col][line]=new Position((char)('a'+col), BOARD_SIZE - line));
            }
            System.out.println();
        }

        whites.add(new King(this, 'R', getPosition('e', 8), true));
        blacks.add(new King(this, 'R', getPosition('e', 7), false));
        blacks.add(new King(this, 'R', getPosition('e', 6), false));

    }

    public Position getPosition(char col, int line){
        return this.boardPositions[col-97][BOARD_SIZE - line];
    }

    /*public void updateBoard(Position position,int line, int col){
        //System.out.println(this.boardArray[line][col].getPosition());
        this.boardArray[line][col].setPosition(new Position('e', 1));
        //System.out.println(this.boardArray[line][col].getPosition());
    }

     */

    public void positionSelected(int col, int line){
        List<Piece> pieces = (turnWhite ? this.whites : this.blacks);

        if (play == 0){
            for (Piece p: pieces ) {
                if (p.getPosition().equals(this.boardPositions[col][line])){
                    System.out.println(p.getPieceName() + p.getPosition().toString());
                    this.selected = p;
                }
            }

            if (this.selected != null) {
                this.moves = this.selected.possibleMoves();
                System.out.println("\n" + moves.toString());

                this.takes = this.selected.possibleTakes(this.moves);
                System.out.println("Takes " + this.takes);
            }

            play++;
        }
        else if (play == 1){
            for (Position m :
                    moves) {
                if (m.equals(this.boardPositions[col][line])) {
                    this.selected.setPosition(this.boardPositions[col][line]);
                    play = 0;
                    turnWhite = !turnWhite;
                    System.out.println(this.whites.get(0).getPieceName() + "" + this.whites.get(0).getPosition());

                    for (Position t : this.takes ) {
                        if (m.equals(t)) {
                            takes.remove(t);
                            System.out.println(takes);
                            break;
                        }
                    }
                }
            }
        }

    }

}
