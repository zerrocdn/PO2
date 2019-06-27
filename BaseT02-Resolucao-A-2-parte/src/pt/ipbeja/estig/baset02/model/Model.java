package pt.ipbeja.estig.baset02.model;

import javafx.scene.control.Label;
import pt.ipbeja.estig.baset02.gui.View;

import java.util.*;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
    public static final int N_LINES = 8;
    public static final int N_COLS = 8;
    private List<List<Integer>> pieces;
    private List<Position> clickedPos;
    private View view;
    private int counter = 0;



    /**
     * Creates board
     */
    public Model(View view) {
        this.view = view;
        this.resetBoard();
        this.clickedPos = new ArrayList<>();
    }

    /**
     * Create and fills board
     */
    private void resetBoard() {

        this.pieces = new ArrayList<>();
        int i = 1;
        for (int line = 0; line < Model.N_LINES; line++) {
            this.pieces.add(new ArrayList<>());
            for (int col = 0; col < Model.N_COLS; col++) {
                this.pieces.get(line).add(i);
            }
        }
    }

    public void positionSelected(Position position) {

            int line = position.getLine();
            int col = position.getCol();

            this.pieces.get(position.getLine()).set(position.getCol(), (line + col) * 10);
            this.view.updateCell(position);

            this.clickedPos.add(position);

    }




    public int getValue(int line, int col) {

        return this.pieces.get(line).get(col);

    }



    public List<List<Position>> groups(){

        List<List<Position>> adjacentPieces = new ArrayList<>();

        for (Position p : this.clickedPos) {
            findAdjacent(p);
        }

        for (int i = 0; i < this.clickedPos.size(); i++) {
            findAdjacent(this.clickedPos.get(i));
        }



        return adjacentPieces;

    }

    public void findAdjacent(Position pos){

        int myLine = pos.getLine();
        int myCol = pos.getCol();

        List<Position> group = new ArrayList<>();

        for (int line = myLine - 1; line <= myLine + 1; line++) {
            for (int col = myCol - 1; col <= myCol + 1; col++) {
                if (col >= 0 && col <= N_COLS && line >= 0 && line <= N_LINES && (myLine != line || myCol != col)){
                    if (this.clickedPos.contains(new Position(line, col))) {
                        if (group.isEmpty()){
                            group.add(pos);
                        }

                        group.add(new Position(line, col));
                        //clickedPos.remove(new Position(line, col));
                    }
                }
            }
        }


        System.out.println(group);


    }



}
