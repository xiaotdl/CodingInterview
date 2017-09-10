package contains_duplicate;

import java.util.*;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // tag: array, set
    // time: O(n)
    // space: O(n)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
}
