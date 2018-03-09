package com.freeuni.oop.seminar3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Various experiments for seminar 2.
 */
public class Experiments {

    public static void main(String[] args) {
        genericLists();
        genericMaps();
        genericsComparison();
        problem();
    }

    private static void genericLists() {
        // ** Likewise, can make a List<Integer>
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            ints.add(new Integer(i * i));
        }

        // No casts needed here -- it knows they are Integer
        int sum = ints.get(0).intValue() + ints.get(1).intValue();

        // With auto Unboxing, can just write it like this...
        sum = ints.get(0) + ints.get(1);

        // Can go back and forth between typed Collections and untyped "raw" // forms -- may get a warning.
        List<String> genList = new ArrayList(); // warning
        List rawList = new ArrayList<String>(); // no warning rawList.add("hello"); // warning
        genList = rawList; // warning
        rawList = genList; // no warning
    }

    private static void genericMaps() {
        // ** Make a map, specifying both key and value types
        Map<Integer, String> map = new HashMap<>();

        // Map Integers to their words
        map.put(new Integer(1), "one");
        map.put(new Integer(2), "two");
        map.put(3, "three"); // Let the autoboxing make the Integer
        map.put(4, "four");

        String s = map.get(new Integer(3)); // returns type String
        s = map.get(3); // Same as above, with autoboxing
        // map.put("hi", "there"); // NO does not compile

        // ** Auto unboxing -- converts between Integer and int
        Integer intObj = new Integer(7);
        int sum = intObj + 3; // intObj unboxes automatically to an int, sum is 10

        // ** More complex example -- map strings to lists of Integer
        HashMap<String, List<Integer>> counts = new HashMap<>();

        List<Integer> evens = new ArrayList<>();
        evens.add(2);
        evens.add(4);
        evens.add(6);
        counts.put("evens", evens);

        // Get the List<Integer> back out...
        List<Integer> evens2 = counts.get("evens");
        sum = evens2.get(0) + evens2.get(1); // unboxing here, sum is 6
    }

    private static void problem() {
        Object o;
        String s = "hello";
        o = s; //fine

        List<Object> lo = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        // lo = ls;  // NO does not compile

        // The following would be a big prob if above were allowed.
        // If we allow ls to be in lo, we lose track of the constraint
        // that ls should only contain Strings.
        lo.add(new Integer(3));

        // doSomething(ls); // NO same problem

        // The point: you can assign a pointer of type sub to a pointer of type
        // super. However, you cannot assign a pointer type container(sub) to a
        // pointer of type container(super).
        // Therefore Collection<Object> will not work as a sort of catch-all
        // type for any sort of collection.
    }

    private static void genericsComparison() {
        System.out.println("\nGenerics comparison: \n");
        List<Object> lo = new ArrayList<>();
        List<String> ls = new ArrayList<>();

        System.out.println(lo.getClass() == ls.getClass());
    }

}
