package seminar14.semaphore;

import java.util.ArrayList;

/**
 * SynchronizedLogger class is used to keep track of thread activities
 * Logs are saved synchronously in a list
 */
@SuppressWarnings("WeakerAccess")
public class SynchronizedLogger {
    private static final ArrayList<String> log = new ArrayList<>();

    public static synchronized void log(String str) {
        log.add(Thread.currentThread().getName() + " - " + str);
    }

    public static synchronized void dumpLog() {
        for (String aLog : log) {
            System.out.println(aLog);
        }
    }

}
