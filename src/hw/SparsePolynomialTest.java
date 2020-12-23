package hw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class SparsePolynomialTest {
    @Test
    public void testdegree(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("0x^3");
        assertEquals(2,sp1.degree());
        assertEquals(0,sp2.degree());
        DensePolynomial sp3 = new DensePolynomial("9x^18+73x^2");
        assertEquals(18,sp3.degree());

    }

    @Test
    public void testgetCoefficient(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("0x^3");
        assertEquals(4,sp1.getCoefficient(2));
        assertEquals(0,sp2.getCoefficient(3));
        assertEquals(0,sp2.getCoefficient(0));
    }

    @Test
    public void testisZero(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("0x^3");
        assertEquals(false,sp1.isZero());
        assertEquals(true,sp2.isZero());
        assertNotEquals(false,sp2.isZero());
    }

    @Test
    public void testadd(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("-4  x^ 2");
        SparsePolynomial sp3 = new SparsePolynomial("0");
        SparsePolynomial sp4 = new SparsePolynomial("5x^3");
        SparsePolynomial sp5 = new SparsePolynomial("5x^3+4x^2");
        assertTrue(sp3.equals(sp1.add(sp2)));
        assertTrue(sp5.equals(sp4.add(sp1)));
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.add(null);
        });
    }

    @Test
    public void testmutiply(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("4  x^ 2");
        SparsePolynomial sp3 = new SparsePolynomial("8x^2");
        SparsePolynomial sp4 = new SparsePolynomial("16x^4");
        SparsePolynomial sp5 = new SparsePolynomial("32x^4");
        assertTrue(sp4.equals(sp1.multiply(sp2)));
        assertTrue(sp5.equals(sp1.multiply(sp3)));
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.multiply(null);
        });

    }

    @Test
    public void testsubstruct(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp2 = new SparsePolynomial("4  x^ 2");
        SparsePolynomial sp3 = new SparsePolynomial("-4x^2");
        SparsePolynomial sp4 = new SparsePolynomial("8x^2");
        SparsePolynomial sp9 = new SparsePolynomial("0");
        assertTrue(sp9.equals(sp1.subtract(sp2)));
        assertTrue(sp4.equals(sp1.subtract(sp3)));
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.subtract(null);
        });

    }

    @Test
    public void testminus(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp3 = new SparsePolynomial("-4x^2");
        assertTrue(sp1.equals(sp3.minus()));
        assertTrue(sp3.equals(sp1.minus()));
    }

    @Test
    public void testwellFormed(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp3 = new SparsePolynomial("-4x^2");
        assertEquals(true,sp1.wellFormed());
        assertEquals(true,sp3.wellFormed());

    }

    @Test
    public void testequals(){
        SparsePolynomial sp1 = new SparsePolynomial("4x^2");
        SparsePolynomial sp3 = new SparsePolynomial("4x         ^2");
        assertTrue(sp1.equals(sp3));
    }



}
