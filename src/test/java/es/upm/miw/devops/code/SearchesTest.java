package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchesTest {
    private final Searches searches = new Searches();

    @Test
    void testFindFirstFractionSubtractionByUserName_withNegativeFraction() {
        // En UsersDatabase: el usuario "Ana" tiene la fracción -1/5 como primera negativa
        Fraction result = searches.findFirstFractionSubtractionByUserName("Ana");

        assertNotNull(result);
        assertEquals(-1, result.getNumerator());
        assertEquals(5, result.getDenominator());
    }

    @Test
    void testFindFirstFractionSubtractionByUserName_withoutNegativeFraction() {
        // En UsersDatabase: el usuario "Oscar" (id=1) solo tiene 0/1, 1/1, 2/1 → todas no negativas
        Fraction result = searches.findFirstFractionSubtractionByUserName("Oscar");

        assertNull(result);
    }

    @Test
    void testFindFirstFractionDivisionByUserId_validDivision() {
        // Usuario "1": 0/1 ÷ 1/1 = 0/1
        Fraction result = searches.findFirstFractionDivisionByUserId("1");

        assertNotNull(result);
        assertEquals(0, result.getNumerator());
        assertEquals(1, result.getDenominator());
    }

    @Test
    void testFindFirstFractionDivisionByUserId_withNegativeResult() {
        // Usuario "2": 2/1 ÷ -1/5 = 10/-1  (equivalente a -10/1)
        Fraction result = searches.findFirstFractionDivisionByUserId("2");

        assertNotNull(result);
        assertTrue(result.isEquivalent(new Fraction(-10, 1)));
    }

    @Test
    void testFindFirstFractionDivisionByUserId_invalidDivision() {
        // Usuario "6": primeras dos fracciones con denom=0 -> inválido -> null
        Fraction result = searches.findFirstFractionDivisionByUserId("6");

        assertNull(result);
    }

    @Test
    void testFindFirstFractionDivisionByUserId_userNotFound() {
        Fraction result = searches.findFirstFractionDivisionByUserId("999");
        assertNull(result);
    }
}
