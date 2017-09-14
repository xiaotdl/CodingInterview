package beautiful_arrangement_ii;

/**
 * Created by Xiaotian on 9/14/17.
 */
public class Solution {
    // credits: https://discuss.leetcode.com/topic/101135/c-concise-code-o-n
    // k distinct distance can be achieved from 1, 2, ..., k+1(<=n) out of 1..n, by the following interweaving strategy:
    // 1, k+1, 2, k, 3, k-1 ...;
    // The distance of this sequence is k, k-1, k-2, ..., 2, 1
    // tag: array
    // time: O(n)
    // space: O(1)
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int l = 1;
        int r = k + 1;
        int i = 0;
        while (l <= r) {
            res[i++] = l++;
            if (l <= r) res[i++] = r--;
        }
        for (int v = k + 2; v <= n; v++) {
            res[i++] = v;
        }
        return res;
    }
}
