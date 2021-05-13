import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int size = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int i =size; i>0; i--){
            StdOut.println(queue.dequeue());
            size--;
        }
    }
}