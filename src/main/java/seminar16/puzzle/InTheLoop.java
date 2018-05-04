package seminar16.puzzle;

@SuppressWarnings("ConstantConditions")
public class InTheLoop {
    private static final int END = Integer.MAX_VALUE;
    private static final int START = END - 100;

    public static void main(String[] args) {
        int count = 0;
        for (int i = START; i <= END; ++i) {
            ++count;
        }
        System.out.println(count);
    }

}
