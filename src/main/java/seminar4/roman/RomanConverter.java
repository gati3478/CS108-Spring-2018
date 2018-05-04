package seminar4.roman;

public class RomanConverter {

    public static Arabic convert(int arabic) {
        return new Arabic(arabic);
    }

    public static Roman convert(String roman) {
        return new Roman(roman);
    }
}
