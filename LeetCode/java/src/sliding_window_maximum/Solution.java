package sliding_window_maximum;

import java.util.*;

/**
 * Created by Xiaotian on 6/15/17.
 */
public class Solution {
    // tag: heap, priority queue
    // time: O(n(logk+n))
    // space: O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};

        int[] res = new int[nums.length - k + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        res[0] = maxHeap.peek();

        int l, r;
        for (l = 0, r = k; r < nums.length; l++, r++) {
            maxHeap.remove(nums[l]);
            maxHeap.add(nums[r]);
            res[l + 1] = maxHeap.peek();
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
