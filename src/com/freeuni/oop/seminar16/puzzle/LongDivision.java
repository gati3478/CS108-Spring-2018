package com.freeuni.oop.seminar16.puzzle;

public class LongDivision {
    public static void main(String[] args) {
        //noinspection NumericOverflow
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }
}
