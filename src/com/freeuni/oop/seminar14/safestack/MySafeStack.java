package com.freeuni.oop.seminar14.safestack;

import java.util.Collection;

/**
 * Custom Stack class with support for generic types.
 */
@SuppressWarnings("WeakerAccess")
public class MySafeStack<T> {

    // Size for initial storage
    private static final int INITIAL_SIZE = 2;

    // Actual underlying storage
    private Object[] arr;

    private int logLen;

    // Locking mechanism
    private final Object lockFirst;
    private final Object lockSecond;

    public MySafeStack() {
        arr = new Object[INITIAL_SIZE];
        logLen = 0; // can also omit 'this' keyword

        lockFirst = new Object();
        lockSecond = new Object();
    }

    public void push(T elem) {
        synchronized (lockFirst) {
            synchronized (lockSecond) {
                if (arr.length == logLen) {
                    grow();
                }
                arr[logLen++] = elem;
            }
        }
    }

    private void grow() {
        Object[] temp = new Object[2 * arr.length];

        // Faster solution for copying arrays
        System.arraycopy(arr, 0, temp, 0, arr.length);

        arr = temp;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        synchronized (lockFirst) {
            synchronized (lockSecond) {
                return (T) arr[--logLen];
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        synchronized (lockFirst) {
            return (T) arr[logLen - 1];
        }
    }

    public int size() {
        synchronized (lockSecond) {
            return logLen;
        }
    }

    public void addAll(Collection<T> coll) {
        synchronized (lockFirst) {
            synchronized (lockSecond) {
                for (T temp : coll) {
                    push(temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        MySafeStack<Integer> mySafeStack = new MySafeStack<>();

        Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new StackAbuser(mySafeStack);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.out.println("Main thread interrupted");
            }
        }

        System.out.println("Size: " + mySafeStack.size());
    }

}

class StackAbuser extends Thread {
    private MySafeStack<Integer> stack;

    StackAbuser(MySafeStack<Integer> stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        for (int i = 0; i < 400; ++i) {
            stack.push(i);
            stack.push(stack.peek());
            stack.push(stack.size());

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stack.pop();
        }
    }

}
