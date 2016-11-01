package two_sum_iii;

import java.util.HashMap;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
}
private class TwoSum {
    // tag: hash table
    // time: O(n), one pass through all keys
    // space: O(n)

    HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
    public void add(int number) {
        int currCount = counter.get(number) == null ? 1 : counter.get(number);
        this.counter.put(number, currCount + 1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (Integer k : this.counter.keySet()) {
            int num1 = Integer.valueOf(k);
            int num2 = value - num1;
            if (counter.containsKey(num2)) {
                if (num1 == num2 && counter.get(num2) >= 2) {
                    return true;
                }
                if (num1 != num2 && counter.get(num2) >= 1) {
                    return true;
                }
            }
        }
        return false;
    }
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
