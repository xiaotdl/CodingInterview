package sliding_window_maximum;

import java.util.*;

/**
 * Created by Xiaotian on 6/15/17.
 */
public class Solution {
    // tag: heap, priority queue
    // time: O(nlogk)
    // space: O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k == 0 || nums.length < k) return new int[0];
        int[] res = new int[nums.length - k + 1];
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (PriorityQueue<? extends Integer>) Collections.reverseOrder()
        );
        for (int i = 0; i < nums.length; i++) {
            if (i > k - 1) pq.remove(nums[i - k]);
            pq.add(nums[i]);
            if (i >= k - 1) res[i - k + 1] = pq.peek();
        }
        return res;
    }
}
class SolutionII {
    // tag: deque
    // time: O(n)
    // space: O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove deque's first num(largest) if it goes out of window
            if (!deque.isEmpty() && deque.peekFirst() == i - k) deque.poll();
            // remove all nums smaller than nums[i] from deque tail, so that deque is in descending order
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.removeLast();
            // add new num to deque
            deque.addLast(i);
            // max num in the window is the first in the deque
            if (i >= k - 1) res[i - k + 1] = nums[deque.peekFirst()];
        }
        return res;
    }
}
