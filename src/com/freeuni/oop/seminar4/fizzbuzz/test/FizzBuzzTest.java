package com.freeuni.oop.seminar4.fizzbuzz.test;

import com.freeuni.oop.seminar4.fizzbuzz.FizzBuzz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Tests for FizzBuzz.
 */
public class FizzBuzzTest {

    FizzBuzz fb;

    @Before
    public void init() {
        // New FizzBuzz is created before each unit test
        fb = new FizzBuzz();
    }

    @Test
    public void testNonMultiples() {
        assertEquals("1", fb.evaluate(1));
        assertEquals("2", fb.evaluate(2));
        assertEquals("1234", fb.evaluate(1234));
        assertEquals("2222", fb.evaluate(2222));
    }

    @Test
    public void testMultiplesOfThree() {
        for (int i = 3; i < 100; i += 3) {
            if (i % 5 != 0) {
                assertEquals("Fizz", fb.evaluate(i));
            }
        }
    }

    @Test
    public void testMultiplesOfFive() {
        for (int i = 5; i < 100; i += 5) {
            if (i % 3 != 0) {
                assertEquals("Buzz", fb.evaluate(i));
            }
        }
    }

    @Test
    public void testMultiplesOfFifteen() {
        for (int i = 15; i < 100; i += 15) {
            assertEquals("FizzBuzz", fb.evaluate(i));
        }
    }

}
