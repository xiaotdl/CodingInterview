package two_sum_iii;

import java.util.*;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
}
class TwoSum {
    // tag: hash table
    // time: O(n), one pass through all keys
    // space: O(n)

    Map<Integer, Integer> val2cnt = new HashMap<>();

    // Add the number to an internal data structure.
    public void add(int number) {
        val2cnt.put(number, val2cnt.containsKey(number) ? val2cnt.get(number) + 1 : 1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (Integer val : val2cnt.keySet()) {
            int target = value - val;
            if (!val2cnt.containsKey(target)) continue;
            if (val == target && val2cnt.get(val) >= 2) return true;
            if (val != target && val2cnt.get(target) >= 1) return true;
        }
        return false;
    }
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
