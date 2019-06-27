package pt.ipbeja.estig.baset02.model;

import pt.ipbeja.estig.baset02.gui.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
	public static final int N_LINES = 5;
	public static final int N_COLS = 7;

	private List<List<Integer>> pieces;
	private View view;


	private int playCounter = 0;

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

		int value = playCounter++ % 2 == 0 ? 10 : 20;

		this.pieces.get(position.getLine()).set(position.getCol(), value);
		this.view.updateText(this.pieces);

		this.view.updateColumnNumbers(n10And20());
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}


	public List<List<Integer>> n10And20() {
		List<List<Integer>> result = new ArrayList<>(N_COLS);

		for (int col = 0; col < N_COLS; col++) { // para cada coluna..
			List<Integer> nums = n10And20InColumn(col); // invocamos o método que conta os 10 e 20 naquela coluna
			result.add(nums); // e adicionamos o resultado à lista de listas
		}

		return result;
	}

	private List<Integer> n10And20InColumn(int col) {
		int tenCount = 0;
		int twentyCount = 0;

		for (List<Integer> line : pieces) {
			int value = line.get(col); // Temos agora o valor naquela linha e coluna
			if(value == 10) {
				tenCount++;
			}
			else if(value == 20) {
				twentyCount++;
			}
		}
		return Arrays.asList(tenCount, twentyCount);
	}


}
