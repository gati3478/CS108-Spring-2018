package seminar8.anonymous;

import seminar6.extra.Ingredient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnonymousDemo {

    @SuppressWarnings("Convert2Lambda")
    public static void main(String[] args) {
        Ingredient a = new Ingredient("Apple", 112);
        Ingredient b = new Ingredient("Banana", 236);
        Ingredient b2 = new Ingredient("Banana", 100);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(b2);
        ingredients.add(a);
        ingredients.add(b);

        ingredients.sort(new Comparator<Ingredient>() {
            public int compare(Ingredient a, Ingredient b) {
                // trick: form the neg/0/pos by subtraction
                return (a.getGrams() - b.getGrams());
            }
        });
        System.out.println("Sorted by grams (low to high):" + ingredients);

        ingredients.sort(new Comparator<Ingredient>() {
            public int compare(Ingredient a, Ingredient b) {
                // trick: form the neg/0/pos by subtraction
                return (b.getGrams() - a.getGrams());
            }
        });
        System.out.println("Sorted by grams (high to low):" + ingredients);

        ingredients.sort(new Comparator<Ingredient>() {
            public int compare(Ingredient a, Ingredient b) {
                // trick: form the neg/0/pos by subtraction
                String aName = a.getName().substring(1);
                String bName = b.getName().substring(1);
                return aName.compareTo(bName);
            }
        });
        System.out.println("Sorted by second letter:" + ingredients);

        // Lambda demo
        ingredients.sort((a1, b1) -> {
            // trick: form the neg/0/pos by subtraction
            return (a1.getGrams() - b1.getGrams());
        });

        System.out.println("\nSorted by grams (low to high) [using Java 8 lambda function]:"
                + ingredients);
    }

}
