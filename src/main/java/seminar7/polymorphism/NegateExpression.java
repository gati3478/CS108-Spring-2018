package seminar7.polymorphism;

public class NegateExpression extends UnaryExpression {
    
    public NegateExpression(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate() {
        return -expression.evaluate();
    }

    @Override
    public String toString() {
        return "(-(" + expression.toString() + "))";
    }

}
