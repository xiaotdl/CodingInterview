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

class SolutionII {
    // quickselect
    // tag: array
    // time: O(n)
    // space: O(1)
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return -1;

        k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p < k) {
                l = p + 1;
            }
            else if (p > k) {
                r = p - 1;
            }
            else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, r, i);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
