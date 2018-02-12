package permutation_sequence;

import java.util.*;

/**
 * Created by Xiaotian on 2/12/18.
 */
public class Solution {
    // Ref: https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
    // tag: math
    // time: O(n^2)
    // space: O(n)
    /*
     * @param n: n
     * @param k: the k th permutation
     * @return: return the k-th permutation
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // create a list of numbers to get indices
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        k = k - 1; // counting from 0

        String res = "";
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            res += nums.get(index);
            nums.remove(index);
            k -= index * factorial[n - i];
        }
        return res;
    }
}
