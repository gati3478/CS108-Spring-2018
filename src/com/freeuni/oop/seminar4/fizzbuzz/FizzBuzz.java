package com.freeuni.oop.seminar4.fizzbuzz;

/**
 * kata.
 */
public class FizzBuzz {

    public String evaluate(int i) {
        if ((i % 3 == 0) && (i % 5 == 0)) {
            return "FizzBuzz";
        }

        if (i % 3 == 0) {
            return "Fizz";
        }

        if (i % 5 == 0) {
            return "Buzz";
        }

        return String.valueOf(i);
    }


}
