package k_empty_slots;

import java.util.*;

/**
 * Created by Xiaotian on 3/18/18.
 */
public class Solution {
    // Ref: https://leetcode.com/problems/k-empty-slots/discuss/107942/Java-Accepted-Solution
    // In day order, put position into treeset
    // On each day, calculate if there is qualified empty slots between (prevPos..k..currPos) or (currPos..k..nextPos)
    // tag: treeset
    // time: O(nlogn)
    // space: O(n)
    public int kEmptySlots(int[] flowers, int k) { // flowers: day2pos
        TreeSet<Integer> ts = new TreeSet<>(); // stores all flowers blooming so far
        for (int i = 0; i < flowers.length; i++) {
            int currDay = i + 1;
            int currPos = flowers[i];

            Integer prevPos = ts.lower(currPos);
            if (prevPos != null && currPos - 1 - prevPos == k) {
                return currDay;
            }

            Integer nextPos = ts.higher(currPos);
            if (nextPos != null && nextPos - 1 - currPos == k) {
                return currDay;
            }

            ts.add(currPos);
        }
        return -1;
    }
}

class SolutionII {
    // Ref: https://leetcode.com/problems/k-empty-slots/discuss/107931/JavaC++-Simple-O(n)-solution
    // tag: sliding window
    // time: O(n)
    // space: O(n)
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }

        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = k + 1;
        int i = 0;
        while (r < days.length) {
            if (i == r) min = Math.min(min, Math.max(days[l], days[r]));
            // for any i = left+1,..., left+k-1, we can have days[left] < days[i] && days[right] < days[i]
            if (!(days[l] <= days[i] && days[i] > days[r])) {
                l = i;
                r = i + k + 1;
            }
            i++;
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }
}
