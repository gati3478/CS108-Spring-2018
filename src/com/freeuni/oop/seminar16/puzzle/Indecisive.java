package com.freeuni.oop.seminar16.puzzle;

@SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
public class Indecisive {
    public static void main(String[] args) {
        System.out.println(decision());
    }

    private static boolean decision() {
        try {
            return true;
        } finally {
            return false;
        }
    }
}
