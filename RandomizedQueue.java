package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items in the data structure. 
 * Create a generic data type RandomizedQueue that implements the following API:
 * 
 * Corner cases.  Throw the specified exception for the following corner cases:
 * Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 * 
 * implement the randomized queue use resizing-array
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    @SuppressWarnings("unchecked")
    //construct an empty randomized queue
	public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    //is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }
    //return the number of items on the randomized queue
    public int size() {
        return n;
    }
    //add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (n == a.length) resize(2 * a.length);
        if (n == 0) {
            a[n++] = item;
            return;
        }
        int randomIndex = StdRandom.uniform(n);
        Item temp = a[randomIndex];
        a[randomIndex] = item;
        a[n++] = temp;
    }

    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    //remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (n == a.length / 4) resize(a.length / 2);
        int randomIndex = StdRandom.uniform(n);
        Item item = a[randomIndex];
        a[randomIndex] = a[--n];
        a[n] = null; // to prevent loitering
        return item;
    }
    //return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return a[StdRandom.uniform(n)];
    }

    @Override
    //return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i;
        private int[] randomIndices;
        public ArrayIterator() {
            i = 0;
            randomIndices = new int[n];
            for (int j = 0; j < n; j++) {
                randomIndices[j] = j;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[randomIndices[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // Unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("sample:" + queue.sample());
        System.out.println("dequeue");
        while (!queue.isEmpty()) System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }
}