package test;

/**
 * Created by Xiaotian on 9/11/17.
 */
import java.util.*;

public class Test {
    public static void main(String[] args) {
//    System.out.println(new Solution().majorityElement(new int[]{2,2,1,3}));
        System.out.println(3 >> 1);

    }
}
class Solution {
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
        if (count1 > nums.length / 3) res.add(major1);
        if (count2 > nums.length / 3) res.add(major2);
        return res;
    }
}


