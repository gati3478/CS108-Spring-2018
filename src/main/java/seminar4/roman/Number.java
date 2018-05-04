package seminar4.roman;

import java.util.ArrayList;
import java.util.List;

public abstract class Number {

    public static class Part {
        private final int arabic;
        private final String roman;

        public Part(int arabic, String roman) {
            this.arabic = arabic;
            this.roman = roman;
        }

        public int getArabic() {
            return this.arabic;
        }

        public String getRoman() {
            return this.roman;
        }
    }

    protected static final List<Part> ROMAN_NUMERALS;

    static {
        // This code will be executed only once during the
        // creation of the Number class (this is static {} behaviour
        ROMAN_NUMERALS = new ArrayList<>();

        ROMAN_NUMERALS.add(new Part(1000, "M"));
        ROMAN_NUMERALS.add(new Part(900, "CM"));
        ROMAN_NUMERALS.add(new Part(500, "D"));
        ROMAN_NUMERALS.add(new Part(400, "CD"));
        ROMAN_NUMERALS.add(new Part(100, "C"));
        ROMAN_NUMERALS.add(new Part(90, "XC"));
        ROMAN_NUMERALS.add(new Part(50, "L"));
        ROMAN_NUMERALS.add(new Part(40, "XL"));
        ROMAN_NUMERALS.add(new Part(10, "X"));
        ROMAN_NUMERALS.add(new Part(9, "IX"));
        ROMAN_NUMERALS.add(new Part(5, "V"));
        ROMAN_NUMERALS.add(new Part(4, "IV"));
        ROMAN_NUMERALS.add(new Part(3, "III"));
        ROMAN_NUMERALS.add(new Part(2, "II"));
        ROMAN_NUMERALS.add(new Part(1, "I"));
    }

}
