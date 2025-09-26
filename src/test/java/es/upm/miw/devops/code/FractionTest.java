package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {
    @Test
    void testConstructorWithParams() {
        Fraction f = new Fraction(3, 4);
        assertEquals(3, f.getNumerator());
        assertEquals(4, f.getDenominator());
    }

    @Test
    void testDefaultConstructor() {
        Fraction f = new Fraction(); // 1/1
        assertEquals(1, f.getNumerator());
        assertEquals(1, f.getDenominator());
        assertEquals(1.0, f.decimal(), 1e-12);
    }

    @Test
    void testGettersAndSetters() {
        Fraction f = new Fraction(2, 5);
        f.setNumerator(7);
        f.setDenominator(9);
        assertEquals(7, f.getNumerator());
        assertEquals(9, f.getDenominator());
        assertEquals(7.0/9.0, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalPositive() {
        Fraction f = new Fraction(2, 4); // 0.5
        assertEquals(0.5, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalNegativeNumerator() {
        Fraction f = new Fraction(-3, 6); // -0.5
        assertEquals(-0.5, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalNegativeDenominator() {
        Fraction f = new Fraction(3, -6); // -0.5
        assertEquals(-0.5, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalBothNegative() {
        Fraction f = new Fraction(-3, -6); // 0.5
        assertEquals(0.5, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalZeroNumerator() {
        Fraction f = new Fraction(0, 7);
        assertEquals(0.0, f.decimal(), 1e-12);
    }

    @Test
    void testDecimalDenominatorZeroNonZeroNumerator() {
        Fraction f = new Fraction(5, 0);
        double value = f.decimal(); // double/0 -> Infinity en Java
        assertTrue(Double.isInfinite(value));
        assertEquals(Double.POSITIVE_INFINITY, value);
    }

    @Test
    void testDecimalDenominatorZeroAndZeroNumerator() {
        Fraction f = new Fraction(0, 0);
        double value = f.decimal(); // 0.0/0.0 -> NaN en Java
        assertTrue(Double.isNaN(value));
    }

    @Test
    void testToStringContainsFields() {
        Fraction f = new Fraction(8, 3);
        String s = f.toString();
        assertTrue(s.contains("numerator=8"));
        assertTrue(s.contains("denominator=3"));
        assertTrue(s.startsWith("Fraction{"));
    }
}
