package _132_pattern;

import java.util.*;

/**
 * Created by Xiaotian on 2/22/18.
 */
public class Solution {
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int prevMin = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (prevMin == nums[j]) continue;
            for (int k = j + 1; k < n; k++) {
                if (prevMin < nums[k] && nums[k] < nums[j]) return true;
            }
            prevMin = Math.min(prevMin, nums[j]);
        }
        return false;
    }
}

class SolutionII {
    // stack is always decreasing from bottom to up
    //         |
    //     |  ||
    //     | |||
    // top ||||| bottom
    // _2要从_3右面所有数里找比_3小的最大的数，这样_1的范围(-inf, _2)才最大
    // tag: stack
    // time: O(n)
    // space: O(n)
    public boolean find132pattern(int[] nums) {
        int _2 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int _1 = nums[i];
            int _3 = nums[i];
            if (_1 < _2) {
                return true;
            }
            else { // _1 >= _2
                // _3 > _2: 找到_3右边比_3小的最大的数
                while (!stack.isEmpty() && _3 > stack.peek()) {
                    _2 = stack.peek();
                    stack.pop();
                }
            }
            stack.push(_1);
        }
        return false;
    }
}

