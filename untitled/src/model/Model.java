package model;

import java.util.Random;

public class Model {

    private int[][] positions;
    private final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        new Model();
    }

    public Model() {
        this.positions = new int[BOARD_SIZE][BOARD_SIZE];
        setBoard();
    }



    public void setBoard(){

        Random random = new Random();
        int l = 0;

        int a[] = new int[32];
        int b[] = new int[32];
        for (int i = 0; i < 32; i++) {
            a[i] = random.nextInt(i);
            b[i] = a[i];
        }





        for (int line = 0; line < BOARD_SIZE; line++) {


            for (int col = 0; col < BOARD_SIZE; col++) {

                this.positions[line/2][col/2] = a[line*col];
                this.positions[line/2][col/2] = b[line * col];

            }
            System.out.println();
        }
    }
}


