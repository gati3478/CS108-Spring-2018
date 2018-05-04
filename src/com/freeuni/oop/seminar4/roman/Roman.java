package com.freeuni.oop.seminar4.roman;

public class Roman extends Number {

    private String roman;

    public Roman(String roman) {
        this.roman = roman;
    }

    public int toArabic() {
        int arabic = 0;
        String currRoman = roman;

        for (Part part : ROMAN_NUMERALS) {
            while (currRoman.startsWith(part.getRoman())) {
                arabic += part.getArabic();
                currRoman = currRoman.substring(part.getRoman().length());
            }
        }

        return arabic;
    }

}
