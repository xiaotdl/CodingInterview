package kth_largest_element_in_an_array;

import java.util.*;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class Solution {
    // minheap
    // tag: array, heap
    // time: O(nlogk)
    // space: O(k)
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num); // == offer(num)

            if (pq.size() > k) {
                pq.remove(); // == poll()
            }
        }
        return pq.peek();
    }
}
