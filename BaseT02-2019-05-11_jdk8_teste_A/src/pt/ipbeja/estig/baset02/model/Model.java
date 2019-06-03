package pt.ipbeja.estig.baset02.model;

import pt.ipbeja.estig.baset02.gui.View;

import java.util.*;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
	public static final int N_LINES = 5;
	public static final int N_COLS = 7;
	private int turnCounter;

	private List<List<Integer>> pieces;
    public List<List<Integer>> numberOf10and20;
	private View view;

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

	public void positionSelected(Position position)
	{
		this.pieces.get(position.getLine()).set(position.getCol(), (turnCounter % 2 == 0 ? 10:20));
		this.view.updateText(this.pieces);
		this.turnCounter ++;
		n10And20();
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}

	public int alternateValue(){


	    if(turnCounter % 2 == 0){
	        return 10;
        }else {
	        return 20;
        }
    }

    public List<List<Integer>> n10And20(){

	    int tens = 0;
	    int twentyes = 0;
	    this.numberOf10and20 = new ArrayList<>();

        for (int i = 0; i < N_COLS; i++) {
            numberOf10and20.add(new ArrayList<>());
            numberOf10and20.get(i).add(0, 0);
            numberOf10and20.get(i).add(1, 0);
            tens = 0;
            twentyes = 0;
            for (int j = 0; j <N_LINES ; j++) {

                if(this.pieces.get(j).get(i) == 10){
                    tens++;
                    this.numberOf10and20.get(i).set(0,tens);

                }else if (this.pieces.get(j).get(i) == 20){
                    twentyes++;
                    this.numberOf10and20.get(i).set(1,twentyes);
                }


            }
        }

        for (int n = 0; n < numberOf10and20.size();  n++) {
            this.view.update10_20Label(n, numberOf10and20.get(n).get(0) ,numberOf10and20.get(n).get(1));
        }
        return numberOf10and20;
    }
}
