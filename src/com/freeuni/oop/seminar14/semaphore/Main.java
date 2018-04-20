package com.freeuni.oop.seminar14.semaphore;

public class Main {
    public static void main(String[] args) {
        // Object with shared data
        SharedData sharedData = new SharedData();


        // Create and run threads
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(new TesterTask(sharedData));
            threads[i].start();
        }


        // Wait for all threads to complete and dump log
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.out.println("Main thread interrupted");
            }
        }
        SynchronizedLogger.dumpLog();
    }

}
