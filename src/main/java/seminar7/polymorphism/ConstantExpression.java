package seminar7.polymorphism;

public class ConstantExpression implements Expression {
    private double value;

    public ConstantExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }

}
