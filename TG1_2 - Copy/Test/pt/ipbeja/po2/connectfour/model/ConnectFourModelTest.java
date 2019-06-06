package pt.ipbeja.po2.connectfour.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

class ConnectFourModelTest {

    /**
     * The test check the placing a part on an empty column
     */
    @Test
    void teste01() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        model.placeSelected(0, 5);
        assertEquals(Cell.PLAYER1, model.getCell(5, 5));
    }

    /**
     * The test check placement of a part of a column already containing a piece on line four, the second line from below
     */
    @Test
    void teste02() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        model.placeSelected(0, 1);
        assertEquals(4, model.bestLine(1));
    }

    /**
     * The test check placing a piece on a full column
     */
    @Test
    void test03() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        model.placeSelected(0, 1);//Player1
        model.placeSelected(0, 1);//Player2
        model.placeSelected(0, 1);//Player1
        model.placeSelected(0, 1);//Player2
        model.placeSelected(0, 1);//Player1
        model.placeSelected(0, 1);//Player2

        Cell cell = model.getCell(0, 1);
        assertEquals(false, model.isFree(0, 1));
        assertEquals(cell, Cell.PLAYER2);

        model.placeSelected(0, 1);//Player1
        assertEquals(cell, Cell.PLAYER2);
    }

    /**
     * The test check a play in which the player does not win
     */
    @Test
    void test04() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        model.placeSelected(0, 5);
        assertEquals(false, model.finish);
    }

    /**
     * The test check a play in which the player wins with a line of four on the horizontal
     */
    @Test
    void test05() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        model.placeSelected(0, 0); //Player1
        model.placeSelected(0, 0); //Player2
        model.placeSelected(0, 1); //Player1
        model.placeSelected(0, 1); //Player2
        model.placeSelected(0, 2); //Player1
        model.placeSelected(0, 2); //Player2
        model.placeSelected(0, 3); //Player1

        assertEquals(true, model.horizontal(5));
    }

    /**
     * The test check a play in which the player wins with a line of four on the vertical
     */
    @Test
    void test06() {
        ConnectFourModel model = new ConnectFourModel(new TestView());

        for (int i = 0; i < 0; i++) {
            for (int j = 0; j < 2; j++) {
                model.placeSelected(0, j);
            }
        }

        assertEquals(true, model.vertical(2, 0));
    }

    /**
     * The test check a play in which the player wins with a line of four on the diagonal
     */
    @Test
    void test07() {
        ConnectFourModel model = new ConnectFourModel(new TestView());

        for (int i = 0; i < 4; i++) {
            model.placeSelected(0, i);
        }

        model.placeSelected(0,1); //1

        for (int i = 0; i < 3; i++) {
            model.placeSelected(0, i);
        }

        model.placeSelected(0,6); //1

        model.placeSelected(0,4); //1

        assertEquals(true, model.checkDiagonals(5,3));
    }

    /**
     * The test check a play in which game without a win
     */
    @Test
    void test08() {
        ConnectFourModel model = new ConnectFourModel(new TestView());
        for (int i = 0; i <= 2; i++) {
            for (int j = model.SIZE_LINE - 1; j >= 0; j--) {
                model.placeSelected(j, i);
            }
        }

        model.placeSelected(5, 6);

        for (int i = 3; i <= 5; i++) {
            for (int j = model.SIZE_LINE - 1; j >= 0; j--) {
                model.placeSelected(j, i);

            }
        }

        model.placeSelected(4, 6);
        model.placeSelected(3, 6);
        model.placeSelected(2, 6);
        model.placeSelected(1, 6);
        model.placeSelected(0, 6);

        assertEquals(true, model.isDrawPosition(0, 6));
    }
}