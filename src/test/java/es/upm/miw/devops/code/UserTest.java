package es.upm.miw.devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private Fraction fraction1;
    private Fraction fraction2;

    @BeforeEach
    void setUp() {
        fraction1 = new Fraction(); // usamos constructor vacío
        fraction2 = new Fraction();

        user = new User("1", "Juan", "Pérez", new ArrayList<>(List.of(fraction1)));
    }

    @Test
    void testEmptyConstructorInitializesFractions() {
        User emptyUser = new User();
        assertNotNull(emptyUser.getFractions());
        assertTrue(emptyUser.getFractions().isEmpty());
    }

    @Test
    void testConstructorWithParams() {
        assertEquals("1", user.getId());
        assertEquals("Juan", user.getName());
        assertEquals("Pérez", user.getFamilyName());
        assertEquals(1, user.getFractions().size());
        assertTrue(user.getFractions().contains(fraction1));
    }

    @Test
    void testSettersAndGetters() {
        user.setName("Pedro");
        user.setFamilyName("García");
        user.setFractions(new ArrayList<>(List.of(fraction2)));

        assertEquals("Pedro", user.getName());
        assertEquals("García", user.getFamilyName());
        assertEquals(1, user.getFractions().size());
        assertTrue(user.getFractions().contains(fraction2));
    }

    @Test
    void testAddFraction() {
        user.addFraction(fraction2);
        assertEquals(2, user.getFractions().size());
        assertTrue(user.getFractions().contains(fraction2));
    }

    @Test
    void testFullName() {
        assertEquals("Juan Pérez", user.fullName());
    }

    @Test
    void testInitials() {
        assertEquals("J.", user.initials());
    }

    @Test
    void testToStringContainsFields() {
        String str = user.toString();
        assertTrue(str.contains("id='1'"));
        assertTrue(str.contains("name='Juan'"));
        assertTrue(str.contains("familyName='Pérez'"));
        assertTrue(str.contains("fractions"));
    }

    @Test
    void testInitialsThrowsWhenNameEmpty() {
        User badUser = new User("2", "", "Pérez", new ArrayList<>());
        assertThrows(StringIndexOutOfBoundsException.class, badUser::initials);
    }
}

//i5

