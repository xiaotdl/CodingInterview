package optimal_division;

/**
 * Created by Xiaotian on 7/11/17.
 */
public class Solution {
    // credit: https://leetcode.com/articles/optimal-division/#approach-3-using-some-math-accepted
    // solution will always be a/(b/c/d), which maximizes denominator and minimizes divisor
    // tag: str, math
    // time: O(n)
    // space: O(n)
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) return Integer.toString(nums[0]);
        if (nums.length == 2) return nums[0] + "/" + nums[1];
        StringBuffer sb = new StringBuffer();
        sb.append(nums[0]+ "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sb.append("/" + nums[i]);
        }
        sb.append(")");
        return sb.toString();

    }
}
