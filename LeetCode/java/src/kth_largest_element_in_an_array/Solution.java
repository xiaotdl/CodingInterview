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
    // time:
    //   avg: O(n)
    //   worst: O(n^2), e.g. each time selected worst pivot, which introduces O(n) swapping
    // space: O(1)
    public int findKthLargest(int[] nums, int k) {
        return qSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int qSelect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];

        int p = partition(nums, l, r);

        if (p < k) {
            return qSelect(nums, p + 1, r, k);
        }
        else if (p > k) {
            return qSelect(nums, l, p - 1, k);
        }
        else return nums[p];
    }

    private int partition(int[] nums, int l, int r) {
        int p = r;
        int i = l;
        for (int j = l; j <= r; j++) {
            if (nums[j] < nums[p]) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, p);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
