package seminar7.polymorphism;

public abstract class UnaryExpression implements Expression {

    protected Expression expression;

    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

}
