package pt.ipbeja.estig.baset02.model;

import org.junit.jupiter.api.Test;

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
    void n10And20() {
        Model m = new Model(new DummyView());

        m.positionSelected(new Position(0, 0));
        m.positionSelected(new Position(1, 0));
        m.positionSelected(new Position(2, 0));

        assertEquals(2, m.numberOf10and20.get(0).get(0));
        assertEquals(1, m.numberOf10and20.get(0).get(1));
    }
}




