package pt.ipbeja.estig.baset02.model;

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
	private View view;

	private List<Position> clickedPositions = new ArrayList<>();

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
		clickedPositions.add(position);
		swapCell(position);
		//this.pieces.get(position.getLine()).set(position.getCol(), 0);
		this.view.updateText(this.pieces);
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}

	public void swapCell(Position position) {
		int v = pieces.get(position.getLine()).get(position.getCol());
		v ^= 1; // xor! :)
		//v = 1 - v;
		//v = (v + 1) % 2;
		pieces.get(position.getLine()).set(position.getCol(), v);
		this.view.swapCell(position); // pode ser retirado
	}

	public List<List<Position>> nearPartitions() {
		List<List<Position>> parts = new ArrayList<>();
		if(clickedPositions.size() == 0) return parts;
		if(clickedPositions.size() == 1) {
			parts.add(Collections.singletonList(clickedPositions.get(0)));
			return parts;
		}

		int index = 0;
		while (index < clickedPositions.size() - 1) {
			List<Position> group = findAdjacent(index);
			parts.add(group);
			index += group.size();
		}

		return parts;
	}

	private List<Position> findAdjacent(int from) {
		List<Position> group = new ArrayList<>();
		Position current = clickedPositions.get(from);
		group.add(current);

		Position next;
		for (int i = from + 1; i < clickedPositions.size(); i++) {
			next = clickedPositions.get(i);
			if(areAdjacent(current, next)) {
				group.add(next);
				current = next;
			}
			else {
				break;
			}
		}
		return group;
	}

	private boolean areAdjacent(Position p0, Position p1) {
		return Math.hypot(p0.getCol() - p1.getCol(), p0.getLine() - p1.getLine()) < 2;
	}
}
