import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int num;

    // construct an empty randomized queue
//    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        arr = (Item[]) new Object[2];
        num = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return num == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return num;
    }

//    @SuppressWarnings("unchecked")
    private void resize(int n) {
        Item[] resizedarr = (Item[]) new Object[n];
        for (int i = 0; i < num; i++) {
            resizedarr[i] = arr[i];
        }
        arr = resizedarr;
    }

    // add the item to the back
    // double size of array s[] when array is full.
    public void enqueue(Item item) {
        checkExceptionIAE(item);
        if (num == arr.length) {
            resize(arr.length * 2);
        }
        arr[num++] = item;
    }

    // remove and return a random item
    // halve size of array s[] when array is one-quarter full.
    public Item dequeue() {
        checkExceptionNEE();
        if (num == arr.length / 4) {
            resize(arr.length / 2);
        }
        int random = StdRandom.uniform(0, num);
        Item ruturnitem = arr[random];

        arr[random] = null;

        for (int i = random; i < num-1; i++) {
            arr[i] = arr[i+1];
        }
        arr[num-1] = null;
        num--;

        return ruturnitem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkExceptionNEE();
        int i = StdRandom.uniform(0, num);
        return arr[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i;
        private final int[] intarr;
        public ListIterator() {
            i = 0;
            intarr = new int[num];
            for (int j = 0; j < num; j++) {
                intarr[j] = j;
            }
            StdRandom.shuffle(intarr);
        }

        public boolean hasNext() {
            return i < num;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            else {
                return arr[intarr[i++]];
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
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        StdOut.println("sample: " + queue.sample());
        StdOut.println("size : " + queue.size());
        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
        }
        StdOut.println("size : " + queue.size());
    }

}