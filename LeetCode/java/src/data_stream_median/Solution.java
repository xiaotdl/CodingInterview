package data_stream_median;

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
    class MedianFinder {
        PriorityQueue<Integer> maxHeap; // size: x or x + 1
        PriorityQueue<Integer> minHeap; // size: x

        MedianFinder(int size) {
            maxHeap = new PriorityQueue<>((size / 2) + 1, Collections.reverseOrder());
            minHeap = new PriorityQueue<>(size / 2);
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public int getMedian() {
            return maxHeap.peek();
        }
    }
    /*
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int[] res = new int[nums.length];
        MedianFinder mf = new MedianFinder(nums.length);
        for (int i = 0; i < nums.length; i++) {
            mf.addNum(nums[i]);
            res[i] = mf.getMedian();
        }
        return res;
    }
}
