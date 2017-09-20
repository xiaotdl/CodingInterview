package find_median_from_data_stream;

import java.util.*;

/**
 * Created by Xiaotian on 9/19/17.
 */
public class Solution {
    // tag: heap
    // time:
    //   addNum: O(logn)
    //   findMedian: O(1)
    // space: O(n)
}

class MedianFinder {
    PriorityQueue<Integer> maxHeap; // size: x or x + 1
    PriorityQueue<Integer> minHeap; // siez: x

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue(1024, Collections.reverseOrder());
        minHeap = new PriorityQueue();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
        return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
