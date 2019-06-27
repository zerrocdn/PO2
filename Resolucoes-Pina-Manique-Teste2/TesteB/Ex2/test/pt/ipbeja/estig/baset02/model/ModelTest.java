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
        assertEquals(0, m.getValue(2, 3));
    }

    @Test
    void nearPartitionsTest() {
        Model m = new Model(new DummyView());
        m.positionSelected(new Position(0,0));
        m.positionSelected(new Position(0,1));
        m.positionSelected(new Position(1,1));
        m.positionSelected(new Position(2,0));
        m.positionSelected(new Position(3,1));
        m.positionSelected(new Position(4,4));
        m.positionSelected(new Position(6,6));
        m.positionSelected(new Position(6,5));

        List<List<Position>> expected = Arrays.asList(
                Arrays.asList(new Position(0,0), new Position(0,1), new Position(1, 1), new Position(2,0), new Position(3,1)),
                Arrays.asList(new Position(4,4)),
                Arrays.asList(new Position(6,6), new Position(6,5))
        );

        List<List<Position>> result = m.nearPartitions();

        assertEquals(expected, result);

    }
}