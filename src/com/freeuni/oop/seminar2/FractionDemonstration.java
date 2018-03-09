package com.freeuni.oop.seminar2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates Fraction class
 */
public class FractionDemonstration {

    public static void main(String[] args) {
        // Adding 1/2 to 1/2
        System.out.println("Addition:\n");
        Fraction fr1 = new Fraction(1, 2);
        Fraction fr2 = new Fraction(1, 2);

        Fraction sum = fr1.add(fr2);

        System.out.println("Sum of " + fr1 + " and " + fr2 + " is " + sum);
        System.out.println("Sum evaluation: " + sum.evaluate() + "\n");

        // Comparing
        System.out.println("Comparing:\n");
        System.out.println(fr1.equals(fr2));
        System.out.println(fr1.equals(new Fraction(4)));

        Fraction fr1Copy = new Fraction(fr1);

        System.out.println(fr1.equals(fr1Copy));

        Fraction fr1Same = fr1;
        System.out.println(fr1.equals(fr1Same));

        // Some various string behaviors demonstrated
        stringBehaviours();

        // HashMap behaviours on Integer
        hashMapBehaviours();

        // Putting Fraction objects in Set
        fractionsInSet();
    }

    private static void fractionsInSet() {
        System.out.println("\nSet:\n");
        Set<Fraction> s = new HashSet<>();

        Fraction fr1 = new Fraction(1, 2);
        Fraction fr2 = new Fraction(1, 2);
        Fraction fr3 = new Fraction(fr1);
        Fraction fr4 = fr1;
        Fraction fr5 = new Fraction(123);

        s.add(fr1);
        s.add(fr2);
        s.add(fr3);
        s.add(fr4);
        s.add(fr5);

        System.out.println(s.size());
    }

    private static void hashMapBehaviours() {
        System.out.println("\nHashMap:\n");
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 5);
        m.put("c", 5);

        m.put("d", 123054);
        m.put("e", 123054);

        System.out.println(m.get("a") == m.get("c")); // Can work on *some* cases, but unsafe
        System.out.println(m.get("d") == m.get("e"));

        System.out.println(m.get("a").equals(m.get("c")));
    }

    private static void stringBehaviours() {
        System.out.println("\nStrings:\n");
        String a = "ab";
        String b = "ab";
        String c = "a" + "b";
        String d = "abc".substring(0, 2);

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(a.equals(d));

        String e = "a";
        String f = e + "b";
        System.out.println(a == f);
        System.out.println(a.equals(f));
    }
}
