package com.freeuni.oop.seminar16.puzzle;

@SuppressWarnings({"AccessStaticViaInstance", "ConstantConditions"})
public class Null {
    private static void greet() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        ((Null) null).greet();
    }
}
