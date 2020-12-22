package hw;

import java.util.Arrays;
import java.util.Map;

public class DensePolynomial implements Polynomial {
    final private int[] powercoeffArray;

    DensePolynomial(int[] powercoeffArray) {
        this.powercoeffArray = powercoeffArray;
    }

    /**
     * Constructor for class
     *
     * @param s given String
     */
    DensePolynomial(String s) {
        if (s.isEmpty()) throw new NullPointerException("input String is empty");
        s = s.replace(" ", "");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-' && i != 0 && s.charAt(i - 1) == '^')
                throw new IllegalArgumentException("Input contains negative degree");
        }
        String[] temp = s.split("\\+");
        int sLength = 0;
        if (s.length() == 0) {
            this.powercoeffArray = new int[]{0};
            return;
        }
        if (Character.isDigit(temp[0].charAt(temp[0].length() - 1)) && temp[0].length() > 1) {
            int inumber = 0;
            for (int i = 0; i < temp[0].length(); i++) {
                if (Character.isDigit(temp[0].charAt(temp[0].length() - 1 - i))) inumber++;
                else break;
            }
            sLength = Integer.parseInt(temp[0].substring(temp[0].length() - inumber)) + 1;
        }
        else if (temp[0].charAt(temp[0].length() - 1) == 'x') sLength = 2;
        else if (Character.isDigit(temp[0].charAt(temp[0].length() - 1))) sLength = 1;
        this.powercoeffArray = new int[sLength];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("x^")) {
                if (temp[i].split("x\\^")[0].isEmpty()) powercoeffArray[Integer.parseInt(temp[i].split("x\\^")[1])] = 1;
                else if (temp[i].split("x\\^")[0].charAt(0) == '-' && temp[i].split("x\\^")[0].length() == 1)
                    powercoeffArray[Integer.parseInt(temp[i].split("x\\^")[1])] = -1;
                else {
                    powercoeffArray[Integer.parseInt(temp[i].split("x\\^")[1])] = Integer.parseInt(temp[i].split("x\\^")[0]);
                }
            } else if (temp[i].contains("x")) {
                if (temp[i].split("x").length != 0 && temp[i].split("x")[0].length() != 0 && temp[i].split("x")[0].charAt(0) == '-')
                    powercoeffArray[1] = -1;
                else if (temp[i].substring(0, temp[i].indexOf('x')).length() != 0)
                    powercoeffArray[1] = Integer.parseInt(temp[i].substring(0, temp[i].indexOf('x')));
                else powercoeffArray[1] = 1;
            } else powercoeffArray[0] = Integer.parseInt(temp[i]);
        }

    }


    /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        for (int i = powercoeffArray.length - 1; i > 0; i--) {
            if (powercoeffArray[i] != 0) return i;
        }
        return 0;
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0 if there is no term with that exponent
     * in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     */
    @Override
    public int getCoefficient(int d) {
        return powercoeffArray[d];
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        for (int i : powercoeffArray) {
            if (i != 0) return false;
        }
        return true;
    }

    /**
     * Returns a polynomial by adding the parameter to the current instance. Neither the current instance nor the
     * parameter are modified.
     *
     * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial add(Polynomial q) {
        if (q == null) throw new NullPointerException("input is null");
        DensePolynomial p1 = new DensePolynomial(powercoeffArray);
        DensePolynomial p2 = new DensePolynomial(q.toString());
        boolean p1Larger = p1.powercoeffArray.length >= p2.powercoeffArray.length ? true : false;
        int length = p1Larger ? p1.powercoeffArray.length : p2.powercoeffArray.length;
        DensePolynomial p3 = new DensePolynomial(new int[length]);
        for (int i = 0; i < length; i++) {
            if (p1Larger) {
                if (i < p2.powercoeffArray.length)
                    p3.powercoeffArray[i] = p1.powercoeffArray[i] + p2.powercoeffArray[i];
                else p3.powercoeffArray[i] = p1.powercoeffArray[i];
            } else {
                if (i < p1.powercoeffArray.length)
                    p3.powercoeffArray[i] = p1.powercoeffArray[i] + p2.powercoeffArray[i];
                else p3.powercoeffArray[i] = p2.powercoeffArray[i];
            }
        }
        return p3;
    }

    /**
     * Returns a polynomial by multiplying the parameter with the current instance.  Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial multiply(Polynomial q) {
        if (q == null) throw new NullPointerException("input is null");
        DensePolynomial p1 = new DensePolynomial(powercoeffArray);
        DensePolynomial p2 = new DensePolynomial(q.toString());
        int[] temp = new int[p1.degree() + p2.degree() + 1];
        for (int i = 0; i < p1.powercoeffArray.length; i++) {
            for (int j = 0; j < p2.powercoeffArray.length; j++) {
                temp[i + j] += p1.powercoeffArray[i] * p2.powercoeffArray[j];
            }
        }


        return new DensePolynomial(temp);
    }

    /**
     * Returns a  polynomial by subtracting the parameter from the current instance. Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial subtract(Polynomial q) {
        if (q == null) throw new NullPointerException("input is null");
        DensePolynomial p1 = new DensePolynomial(powercoeffArray);
        DensePolynomial p2 = new DensePolynomial(q.toString());
        DensePolynomial p3 = new DensePolynomial(p2.minus().toString());
        return p1.add(p3);

    }

    /**
     * Returns a polynomial by negating the current instance. The current instance is not modified.
     *
     * @return -this
     */
    @Override
    public Polynomial minus() {
        int[] temp = new int[powercoeffArray.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = powercoeffArray[i] * -1;
        }
        return new DensePolynomial(temp);
    }

    /**
     * Checks if the class invariant holds for the current instance.
     *
     * @return {@literal true} if the class invariant holds, and {@literal false} otherwise.
     */
    @Override
    public boolean wellFormed() {
        return true;
    }

    @Override
    public boolean equals(Object p) {
        if (p == this) {
            return true;
        }
        if (!(p instanceof DensePolynomial)) return false;
        DensePolynomial p1 = (DensePolynomial) p;
        DensePolynomial p2 = new DensePolynomial(powercoeffArray);
        if (p2.toString().equals(p1.toString())) return true;
        return false;
    }

    /**
     * display a polynomial in the canonical mathematica way.
     *
     * @return output string
     */
    @Override
    public String toString() {
        if (isZero() == true) return "0";
        String temp2 = "";
        for (int i = powercoeffArray.length - 1; i >= 0; i--) {
            if (powercoeffArray[i] != 0) {
                switch (i) {
                    case 0:
                        temp2 += powercoeffArray[i] + "+";
                        break;
                    case 1:
                        temp2 += powercoeffArray[i] + "x+";
                        break;
                    default:
                        temp2 += powercoeffArray[i] + "x^" + i + "+";
                        break;
                }
            }
        }

        if (temp2.length() != 0) temp2 = temp2.substring(0, temp2.length() - 1);
        if (temp2.length() == 0) temp2 = "It's nothing";
        return temp2;
    }

    /**
     * Check if another obeject and this one are equal
     *
     * @param p given object
     * @return{@literal true} if they are equals, and {@literal false} otherwise
     */

//    public boolean equals(Polynomial p) {
//        System.out.println("oaisdjfoijasodf");
//        if (p == this) {
//            return true;
//        }
//        System.out.println("assaodiajid");
//        System.out.println( p instanceof DensePolynomial);
//        if (!(p instanceof DensePolynomial)) return false;
//        System.out.println("pass 1");
//        DensePolynomial p1 = (DensePolynomial) p;
//        DensePolynomial p2 = new DensePolynomial(powercoeffArray);
//        if (p2.toString().equals(p1.toString())) return true;
//        return false;
//    }
}
