import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node head;
    private Node tail;

    private class Node {
        private Node next;
        private Node prev;
        private Item data;
    }

    public Deque() {
        head = null;
        tail = null;
        this.n = 0;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int length() {
        return this.n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException("wrong argument");

        Node newNode = new Node();
        newNode.data = item;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = null;
            newNode.prev = null;
        } else {
            Node oldHead = head;
            head = newNode;
            oldHead.prev = newNode;
            newNode.next = oldHead;
            newNode.prev = null;
        }
        this.n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException("wrong argument");

        Node newNode = new Node();
        newNode.data = item;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = null;
            newNode.prev = null;
        } else {
            Node oldTail = tail;
            tail = newNode;
            oldTail.next = newNode;
            newNode.prev = oldTail;
            newNode.next = null;
        }
        this.n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty list");

        Item item = head.data;
        if (this.n > 1) {
            Node newHead = head.next;
            newHead.prev = null;
            head = newHead;
        }
        this.n--;
        if (this.n == 1) tail = head;

        if (isEmpty()) {
            head = null;
            tail = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty list");

        Item item = tail.data;
        if (this.n > 1) {
            Node newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
        }
        this.n--;
        if (this.n == 1) head = tail;

        if (isEmpty()) {
            head = null;
            tail = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("empty");

            Item item = node.data;
            node = node.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("no such method");
        }
    }

    public static void main(String[] args) {
        Deque<String> de = new Deque<String>();
        de.addFirst("1");
        de.addFirst("2");
        de.addFirst("3");
        de.addFirst("4");
        de.addFirst("5");
        de.addFirst("6");
        de.addFirst("7");
        de.addFirst("8");
        de.addLast("1");
        de.addLast("2");
        de.addLast("3");
        de.addLast("4");
        de.addLast("5");
        de.addLast("6");
        de.addLast("7");
        de.addLast("8");
        Iterator<String> itr = de.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}
