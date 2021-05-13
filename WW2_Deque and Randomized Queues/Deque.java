import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;



public class Deque<Item> implements Iterable<Item> {

    // private inner class
    private class Node {
        private Item item;
        private Node next;
        private Node before;
    }

    private Node first, last;
    private int num;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        num = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return num == 0;
    }

    // return the number of items on the deque
    public int size() {
        return num;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkExceptionIAE(item);
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.before = null;
        if (isEmpty()) last = first;
        else {
            first.next = oldfirst;
            oldfirst.before = first;
        }
        num++;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkExceptionIAE(item);
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else {
            last.before = oldlast;
            oldlast.next = last;
        }
        num++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkExceptionNEE();
        Item item = first.item;

        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.before = null;
        }
        num--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkExceptionNEE();
        Item item = last.item;

        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.before;
            last.next = null;
        }
        num--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    private void checkExceptionIAE(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkExceptionNEE() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.println("isEmpty : " + deque.isEmpty());
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }
        deque.addLast(3);
        StdOut.println("isEmpty : " + deque.isEmpty());
        StdOut.println("removeLast : " + deque.removeFirst());
        StdOut.println("size : " + deque.size());
        while (!deque.isEmpty()) {
            StdOut.println(deque.removeFirst());
        }
        StdOut.println("size : " + deque.size());
    }

}