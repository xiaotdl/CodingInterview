package ugly_number_ii;

/**
 * Created by Xiaotian on 12/27/16.
 */
// uglyFactor * uglyNum is still an uglyNum
// tag: dp/math
// time: O(n)
// space: O(n)
public class Solution {
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int i1 = 0, i2 = 0, i3 = 0;
        int x1 = 2, x2 = 3, x3 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(x1, Math.min(x2, x3));
            uglyNums[i] = min;
            // in case same values, we use parallel if to process each value
            if (x1 == min) x1 = 2 * uglyNums[++i1];
            if (x2 == min) x2 = 3 * uglyNums[++i2];
            if (x3 == min) x3 = 5 * uglyNums[++i3];
        }
        return uglyNums[n - 1];
    }
}
