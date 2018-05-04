package seminar7.polymorphism;

public class AbsExpression extends UnaryExpression {

    public AbsExpression(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate() {
        return Math.abs(expression.evaluate());
    }

    @Override
    public String toString() {
        return "|" + expression.toString() + "|";
    }

}
