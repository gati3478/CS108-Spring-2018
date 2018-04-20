package com.freeuni.oop.seminar14.semaphore;

public class SharedData {
    private final Semaphore semaphore = new Semaphore(2);

    public int getValue() {
        try {
            semaphore.acquire();
            SynchronizedLogger.log("acquired lock");

            // Critical region guarded with semaphore
            // do something (sleep for a while)
            int sleepTime = (int) (Math.random() * 5) * 1000;
            Thread.sleep(sleepTime);
            int answer = 42;

            semaphore.release();
            SynchronizedLogger.log("released lock");

            return answer;
        } catch (InterruptedException ex) {
            System.out.println("getValue method was interrupted");
        }

        return 0;
    }

}
