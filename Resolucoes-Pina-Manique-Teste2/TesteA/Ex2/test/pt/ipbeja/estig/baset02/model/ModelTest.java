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
        assertEquals((2 + 3) * 10, m.getValue(2, 3));
    }

    @Test
    void groupsTest() {
        Model m = new Model(new DummyView());

        // condições iniciais (jogadas indicadas no enunciado)
        m.positionSelected(new Position(0,1));
        m.positionSelected(new Position(0,2));
        m.positionSelected(new Position(1,2));
        m.positionSelected(new Position(3,1));
        m.positionSelected(new Position(3,2));
        m.positionSelected(new Position(5,6));
        m.positionSelected(new Position(5,5));
        m.positionSelected(new Position(7,5));


        // o resultado esperado
        List<List<Position>> expected = Arrays.asList(
                Arrays.asList(new Position(7,5)),
                Arrays.asList(new Position(5,5), new Position(5, 6)),
                Arrays.asList(new Position(3,2), new Position(3, 1)),
                Arrays.asList(new Position(1,2), new Position(0, 2), new Position(0,1))
        );

        // resultado real
        List<List<Position>> result = m.groups();

        assertEquals(expected, result);
    }
}