package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue
 *  that supports adding and removing items from either the front or the back of the data structure. 
 *  Create a generic data type Deque that implements the following API:
 *  
 * Corner cases.  Throw the specified exception for the following corner cases:

 * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator. 
 *  
 * Use linked list which is a linear data structure where each element is a separate object
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n; //size of the deck
    
    //construct an empty deque
    public Deque() {
        n = 0;
    }
    //is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
    //add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("input must be not null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (last == null) last = first;//if last node is empty
        else first.next.prev = first;
        n++;
    }
    //add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("input must be not null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (first == null) first = last;
        else last.prev.next = last;
        n++;
    }
    //remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        n--;
        if (isEmpty()) {
            last = null;
            first = null;
        } 
        else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }
    //remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    //the node class
    private class Node {

        private Item item;
        private Node next;
        private Node prev;
    }
    //return an iterator over time in order from front to end
    private class ListIterator implements Iterator<Item> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
    //unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        System.out.println(deque.isEmpty());
/*        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(1);
        System.out.println(deque.removeFirst());
        deque.addFirst(2);
        System.out.println(deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);*/
    }
}