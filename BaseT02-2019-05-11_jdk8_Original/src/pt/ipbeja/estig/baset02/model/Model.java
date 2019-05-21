package pt.ipbeja.estig.baset02.model;

import pt.ipbeja.estig.baset02.gui.View;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
	public static final int N_LINES = 5;
	public static final int N_COLS = 7;

	private List<List<Integer>> pieces;
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
		this.pieces.get(position.getLine()).set(position.getCol(), 0);
		this.view.updateText(this.pieces);
	}

	public void newGame(){
		this.resetBoard();
		this.view.clearBoard();
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}
}
