package pt.ipbeja.estig.baset02.model;

import org.junit.jupiter.api.Test;

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
}