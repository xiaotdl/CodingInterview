package intersection_of_two_arrays_ii;

import java.util.HashMap;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
    // tag: hash map
    // time: O(n), two pass through all keys
    // space: O(n)
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> resultMap = new HashMap<Integer, Integer>();

        HashMap<Integer, Integer> cnt1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> cnt2 = new HashMap<Integer, Integer>();

        for (int num : nums1) {
            cnt1.put(num, cnt1.get(num) == null ? 1 : cnt1.get(num) + 1);
        }

        for (int num : nums2) {
            cnt2.put(num, cnt2.get(num) == null ? 1 : cnt2.get(num) + 1);
        }

        for (Integer k1 : cnt1.keySet()) {
            int num1 = Integer.valueOf(k1);
            if (cnt2.containsKey(num1)) {
                resultMap.put(num1, Math.min(cnt1.get(num1), cnt2.get(num1)));
            }
        }

        int size = 0;
        for (Integer k : resultMap.keySet()) {
            size += resultMap.get(k);
        }

        int[] result = new int[size];
        int i = 0;
        for (Integer k : resultMap.keySet()) {
            int num = Integer.valueOf(k);
            while (resultMap.get(k) != 0) {
                result[i++] = num;
                resultMap.put(k, resultMap.get(k) - 1);
            }
        }

        return result;
    }
}
