package seminar7.myqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> {

    private Node head;
    private Node tail;

    private static class Node {
        Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        Object data;
        Node prev;
        Node next;
    }

    public void add(E e) {
        Node newNode = new Node(e, tail, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        E value = (E) head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Iterator<E> iterator() {
        return new MyQueueIterator<>();
    }

    // This is inner class
    private class MyQueueIterator<E> implements Iterator<E> {
        // to keep things simple we assume no one else may change
        // the list as we are iterating through it.

        MyQueueIterator() {
            currNode = head;
        }

        Node currNode;
        Node prevNode;
        boolean removeCalled = false;
        // can only call remove once per call to next

        @Override
        public boolean hasNext() {
            return currNode != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (currNode == null) {
                throw new NoSuchElementException();
            }

            E value = (E) currNode.data;
            prevNode = currNode;
            currNode = currNode.next;
            removeCalled = false;
            return value;
        }

        @Override
        public void remove() {

            // if we've already removed a node (can only call once
            // per call to next) or if we're the first node, remove
            // is illegal
            if (removeCalled || prevNode == null) {
                throw new IllegalStateException();
            }

            if (prevNode == head) {
                head = currNode;
                if (currNode != null) {
                    currNode.prev = null;
                }
            } else {
                prevNode.prev.next = currNode;
                if (currNode != null) {
                    currNode.prev = prevNode.prev;
                }
            }
            removeCalled = true;
        }

    }

}
