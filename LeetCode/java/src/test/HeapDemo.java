package test;

import java.util.*;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class HeapDemo {
    private final static int HEAP_SIZE = 10; //size of heap

    //INNER CLASS
    static class maxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare (Integer x, Integer y) {
            return y-x; //reverse order
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(HeapDemo.HEAP_SIZE);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(HeapDemo.HEAP_SIZE, new maxHeapComparator());

        for(int i=1; i<=HeapDemo.HEAP_SIZE; ++i){
            int data = new Random().nextInt(100) +1; //number between 0 to 100
            minHeap.add(data);
            maxHeap.add(data);
        }

        System.out.print("\nMIN Heap : ");
        Iterator<Integer> iter = minHeap.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }

        System.out.print("\nMAX Heap : ");
        iter = maxHeap.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
    }
}
