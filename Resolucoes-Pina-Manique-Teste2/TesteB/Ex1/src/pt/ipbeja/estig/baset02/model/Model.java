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

	public void positionSelected(Position position) {

		int value = sumLine(position.getLine(), position.getCol());

		this.pieces.get(position.getLine()).set(position.getCol(), value);
		this.view.updateText(this.pieces);
	}

	private int sumLine(int line, int col) {
		int sum = 0;
		List<Integer> row = pieces.get(line); // vamos buscar a linha que nos interessa verificar
		// somar os valores
		for (int c = 0; c < row.size(); c++) {
			if(c != col) { // excepto a coluna da jogada
				sum += row.get(c);
			}
		}
		return sum;
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}

	public List<List<Integer>> sumOthersInLine() {
		List<List<Integer>> sums = new ArrayList<>();

		for (List<Integer> line : pieces) {
			sums.add(minMaxInLine(line));
		}
		return sums;
	}

	private List<Integer> minMaxInLine(List<Integer> line) {

		int min = Integer.MAX_VALUE; // inicializamos a variável min com o maior valor que um int pode tomar
		int max = Integer.MIN_VALUE; // identico para max mas valor mínimo de int

		for (int val : line) {
			min = Math.min(min, val); // trocamos o valor para o menor dos dois
			max = Math.max(max, val); // ...
		}

		// Ou simplesmente
		//int min = Collections.min(line);
		//int maxn = Collections.max(line);

		return Arrays.asList(min, max); // devolvemos a lista com os 2 valores
	}
}
