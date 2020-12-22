package hw;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class DensePolynomialTest {

    @Test
    public void testdegree(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("0x^3");
        assertEquals(2,sp1.degree());
        assertEquals(0,sp2.degree());
        DensePolynomial sp3 = new DensePolynomial("9x^18+73x^2");
        assertEquals(18,sp3.degree());

    }

    @Test
    public void testgetCoefficient(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("0x^3");
        assertEquals(4,sp1.getCoefficient(2));
        assertEquals(0,sp2.getCoefficient(3));
        assertEquals(0,sp2.getCoefficient(0));
    }

    @Test
    public void testisZero(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("0x^3");
        assertEquals(false,sp1.isZero());
        assertEquals(true,sp2.isZero());
        assertNotEquals(false,sp2.isZero());
    }

    @Test
    public void testadd(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("-4  x^ 2");
        DensePolynomial sp3 = new DensePolynomial("0");
        DensePolynomial sp4 = new DensePolynomial("5x^3");
        DensePolynomial sp5 = new DensePolynomial("5x^3+4x^2");
        SparsePolynomial sp6 = new SparsePolynomial();
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.add(null);
        });
        assertTrue(sp3.equals(sp1.add(sp2)));
        assertTrue(sp5.equals(sp4.add(sp1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sp1.add(new SparsePolynomial("-1x^-1"));
        });
    }

    @Test
    public void testmutiply(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("4  x^ 2");
        DensePolynomial sp3 = new DensePolynomial("8x^2");
        DensePolynomial sp4 = new DensePolynomial("16x^4");
        DensePolynomial sp5 = new DensePolynomial("32x^4");
        assertTrue(sp4.equals(sp1.multiply(sp2)));
        assertTrue(sp5.equals(sp1.multiply(sp3)));
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.multiply(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sp1.multiply(new SparsePolynomial("-1x^-1"));
        });

    }

    @Test
    public void testsubstruct(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp2 = new DensePolynomial("4  x^ 2");
        DensePolynomial sp3 = new DensePolynomial("-4x^2");
        DensePolynomial sp4 = new DensePolynomial("8x^2");
        DensePolynomial sp9 = new DensePolynomial("0");
        assertTrue(sp9.equals(sp1.subtract(sp2)));
        assertTrue(sp4.equals(sp1.subtract(sp3)));
        Assertions.assertThrows(NullPointerException.class, () -> {
            sp1.subtract(null);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sp1.subtract(new SparsePolynomial("-1x^-1"));
        });

    }

    @Test
    public void testminus(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp3 = new DensePolynomial("-4x^2");
        assertTrue(sp1.equals(sp3.minus()));
        assertTrue(sp3.equals(sp1.minus()));
    }

    @Test
    public void testwellFormed(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp3 = new DensePolynomial("-4x^2");
        assertEquals(true,sp1.wellFormed());
        assertEquals(true,sp3.wellFormed());

    }

    @Test
    public void testequals(){
        DensePolynomial sp1 = new DensePolynomial("4x^2");
        DensePolynomial sp3 = new DensePolynomial("4x         ^2");
        assertTrue(sp1.equals(sp3));
    }



}
