package pt.ipbeja.estig.baset02.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
class ModelTest {
    @Test
    void testPositionSelected() {
        Model m = new Model(new DummyView());

        m.positionSelected(new Position(2, 3));
        assertEquals(6, m.getValue(2, 3));
    }

    @Test
    void sumOthersTest() {
        Model m = new Model(new DummyView());
        m.positionSelected(new Position(0,0)); // 1, 6
        m.positionSelected(new Position(0,1)); // 1, 11
        m.positionSelected(new Position(0,0)); // 1, 16
        m.positionSelected(new Position(0,6));
        m.positionSelected(new Position(0,5));
        m.positionSelected(new Position(0,4));
        m.positionSelected(new Position(0,3));
        m.positionSelected(new Position(0,2)); // 11, 481

        List<List<Integer>> result = m.sumOthersInLine();
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(11, 481),
                Arrays.asList(1, 1),
                Arrays.asList(1, 1),
                Arrays.asList(1, 1),
                Arrays.asList(1, 1)
        );

        assertEquals(expected, result);


    }
}