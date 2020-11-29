package hw;

public class DensePolynomial implements Polynomial {
    final private int[] powercoeffArray;

    public DensePolynomial(int[] powercoeffArray) {
        this.powercoeffArray = powercoeffArray;
    }

    public DensePolynomial(String s) {
        if(s.isEmpty()) throw new NullPointerException("input String is empty");
        s = s.replace(" ", "");
        String[] temp = s.split("\\+");
        int sLength = 0;
        if (s.length() == 0) {
            this.powercoeffArray = new int[]{0};
            return;
        }
        if (Character.isDigit(temp[0].charAt(temp[0].length() - 1)) && temp[0].length() > 1)
            sLength = Integer.parseInt(temp[0].charAt(temp[0].length() - 1) + "") + 1;
        else if (temp[0].charAt(temp[0].length() - 1) == 'x') sLength = 2;
        else if (Character.isDigit(temp[0].charAt(temp[0].length() - 1))) sLength = 1;
        this.powercoeffArray = new int[sLength];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("x^")) {
                if(temp[i].split("x\\^")[0].isEmpty()) powercoeffArray[Integer.parseInt(temp[i].split("x\\^")[1])] = 1;
                else powercoeffArray[Integer.parseInt(temp[i].split("x\\^")[1])] = Integer.parseInt(temp[i].split("x\\^")[0]);
            } else if (temp[i].contains("x"))
                powercoeffArray[1] = Integer.parseInt(temp[i].substring(0, temp[i].indexOf('x')));
            else powercoeffArray[0] = Integer.parseInt(temp[i]);
        }
        for (int i = 0; i < powercoeffArray.length; i++) {
            System.out.println(i + " : " + powercoeffArray[i]);
        }
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        return powercoeffArray.length - 1;
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

    @Override
    public Polynomial add(Polynomial q) {
        return null;
    }

    @Override
    public Polynomial multiply(Polynomial q) {
        return null;
    }

    @Override
    public Polynomial subtract(Polynomial q) {
        return null;
    }

    @Override
    public Polynomial minus() {
        return null;
    }

    @Override
    public boolean wellFormed() {
        return false;
    }

    @Override
    public String toString() {
        if(isZero()==true) return"0";
        String temp2 = "";
        for (int i = powercoeffArray.length - 1; i >= 0; i--){
            if(powercoeffArray[i]!=0){
             switch (i){
                 case 0:  temp2+= powercoeffArray[i] + "+";break;
                 case 1:    temp2+= powercoeffArray[i] + "x+";break;
                 default:  temp2+= powercoeffArray[i] + "x^"+ i+"+";break;
             }
            }
        }

        if (temp2.length()!=0)temp2=temp2.substring(0,temp2.length()-1);
        if(temp2.length()==0) temp2 = "It's nothing";
        return temp2;
    }
}
