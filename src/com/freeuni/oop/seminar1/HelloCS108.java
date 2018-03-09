package com.freeuni.oop.seminar1;

import java.util.Scanner;

/**
 * com.freeuni.oop.seminar2extra.Hello World.
 */
public class HelloCS108 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // This is how reading from console is done in Java
        System.out.print("Enter your name: ");
        String stringFromConsole = scan.next();

        System.out.print("Enter your age: ");
        int intFromConsole = scan.nextInt();

        System.out.println("com.freeuni.oop.seminar1.Hello " + stringFromConsole + " " + intFromConsole + "!");
    }

}
