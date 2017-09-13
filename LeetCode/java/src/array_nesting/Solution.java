package array_nesting;

import java.util.*;

/**
 * Created by Xiaotian on 9/13/17.
 */
public class Solution {
    // TLE
    // tag: array, hash
    // time: O(n^2)
    // space: O(n)
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = new HashSet<>();
            int j = i;
            while (!set.contains(nums[j])) {
                set.add(nums[j]);
                j = nums[j];
            }
            res = Math.max(res, set.size());
        }
        return res;
    }
}

class SolutionII {
    // TLE
    // tag: array
    // time: O(n^2)
    // space: O(1)
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            int len = 1;
            int j = nums[i];
            while (nums[j] != start) {
                len++;
                j = nums[j];
            }
            res = Math.max(res, len);
        }
        return res;
    }
}

class SolutionIII {
    // tag: array
    // time: O(n)
    // space: O(n)
    public int arrayNesting(int[] nums) {
        int res = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            int start = nums[i];
            visited[i] = true;
            int len = 1;
            int j = nums[i];
            while (nums[j] != start) {
                len++;
                visited[j] = true;
                j = nums[j];
            }
            res = Math.max(res, len);
        }
        return res;
    }
}

class SolutionIV {
    // similar to SolutionIII, in-place records visited num by fliping num to -num
    // tag: array
    // time: O(n)
    // space: O(1)
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) continue;

            int start = nums[i];
            int len = 1;
            int j = nums[i];
            nums[i] *= -1;
            while (Math.abs(nums[j]) != start) {
                len++;
                nums[j] *= -1;
                j = Math.abs(nums[j]);
            }
            res = Math.max(res, len);
        }
        return res;
    }
}
