package seminar7.polymorphism;

public class Demo {

    public static void main(String[] args) {
        // ((-((4.0 + 5.0))) * |-100.0|)
        Expression expression = new MultiplicationExpression(
                new NegateExpression(
                        new AdditionExpression(
                                new ConstantExpression(4),
                                new ConstantExpression(5))),
                new AbsExpression(
                        new ConstantExpression(-100)));

        System.out.println(expression.toString() + " = " + expression.evaluate());
    }

}
