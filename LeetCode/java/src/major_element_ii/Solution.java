package major_element_ii;

import java.util.*;

/**
 * Created by Xiaotian on 9/9/17.
 */
public class Solution {
    // Boyer-Moore Majority Vote algorithm
    // tag: array
    // time: O(n)
    // space: O(1)
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int major1, major2, count1, count2;
        major1 = major2 = 0;
        count1 = count2 = 0;
        for (int num : nums) {
            if (num == major1) {
                count1++;
            }
            else if (num == major2) {
                count2++;
            }
            else if (count1 == 0) {
                major1 = num;
                count1 = 1;
            }
            else if (count2 == 0) {
                major2 = num;
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }

        count1 = count2 = 0;
        for (int num : nums) {
            if (num == major1) count1++;
            else if (num == major2) count2++;
        }

        if (count1 > nums.length / 3) res.add(major1);
        if (count2 > nums.length / 3) res.add(major2);
        return res;
    }
}
