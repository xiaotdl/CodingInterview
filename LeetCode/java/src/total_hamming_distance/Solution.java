package total_hamming_distance;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // TLE
    // tag: bit
    // time: O(n^2)
    // space: O(1)
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }

    private int hammingDistance(int a, int b) {
        return countOnes(a ^ b);
    }

    private int countOnes(int x) {
        int cnt = 0;
        while (x > 0) {
            x = x & (x - 1);
            cnt++;
        }
        return cnt;
    }
}
