package pt.ipbeja.estig.baset02.model;

import javafx.scene.control.Label;
import pt.ipbeja.estig.baset02.gui.View;

import java.util.*;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
    public static final int N_LINES = 5;
    public static final int N_COLS = 7;

    private List<List<Integer>> pieces;
    private View view;
    private int counter;

    /**
     * Creates board
     */
    public Model(View view) {
        this.view = view;
        this.resetBoard();

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


        if (this.counter % 2 == 0) {
            this.pieces.get(position.getLine()).set(position.getCol(), 10);
            this.view.updateText(this.pieces);

        } else {
            this.pieces.get(position.getLine()).set(position.getCol(), 20);
            this.view.updateText(this.pieces);
        }
        this.counter++;

        this.view.updateLabels(n10And20());

    }


    public int getValue(int line, int col) {
        return this.pieces.get(line).get(col);
    }

    public List<List<Integer>> n10And20() {

        List<List<Integer>> numbers = new ArrayList<>();

        int tens = 0, twenties = 0;

        for (int i = 0; i < N_COLS; i++) {
            numbers.add(new ArrayList<>());
            tens = 0;
            twenties = 0;
            for (int j = 0; j < N_LINES; j++) {

                if (this.pieces.get(j).get(i) == 10) {
                    tens++;
                } else if (this.pieces.get(j).get(i) == 20) {
                    twenties++;
                }
            }
            numbers.get(i).add(0, tens);
            numbers.get(i).add(1, twenties);

        }


        return numbers;
    }
}
