package intersection_of_two_arrays;

import java.util.HashSet;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
    // tag: hash set
    // time: O(n), one pass through all keys
    // space: O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> resultSet = new HashSet<Integer>();

        HashSet<Integer> hs1 = new HashSet<Integer>();

        for (int num : nums1) {
            hs1.add(num);
        }

        for (int i = 0; i < nums2.length; i++) {
            int num2 = nums2[i];
            if (hs1.contains(num2)) {
                resultSet.add(num2);
            }
        }

        int[] result = new int[resultSet.size()];

        int i = 0;
        for (Integer k : resultSet) {
            result[i++] = Integer.valueOf(k);
        }

        return result;
    }
}
