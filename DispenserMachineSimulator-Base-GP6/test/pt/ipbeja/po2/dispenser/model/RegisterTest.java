package pt.ipbeja.po2.dispenser.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author DiogoPM
 * @version 02/05/2019
 */

class RegisterTest {



    @Test
    void testInit() {
        Register register = new Register("XYZ", "Bolhas company", 2006);

        assertEquals("XYZ", register.getModel());
        assertEquals("Bolhas company", register.getCompany());
        assertEquals(2006, register.getYear());

    }

}