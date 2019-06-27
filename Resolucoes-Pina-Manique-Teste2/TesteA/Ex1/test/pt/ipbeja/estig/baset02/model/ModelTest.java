package pt.ipbeja.estig.baset02.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
class ModelTest {
    @Test
    void testPositionSelected() {
        Model m = new Model(new DummyView());
        m.positionSelected(new Position(2, 3));
        assertEquals(10, m.getValue(2, 3));
    }

    @Test
    void n10And20Test() {
        Model m = new Model(new DummyView());
        m.positionSelected(new Position(0,0)); // 10
        m.positionSelected(new Position(0,1)); // 20
        m.positionSelected(new Position(1,0)); // 10
        m.positionSelected(new Position(0,3)); // 20
        m.positionSelected(new Position(1,3)); // 10

        // coluna 0 = 2x10 e 0x20
        // coluna 1 = 0x10 e 1x20
        // coluna 3 = 1x10 e 1x20
        // outras são 0,0

        List<List<Integer>> result = m.n10And20();


        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(2, 0), // 0
            Arrays.asList(0, 1), // 1
            Arrays.asList(0, 0), // 2
            Arrays.asList(1, 1), // 3
            Arrays.asList(0, 0), // ...
            Arrays.asList(0, 0),
            Arrays.asList(0, 0)
        );

        assertEquals(expected, result);
    }

}