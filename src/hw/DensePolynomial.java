package hw;

public class DensePolynomial implements Polynomial {
    @Override
    public int degree() {
        return 0;
    }

    @Override
    public int getCoefficient(int d) {
        return 0;
    }

    @Override
    public boolean isZero() {
        return false;
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
}
