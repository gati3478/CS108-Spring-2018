package com.freeuni.oop.seminar2;

/**
 * The Fraction class implements non-negative fractions, i.e., rational
 * numbers.
 */
public class Fraction {

    private int numerator;

    private int denominator;

    /**
     * Constructs a Fraction n/d.
     *
     * @param n is the numerator, assumed non-negative.
     * @param d is the denominator, assumed positive.
     */
    public Fraction(int n, int d) {
        numerator = n;
        denominator = d;
    }

    /**
     * Constructs a Fraction n/1.
     *
     * @param n is the numerator, assumed non-negative.
     */
    public Fraction(int n) {
        this(n, 1);
    }

    /**
     * Copy constructor
     *
     * @param other The Fraction instance we need to copy from
     */
    public Fraction(Fraction other) {
        // TODO: will be further discussed and compared to clone()
        numerator = other.numerator;
        denominator = other.denominator;
    }

    @Override
    public String toString() {
        int thisGcd = gcd(numerator, denominator);

        return (numerator / thisGcd + "/" + denominator / thisGcd);
    }

    public double evaluate() {
        double n = numerator;    // convert to double
        double d = denominator;
        return (n / d);
    }

    /**
     * Add f2 to this fraction and return the result.
     *
     * @param f2 is the fraction to be added.
     * @return the result of adding f2 to this Fraction.
     */
    public Fraction add(Fraction f2) {
        return new Fraction((numerator * f2.denominator) +
                (f2.numerator * denominator),
                (denominator * f2.denominator));
    }

    /**
     * Multiply f2 to this fraction and return the result.
     *
     * @param f2 is the fraction to be multiplied.
     * @return the result of multiplying f2 to this Fraction.
     */
    public Fraction multiply(Fraction f2) {
        return new Fraction(numerator * f2.numerator,
                denominator * f2.denominator);
    }

    // recursive implementation
    private static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    // non-recursive implementation
    private static int gcd2(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            // We could have used if (!(obj instanceof Fraction)) {}
            return false;
        }

        Fraction other = (Fraction) obj;
        return this.evaluate() == other.evaluate();
    }

    @Override
    public int hashCode() {
        // TODO: will be further discussed
        return Integer.valueOf(denominator).hashCode()
                & Integer.valueOf(numerator).hashCode();
    }

}
