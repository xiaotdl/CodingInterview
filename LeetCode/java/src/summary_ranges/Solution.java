package summary_ranges;

import java.util.*;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int start, end;
        start = end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                if (start == end) {
                    res.add(String.format("%d", start));
                }
                else {
                    res.add(String.format("%d->%d", start, end));
                }
                start = end = nums[i];
            }
            end = nums[i];
        }
        if (start == end) {
            res.add(String.format("%d", start));
        }
        else {
            res.add(String.format("%d->%d", start, end));
        }
        return res;
    }
}

class SolutionII {
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();

        Stack<int[]> stack = new Stack<>();
        for (int num : nums) {
            if (stack.isEmpty()) {
                stack.push(new int[]{num, num});
            }
            else {
                if (num == stack.peek()[1] + 1) {
                    stack.peek()[1] = num;
                    continue;
                }

                int[] range = stack.pop();
                res.add(range[0] == range[1] ? "" + range[0] : range[0] + "->" + range[1]);

                stack.push(new int[]{num, num});
            }
        }
        if (!stack.isEmpty()) {
            int[] range = stack.pop();
            res.add(range[0] == range[1] ? "" + range[0] : range[0] + "->" + range[1]);
        }
        return res;
    }
}
