package total_hamming_distance;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // TLE!!!
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

class SolutionII {
    // For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set.
    // Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.
    // tag: bit
    // time: O(n)
    // space: O(1)
    public int totalHammingDistance(int[] nums) {
        int total = 0;
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int num : nums) {
                bitCount += (num >> i & 1);
            }
            total += bitCount * (nums.length - bitCount);
        }
        return total;
    }
}
