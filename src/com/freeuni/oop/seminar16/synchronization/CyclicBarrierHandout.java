package com.freeuni.oop.seminar16.synchronization;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierHandout {
    private static final int NUM_THREADS = 5;
    static CyclicBarrier barrier;

    static class TestListThread extends Thread {

        public void run() {
            System.out.println(getName() + " is working");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " is done");
        }
    }

    public static void main(String[] args) {
        barrier = new CyclicBarrier(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; ++i) {
            new TestListThread().start();
        }

        System.out.println("Main Thread Done");
    }

}
