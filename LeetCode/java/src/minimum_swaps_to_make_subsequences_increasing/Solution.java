package minimum_swaps_to_make_subsequences_increasing;

/**
 * Created by Xiaotian on 5/1/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/127215/Simple-DP-in-Java-with-time:O(N)-space:O(1)
    // credit: https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/solution/
    // tag: dp
    // time: O(n)
    // space: O(1)
    public int minSwap(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0;
        int s1 = 1;
        for (int i = 1; i < A.length; ++i) {
            int n2 = Integer.MAX_VALUE;
            int s2 = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }
}
