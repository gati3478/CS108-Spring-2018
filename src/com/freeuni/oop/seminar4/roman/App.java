package com.freeuni.oop.seminar4.roman;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        if (args.length > 1) {
            // In case our program is run from the terminal
            runWithTerminalInputs(args);
        } else {
            // In case our program is run from the editor
            askUserForInput();
        }
    }

    private static void runWithTerminalInputs(String[] args) {
        for (String arg : args) {
            parseAndPrint(arg);
        }
    }

    private static void askUserForInput() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter arabic or roman: ");
        String numeral = scan.nextLine();

        parseAndPrint(numeral);
    }

    private static void parseAndPrint(String input) {
        try {
            int arabic = Integer.parseInt(input);
            String roman = RomanConverter.convert(arabic).toRoman();
            System.out.println("Roman of " + arabic + " is - " + roman);
        } catch (NumberFormatException ex) {
            int arabic = RomanConverter.convert(input).toArabic();
            System.out.println("Arabic of " + input + " is - " + arabic);
        }
    }

}
