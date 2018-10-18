import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int top;
    private Item[] randomQueue;

    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[2];
        this.top = 0;
    }                // construct an empty randomized queue

    public boolean isEmpty() {
        return this.top == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return this.top;
    }                       // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("wrong argument");
        }
        if (top == randomQueue.length) {
            resize(2 * randomQueue.length);
        }
        randomQueue[top++] = item;
    }          // add the item

    private void resize(int i) {
        Item[] copyArray = (Item[]) new Object[i];
        for (int p = 0; p < top; p++) {
            copyArray[p] = randomQueue[p];
        }
        randomQueue = copyArray;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty queue");

        int index = StdRandom.uniform(top);
        Item item = randomQueue[index];
        randomQueue[index] = randomQueue[--top];
        randomQueue[top] = null;
        if (top > 0 && top == randomQueue.length / 4) resize(randomQueue.length / 2);
        return item;
    }                   // remove and return a random item

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty queue");
        int index = StdRandom.uniform(top);
        return randomQueue[index];
    }                     // return a random item (but do not remove it)

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }         // return an independent iterator over items in random order

    private class QueueIterator implements Iterator<Item> {
        private Item[] array;
        private int index;

        public QueueIterator() {
            array = (Item[]) new Object[top];
            for (int i = 0; i < top; i++) {
                array[i] = randomQueue[i];
            }
            StdRandom.shuffle(array);
        }

        @Override
        public boolean hasNext() {
            return index < top;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("empty");
            return array[index++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Not supported");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("5");
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        Iterator<String> itr = rq.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
