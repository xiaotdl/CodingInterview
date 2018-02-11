package largest_number;

import java.util.*;

/**
 * Created by Xiaotian on 2/10/18.
 */
public class Solution {
    // tag: sort
    // time: O(nlogn)
    // space: O(n)
    /*
     * @param nums: A list of non negative integers
     * @return: A string
     */
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = "" + nums[i];
        }

        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

        String res = "";
        for (String s : strs) res += s;
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        return i == res.length() ? "0" : res.substring(i);
    }
}
