package contains_duplicate_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // tag: array, hashmap
    // time: O(n)
    // space: O(n)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // num2indexes
        for (int i = 0; i < nums.length ; i++) {
            if (map.containsKey(nums[i])) {
                for (int j : map.get(nums[i])) {
                    if (i - j <= k) return true;
                }
            }
            else {
                map.put(nums[i], new ArrayList<Integer>());
            }
            map.get(nums[i]).add(i);
        }
        return false;
    }
}
