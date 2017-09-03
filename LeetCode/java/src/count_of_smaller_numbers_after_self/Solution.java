package count_of_smaller_numbers_after_self;

import java.util.*;

/**
 * Created by Xiaotian on 9/2/17.
 */
public class Solution {
    // tag: binary search
    // time: O(nlogn)
    // space: O(n)
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> tmp = new ArrayList<>(n);
        List<Integer> res = new ArrayList<>(n);
        for (int i = 0; i < n; i++) res.add(0);

        for (int i = n - 1; i >= 0; i--) {
            // find first smaller num than nums[i] in tmp, then insert nums[i] into smaller_index+1
            int first_smaller = binary_search_first_smaller(tmp, nums[i]);
            res.set(i, first_smaller + 1);
            tmp.add(first_smaller + 1, nums[i]);
        }

        return res;
    }

    private int binary_search_first_smaller(List<Integer> list, int target) {
        if (list == null || list.size() == 0) return -1;

        int l = 0;
        int r = list.size() - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (list.get(m) >= target) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (list.get(r) < target) {
            return r;
        }
        else if (list.get(l) < target) {
            return l;
        }
        return -1;
    }
}
