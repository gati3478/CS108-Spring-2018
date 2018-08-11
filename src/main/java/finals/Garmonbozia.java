package finals;

import java.util.HashMap;
import java.util.Map;

public class Garmonbozia implements Runnable {

    public static int y = 5;

    public static Map<Integer, Integer> ints = new HashMap<>();

    static {
        ints.put(1, -64);
        ints.put(2, -64);
        ints.put(3, 256);
        ints.put(4, 256);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread());

        if (ints.get(3) == ints.get(4)) {
            y++;
        } else {
            y--;
        }

        System.out.println(y);
    }

    public static void main(String args[]) {
        Thread t1 = new Thread(new Garmonbozia());

        if (ints.get(1) == ints.get(2)) {
            t1.run();
        } else {
            t1.start();
        }

        String j = "jack" + "pots";

        if (j == "jack" + "pots") {
            t1.start();
        } else {
            t1.run();
        }

    }
}
