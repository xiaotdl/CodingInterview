package is_subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Xiaotian on 10/30/16.
 */
public class Solution {
    // tag: two pointer
    // time: O(n), one pass
    // space: O(1)
    public boolean isSubsequence1(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return i == s.length();
    }

    // tag: binary search
    // time: O(mlogn)
    // space: O(n)
    public boolean isSubsequence2(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        HashMap<Character, ArrayList<Integer>> hm = new HashMap<Character, ArrayList<Integer>>();
        // a: [0, 2, 5, 8...]
        // b: [1, 9...]
        char[] chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!hm.containsKey(c)) {
                hm.put(c, new ArrayList<Integer>(Arrays.asList(i)));
            }
            else {
                hm.get(c).add(i);
            }
        }

        int prevIndex = -1;
        for (char c : s.toCharArray()) {
            int index = binarySearchFirstLarger(hm.get(c), prevIndex);
            if (index == -1) {
                return false;
            }
            prevIndex = hm.get(c).get(index);
        }
        return true;
    }

    int binarySearchFirstLarger(ArrayList<Integer> nums, int target) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) > target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (nums.get(start) > target) {
            return start;
        }
        if (nums.get(end) > target) {
            return end;
        }
        return -1;
    }
}
