package seminar4.roman;

public class Arabic extends Number {

    private int arabic;

    public Arabic(int arabic) {
        this.arabic = arabic;
    }

    public String toRoman() {
        StringBuilder roman = new StringBuilder();

        for (Part part : ROMAN_NUMERALS) {
            while (arabic >= part.getArabic()) {
                roman.append(part.getRoman());
                arabic -= part.getArabic();
            }
        }

        return roman.toString();
    }
}
