package hw;

import javafx.beans.binding.ObjectExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SparsePolynomial implements Polynomial {
    private final TreeMap<Integer, Integer> powerCoeffMap;

    SparsePolynomial() {
        this.powerCoeffMap = new TreeMap<Integer, Integer>();
    }

    /**
     * Constructor for class
     *
     * @param s given String
     */
    SparsePolynomial(String s) {
        s = s.replace(" ", "");
        if (s.isEmpty()) throw new NullPointerException("input String is empty");
        String[] temp = s.split("\\+");
        this.powerCoeffMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("x^")) {
                if (temp[i].split("x\\^")[0].isEmpty()) {
                    powerCoeffMap.put(Integer.parseInt(temp[i].split("x\\^")[1]), 1);
                } else {
                    if(temp[i].split("x\\^")[0].charAt(0)=='-'&&temp[i].split("x\\^")[0].length()==1)powerCoeffMap.put(Integer.parseInt(temp[i].split("x\\^")[1]), -1);
                    else if (Integer.parseInt(temp[i].split("x\\^")[0]) != 0)
                        powerCoeffMap.put(Integer.parseInt(temp[i].split("x\\^")[1]), Integer.parseInt(temp[i].split("x\\^")[0]));
                }
            } else if (temp[i].contains("x")) {
                if(temp[i].indexOf('x')==temp[i].indexOf('-')+1&&temp[i].indexOf('-')!=-1) powerCoeffMap.put(1, -1);
                else if(temp[i].length()==1)powerCoeffMap.put(1, 1);
                else if (Integer.parseInt(temp[i].substring(0, temp[i].indexOf('x'))) != 0) {
                    powerCoeffMap.put(1, Integer.parseInt(temp[i].substring(0, temp[i].indexOf('x'))));
                }
            } else {
                if (Integer.parseInt(temp[i]) != 0)
                    powerCoeffMap.put(0, Integer.parseInt(temp[i]));
            }

            }
        }



    SparsePolynomial(TreeMap<Integer, Integer> tm) {
        this.powerCoeffMap = tm;

    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        for (Map.Entry<Integer, Integer> e : powerCoeffMap.entrySet()) {
            if (e.getValue() != 0) return e.getKey();
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
        if (!powerCoeffMap.keySet().contains(d)) return 0;
        return powerCoeffMap.get(d);
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        for (int b : powerCoeffMap.values()) {
            if (b != 0) return false;
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
        SparsePolynomial p1 = new SparsePolynomial(powerCoeffMap);
        SparsePolynomial p2 = new SparsePolynomial(q.toString());
        SparsePolynomial p3 = new SparsePolynomial();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> e : p1.powerCoeffMap.entrySet()) {
            for (Map.Entry<Integer, Integer> ee : p2.powerCoeffMap.entrySet()) {
                if (e.getKey() == ee.getKey()) {
                    a.add(e.getKey());
                }
            }
        }
        p3.powerCoeffMap.putAll(p1.powerCoeffMap);
        p3.powerCoeffMap.putAll(p2.powerCoeffMap);
        for (int i : a) {
            p3.powerCoeffMap.put(i, p1.powerCoeffMap.get(i) + p2.powerCoeffMap.get(i));
        }
        for (Map.Entry<Integer, Integer> e : p3.powerCoeffMap.entrySet()) {
            if (e.getValue() == 0) {
                p3.powerCoeffMap.remove(e.getKey());
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
        SparsePolynomial p1 = new SparsePolynomial(powerCoeffMap);
        SparsePolynomial p2 = new SparsePolynomial(q.toString());
        SparsePolynomial p3 = new SparsePolynomial();
        boolean repeated = false;
        for (Map.Entry<Integer, Integer> e1 : p1.powerCoeffMap.entrySet()) {
            for (Map.Entry<Integer, Integer> e2 : p2.powerCoeffMap.entrySet()) {
                p3.add(e1.getKey() + e2.getKey(), e1.getValue() * e2.getValue());
            }
        }

        return p3;
    }

    ;

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
        SparsePolynomial p1 = new SparsePolynomial(powerCoeffMap);
        SparsePolynomial p2 = new SparsePolynomial(q.toString());
        SparsePolynomial p3 = new SparsePolynomial();
        p3.powerCoeffMap.putAll(p1.powerCoeffMap);
        p3.subtractAll(p2);
        return p3;

    }

    /**
     * Returns a polynomial by negating the current instance. The current instance is not modified.
     *
     * @return -this
     */
    @Override
    public Polynomial minus() {
        SparsePolynomial p1 = new SparsePolynomial();
        int[] temp = new int[powerCoeffMap.size() * 2];
        int counter = 0;
        for (Map.Entry<Integer, Integer> e : powerCoeffMap.entrySet()) {
            temp[counter++] = e.getKey();
            temp[counter++] = e.getValue() * -1;
        }
        for (int i = 0; i < counter - 1; i++) {
            p1.powerCoeffMap.put(temp[i++], temp[i]);
        }
        return p1;
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

    /**
     * display a polynomial in the canonical mathematica way.
     *
     * @return output string
     */
    @Override
    public String toString() {
        String temp2 = "";
        if (powerCoeffMap.size() == 0) return "0";
        int currentEntryKey = ((TreeMap<Integer, Integer>) powerCoeffMap).lastEntry().getKey();
        int treesetSize = powerCoeffMap.size();
        while (treesetSize > 0) {
            if (treesetSize == powerCoeffMap.size()) {
                switch (currentEntryKey) {
                    case 1:
                        temp2 += powerCoeffMap.get(currentEntryKey) + "x";
                        break;
                    case 0:
                        temp2 += powerCoeffMap.get(currentEntryKey);
                        break;
                    default:
                        temp2 += powerCoeffMap.get(currentEntryKey) + "x^" + currentEntryKey;
                        break;
                }
            } else {
                switch (currentEntryKey) {
                    case 1:
                        temp2 += powerCoeffMap.get(currentEntryKey) + "x";
                        break;
                    case 0:
                        temp2 += powerCoeffMap.get(currentEntryKey);
                        break;
                    default:
                        temp2 += powerCoeffMap.get(currentEntryKey) + "x^" + currentEntryKey;
                        break;
                }
            }
            if (treesetSize > 1)
                currentEntryKey = ((TreeMap<Integer, Integer>) powerCoeffMap).lowerEntry(currentEntryKey).getKey();
            if (treesetSize > 1) {
                temp2 += "+";
            }
            treesetSize--;
        }
        return temp2;
    }

    /**
     * Check if another obeject and this one are equal
     *
     * @param p given object
     * @return{@literal true} if they are equals, and {@literal false} otherwise
     */
    public boolean equals(Object p) {
        if (p == this) {
            return true;
        }
        if (!(p instanceof SparsePolynomial)) return false;
        SparsePolynomial p1 = (SparsePolynomial) p;
        if (p1.powerCoeffMap.equals(powerCoeffMap)) return true;
        return false;
    }

    private Integer add(Integer power, Integer coefficient) {
        double old_coefficient = 0;
        if (containsKey(power)) {
            old_coefficient = get(power);
        }
        if (old_coefficient + coefficient == 0) {
            remove(power);
            return 0;
        } else {
            return put(power, (int) (old_coefficient + coefficient));
        }
    }

    private boolean containsKey(Integer power) {
        return powerCoeffMap.containsKey(power);
    }

    private int remove(Integer power) {
        return powerCoeffMap.remove(power);
    }

    private Integer put(Integer power, Integer coefficient) {
        return powerCoeffMap.put(power, coefficient);
    }

    private Integer get(Integer power) {
        return powerCoeffMap.get(power);
    }

    private Integer subtract(Integer power, Integer coefficient) {
        double old_coefficient = 0;
        if (containsKey(power)) {
            old_coefficient = get(power);
        }
        if (old_coefficient - coefficient == 0) {
            remove(power);
            return 0;
        } else {
            return put(power, (int) (old_coefficient - coefficient));
        }
    }

    private void subtractAll(SparsePolynomial p) {
        subtractAll(p.powerCoeffMap);
    }

    private void subtractAll(Map<Integer, Integer> m) {
        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            subtract(e.getKey(), e.getValue());
        }
    }
}
