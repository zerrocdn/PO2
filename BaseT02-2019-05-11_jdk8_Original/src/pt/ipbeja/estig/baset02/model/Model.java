package pt.ipbeja.estig.baset02.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import pt.ipbeja.estig.baset02.gui.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static jdk.nashorn.internal.parser.TokenType.EOL;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class Model {
	public static final int N_LINES = 5;
	public static final int N_COLS = 7;

	private List<List<Integer>> pieces;
	private View view;
	private File file;
	private Scanner scanner;

	/**
	 * Creates board
	 */
	public Model(View view, File file) {
		this.file = file;
		this.view = view;
		this.resetBoard();
	}

	/**
	 * Create and fills board
	 */
	private void resetBoard() {
		this.pieces = new ArrayList<>();
		try {
			this.scanner = new Scanner(file);
			for (int line = 0; line < Model.N_LINES; line++) {
				this.pieces.add(new ArrayList<>());
				for (int col = 0; col < Model.N_COLS; col++) {
					int i = scanner.nextInt();
					this.pieces.get(line).add(i);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERRO");
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
