package sort_transformed_array;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // tag: math, ptr
    // time: O(n)
    // space: O(1)
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int l = 0;
        int r = nums.length - 1;
        if (a > 0) { // largest nums on two sides
            for (int i = n - 1; i >= 0; i--) {
                int y1 = f(nums[l], a, b, c);
                int y2 = f(nums[r], a, b, c);
                if (y1 >= y2) {
                    res[i] = y1;
                    l++;
                }
                else {
                    res[i] = y2;
                    r--;
                }
            }
        }
        else { // smallest nums on two sides
            for (int i = 0; i < n; i++) {
                int y1 = f(nums[l], a, b, c);
                int y2 = f(nums[r], a, b, c);
                if (y1 <= y2) {
                    res[i] = y1;
                    l++;
                }
                else {
                    res[i] = y2;
                    r--;
                }
            }
        }
        return res;
    }

    private int f(int x, int a, int b, int c) {
        return a*x*x + b*x + c;
    }
}
