package com.freeuni.oop.seminar7.polymorphism;

public class AdditionExpression extends BinaryExpression {

    public AdditionExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }

}
