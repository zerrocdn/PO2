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
	private int max;
	private List<List<Integer>> pieces;
	private View view;
	private Random rand;

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
		this.rand = new Random();
		askValue();
		this.pieces = new ArrayList<>();
		for (int line = 0; line < Model.N_LINES; line++) {
			this.pieces.add(new ArrayList<>());
			for (int col = 0; col < Model.N_COLS; col++) {
				this.pieces.get(line).add(this.rand.nextInt(this.max+1));
			}
		}
	}
	public void askValue(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Indique um numero: ");
		setMaximum(scan.nextInt());
	}

	public void setMaximum(int max){

		this.max = max;
	}

	/*public void resetIfSmall(Position position){

		int count = 0;
		int row = position.getLine();
		int col = position.getCol();
		if(row-1 >= 0 && col-1>=0 && this.pieces.get(row).get(col) < this.pieces.get(row-1).get(col-1)){
			count++;
		}
		if(row-1 >= 0 && col+1<=6 && this.pieces.get(row).get(col) < this.pieces.get(row-1).get(col+1)){
			count++;
		}
		if(row+1 <= 4 && col +1 <= 6 && this.pieces.get(row).get(col) < this.pieces.get(row+1).get(col+1)){
			count++;
		}
		if(row+1 <=4 && col-1>=0 && this.pieces.get(row).get(col) < this.pieces.get(row+1).get(col-1)){
			count++;
		}
		if (count >= 2){
			this.pieces.get(position.getLine()).set(position.getCol(), 0);
		}

	}*/



	/*public void sumOfLinesAndCols(Position position){
		int row = position.getLine();
		int col = position.getCol();
		int clicked = 0;
		int sum1 = clicked;
		int finalSum = 0;



		if(row-2 >= 0){
			for (int i = 0; i < 3; i++) {
				sum1+=this.pieces.get(row-i).get(col);
			}
			if(sum1 > finalSum){
				finalSum = sum1;
			}
			System.out.println("cima: " + sum1);
			sum1 = clicked;
		}
		if(row+2 < N_LINES){
			for (int i = 0; i < 3; i++) {

				sum1+=this.pieces.get(row+i).get(col);
			}
			if(sum1 > finalSum){
				finalSum = sum1;
			}
			System.out.println("baixo: " + sum1);
			sum1=clicked;
		}
		if(col-2 >= 0){
			for (int i = 0; i < 3; i++) {

				sum1+=this.pieces.get(row).get(col-i);
			}
			if(sum1 > finalSum){
				finalSum = sum1;
			}
			System.out.println("esquerda: " + sum1);
			sum1 = clicked;
		}
		if(col+2 < N_COLS){
			for (int i = 0; i < 3; i++) {

				sum1+=this.pieces.get(row).get(col+i);
			}
			if(sum1 > finalSum){
				finalSum = sum1;
			}
			System.out.println("direita: " + sum1);

		}
		System.out.println(finalSum);
		this.pieces.get(row).set(col,finalSum);
		this.view.setOff(row, col);
	}*/



	public void positionSelected(Position position)
	{
		//this.pieces.get(position.getLine()).set(position.getCol(), 0);
		this.pieces.get(position.getLine()).get(position.getCol());
		//resetIfSmall(position);
		//sumOfLinesAndCols(position);
		this.view.updateText(this.pieces);
	}

	public int getValue(int line, int col)
	{
		return this.pieces.get(line).get(col);
	}

	public void newGame(){
		this.resetBoard();
	}


	public void printBoard(){
		for (int i = 0; i < N_LINES; i++) {
			for (int j = 0; j < N_COLS; j++) {
				System.out.print(this.getValue(i, j)+" ");
			}
			System.out.println();
		}
	}
}
