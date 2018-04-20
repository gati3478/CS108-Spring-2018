package com.freeuni.oop.seminar13.threads;

@SuppressWarnings("Convert2Lambda")
public class AnonRunnable {

    private static AnonRunnable instance;

    public static AnonRunnable getInstance() {
        if (instance == null) {
            instance = new AnonRunnable();
        }
        return instance;
    }

    private AnonRunnable() {
        //
    }

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("git gud");
            }
        });
        a.start();
    }

}
