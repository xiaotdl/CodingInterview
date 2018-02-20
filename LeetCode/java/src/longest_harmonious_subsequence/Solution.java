package longest_harmonious_subsequence;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(n)
    public int findLHS(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>(); // num2cnt
        for (int num : nums) {
            m.put(num, m.containsKey(num) ? m.get(num) + 1 : 1);
        }

        int maxLen = 0;
        for (int n : nums) {
            if (m.containsKey(n - 1)) {
                maxLen = Math.max(maxLen, m.get(n) + m.get(n - 1));
            }
            if (m.containsKey(n + 1)) {
                maxLen = Math.max(maxLen, m.get(n) + m.get(n + 1));
            }
        }
        return maxLen;
    }
}
