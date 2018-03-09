package com.freeuni.oop.seminar4.roman.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.freeuni.oop.seminar4.roman.RomanConverter;
import org.junit.Test;

public class RomanConverterTest {

    @Test
    public void testRomanNumberBasic() {
        checkToRoman(1, "I");
        checkToRoman(2, "II");
        checkToRoman(3, "III");
        checkToRoman(4, "IV");
        checkToRoman(5, "V");
        checkToRoman(6, "VI");
        checkToRoman(7, "VII");
        checkToRoman(8, "VIII");
        checkToRoman(9, "IX");
        checkToRoman(10, "X");
    }

    @Test
    public void testRomanNumberAdvanced() {
        checkToRoman(3999, "MMMCMXCIX");
        checkToRoman(1739, "MDCCXXXIX");
        checkToRoman(689, "DCLXXXIX");
        checkToRoman(49, "XLIX");
        checkToRoman(151, "CLI");
    }

    @Test
    public void testRomanNumberEdge() {
        // OPTIONAL: try yourself, what are the edge cases?
    }

    @Test
    public void testArabicNumberBasic() {
        checkToArabic("I", 1);
        checkToArabic("II", 2);
        checkToArabic("III", 3);
        checkToArabic("IV", 4);
        checkToArabic("V", 5);
        checkToArabic("VI", 6);
        checkToArabic("VII", 7);
        checkToArabic("VIII", 8);
        checkToArabic("IX", 9);
        checkToArabic("X", 10);
    }

    @Test
    public void testArabicNumberAdvanced() {
        checkToArabic("MMMCMXCIX", 3999);
        checkToArabic("MDCCXXXIX", 1739);
        checkToArabic("DCLXXXIX", 689);
        checkToArabic("XLIX", 49);
        checkToArabic("CLI", 151);
    }

    @Test
    public void testArabicNumberEdge() {
        // OPTIONAL: try yourself, what are the edge cases?
    }

    private void checkToRoman(int arabic, String roman) {
        assertThat(RomanConverter.convert(arabic).toRoman(),
                is(equalTo(roman)));
    }

    private void checkToArabic(String roman, int arabic) {
        assertThat(RomanConverter.convert(roman).toArabic(),
                is(equalTo(arabic)));
    }

}
