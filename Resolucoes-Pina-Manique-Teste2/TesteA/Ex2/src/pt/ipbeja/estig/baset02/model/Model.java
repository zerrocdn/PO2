package pt.ipbeja.estig.baset02.model;

import pt.ipbeja.estig.baset02.gui.View;

import java.util.*;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {

	// Temos de aumentar tamanho da grelha por causa dos testes
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
		// temos várias possibilidades para resolver este problema mas a class Position terá de guardar ou calcular o
		// seu value pois o enunciado dita que o método updateCell recebe apenas como arg uma Position

		// podemos calcular e guardar o valor na Position ou fazer com que Position calcule o seu valor (ver na class o código comentado)
		int value = (position.getCol() + position.getLine()) * 10;
		position.setValue(value);
		this.clickedPositions.add(position);
		this.pieces.get(position.getLine()).set(position.getCol(), value);
		//this.view.updateText(this.pieces);
		this.view.updateCell(position);
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}

	public List<List<Position>> groups() {
		List<List<Position>> groups = new ArrayList<>();
		int positionsCount = clickedPositions.size();

		if(positionsCount == 0) return  groups; // se não há elementos.. não há nada a procurar
		if(positionsCount == 1) { // se só houver um elemento
			List<Position> single = Arrays.asList(clickedPositions.get(0)); // só há um grupo com o único elemento
			groups.add(single);
			return groups; // devolvemos o único grupo que existe e termina aqui o método
		}

		// agora sim devemos procurar grupos de adjacencias
		int index = positionsCount - 1;
		while (index >= 0) {
			List<Position> group = findAdjacent(index);
			groups.add(group);

			index -= group.size();
			// vamos decrementar o indice de acordo com o tamanho do grupo calculado para só
			// começarmos a procurar um novo grupo a partir de um indice que não tenha sido já considerado
		}

		return groups;
	}


	private List<Position> findAdjacent(int from) {
		List<Position> group = new ArrayList<>();

		Position current = clickedPositions.get(from);
		group.add(current); // A primeira faz sempre parte do grupo, portanto podemos adiciona-la já

		while (from > 0) {
			Position previous = clickedPositions.get(from - 1); // vamos buscar a posição anterior à "current"
			if(areAdjacent(current, previous)) { // se forem adjacentes...
				group.add(previous); // adicionamos o elemento à lista
				current = previous; // o novo "current" é agora o "previous". Queremos comparar 5->4, 4->3, 3->2 ...
			}
			else {
				break; // se não forem adjacentes, terminamos a procura!
			}
			from--; // decrementamos o contador para ir buscar o próximo elemento no próximo ciclo
		}

		return group;
	}


	private boolean areAdjacent(Position p0, Position p1) {
		int x = p0.getCol() - p1.getCol();
		int y = p0.getLine() - p1.getLine();
		return Math.hypot(x, y) == 1;
	}

	// Uma alternativa...

	private List<Position> findAdjacent2(int from) {

		int to = from + 1; // to em sublist é exclusivo

		while (from > 0) {
			Position current = clickedPositions.get(from);
			Position previous = clickedPositions.get(from - 1); // vamos buscar a posição anterior à "current"

			if(!areAdjacent(current, previous)) { // se não forem adjacentes...
				break;
			}
			from--; // decrementamos o contador para ir buscar o próximo elemento no próximo ciclo
		}
		// neste caso temos de inverter a lista pois as sublists vêm pela ordem dos elementos
		
		// Collections.reverse(list); // alternativa ao método reverse abaixo
		List<Position> positions = clickedPositions.subList(from, to);
		return reverse(positions);
	}

	private List<Position> reverse(List<Position> list) {

		List<Position> reversed = new ArrayList<>(list.size());
		for (Position position : list) {
			reversed.add(0, position);
		}
		return reversed;
	}
}

