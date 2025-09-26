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
}
