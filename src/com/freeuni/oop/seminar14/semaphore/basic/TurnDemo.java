package com.freeuni.oop.seminar14.semaphore.basic;

import java.util.concurrent.*;

/*
Use two Semaphores to get A and B threads to take turns.
This code works.
*/
@SuppressWarnings("AnonymousHasLambdaAlternative")
class TurnDemo {
    private Semaphore aGo = new Semaphore(1);        // a gets to go first
    private Semaphore bGo = new Semaphore(0);

    private void a() {
        // aGo.acquire() -- wrapped in try/catch
        try {
            aGo.acquire();
        } catch (InterruptedException ignored) {
        }
        System.out.println("It's A turn, A rules!");
        bGo.release();
    }

    private void b() {
        try {
            bGo.acquire();
        } catch (InterruptedException ignored) {
        }
        System.out.println("It's B turn, B rules!");
        aGo.release();
    }

    /*
     Q: Suppose a() and b() where synchronized -- what would happen?
     A: deadlock! a thread would take the "this" lock and block its its acquire(),
     and the other thread could never get in to do the matching release().
    */
    private void demo() {
        // Use this to wait for the workers
        // using an initial value of -1
        final Semaphore finished = new Semaphore(-1);

        // Thread that calls b() a lot
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    b();
                }
                finished.release();
            }
        }.start();

        // Thread that calls a() a lot
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    a();
                }
                finished.release();
            }
        }.start();

        // wait for both threads (vs. using join)
        try {
            finished.acquire();
        } catch (InterruptedException ignored) {
        }
        System.out.println("All done!");
    }

    public static void main(String args[]) {
        new TurnDemo().demo();
    }

}
