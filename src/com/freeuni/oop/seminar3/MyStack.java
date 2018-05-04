package com.freeuni.oop.seminar3;

import java.util.Collection;
import java.util.Stack;

/**
 * Custom Stack class with support for generic types.
 */
public class MyStack<T> {

    // Size for initial storage
    private static final int INITIAL_SIZE = 2;

    // Actual underlying storage
    private Object[] arr;

    private int logLen;

    // Static variable example
    public static int seminarGroupId = 3;

    public MyStack() {
        this.arr = new Object[INITIAL_SIZE];
        logLen = 0; // can also omit 'this' keyword
    }

    public void push(T elem) {
        if (arr.length == logLen) {
            grow();
        }
        arr[logLen++] = elem;
    }

    private void grow() {
        Object[] temp = new Object[2 * arr.length];

        // Faster solution for copying arrays
        System.arraycopy(arr, 0, temp, 0, arr.length);

        arr = temp;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        return (T) arr[--logLen];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) arr[logLen - 1];
    }

    public void addAll(Collection<T> coll) {
        for (T temp : coll) {
            push(temp);
        }
    }

    public int size() {
        return logLen;
    }

}
