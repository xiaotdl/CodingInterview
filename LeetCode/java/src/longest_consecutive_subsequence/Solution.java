package longest_consecutive_subsequence;

import java.util.*;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // one pass
    // tag: array, hash
    // time: O(n)
    // space: O(n)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int res = 0;
        Map<Integer, Integer> map = new HashMap<>(); // num2lenOfSequence
        for (int num : nums) {
            if (map.containsKey(num)) continue; // skip duplicate num
            int leftSeqLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
            int rightSeqLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
            int totalSeqLen = leftSeqLen + rightSeqLen + 1;
            map.put(num, totalSeqLen);
            res = Math.max(res, totalSeqLen);
            // update boundries' sequence length only
            if (leftSeqLen != 0) map.put(num - leftSeqLen, totalSeqLen);
            if (rightSeqLen != 0) map.put(num + rightSeqLen, totalSeqLen);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}

class SolutionII {
    // two pass
    // tag: array, set
    // time: O(n)
    // space: O(n)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - 1)) continue; // skip non-starting num of a sequence

            int lenOfSeq = 1;
            int nextNum = nums[i] + 1;
            while (set.contains(nextNum)) {
                nextNum++;
                lenOfSeq++;
            }
            res = Math.max(res, lenOfSeq);
        }
        return res;
    }
}
