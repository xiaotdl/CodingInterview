package two_sum_iii;

import java.util.*;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
}

class TwoSum {
    // tag: hash
    // time:
    //   add: O(1)
    //   find: O(n)
    // space: O(n)
    Map<Integer, Integer> m; // num2cnt
    public TwoSum() {
        m = new HashMap<>();
    }

    // O(1)
    public void add(int number) {
        m.put(number, m.getOrDefault(number, 0) + 1);
    }

    // O(n)
    public boolean find(int sum) {
        for (Integer k : m.keySet()) {
            int target = sum - k;
            if (target == k && m.get(k) >= 2) return true;
            if (target != k && m.containsKey(target)) return true;
        }
        return false;
    }
}



// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);


class TwoSumII {
    // tag: hash
    // time:
    //   add: O(n)
    //   find: O(1)
    // space: O(n)
    Set<Integer> nums;
    Set<Integer> sums;
    public TwoSumII() {
        nums = new HashSet<>();
        sums = new HashSet<>();
    }

    // O(n)
    public void add(int number) {
        if (nums.contains(number)) {
            sums.add(number + number);
            return;
        }
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext()) {
            sums.add(it.next() + number);
        }
        nums.add(number);

    }

    // O(1)
    public boolean find(int value) {
        return sums.contains(value);
    }
}