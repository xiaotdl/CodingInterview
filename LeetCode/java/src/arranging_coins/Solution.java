package arranging_coins;

/**
 * Created by Xiaotian on 11/1/16.
 */
public class Solution {
    // tag: brutal force
    // time: O(n)
    // space: O(1)
    public int arrangeCoins1(int n) {
        int stairs = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (n - i >= 0) {
                stairs++;
                n -= i;
            }
            else {
                return stairs;
            }
        }

        return -1;
    }

    // tag: math
    // find x that satisfy the following condition:1 + 2 + 3 + 4 + ... + x <= n.
    // (1 + x) * x / 2 <= n

    // tag: binary search
    // find x that satisfy the following condition:1 + 2 + 3 + 4 + ... + x <= n.
    // (1 + x) * x / 2 <= n
    // Using binary search to find x.
    public int arrangeCoins2(int n) {
        if (n <= 0) {
            return 0;
        }
        return binarySearchLastSmaller(n);

    }

    int binarySearchLastSmaller(int n) {
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getSum(mid) <= n) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (getSum(end) <= n) {
            return end;
        }
        if (getSum(start) <= n) {
            return start;
        }
        return -1;
    }

    long getSum(int x) {
        return (1 + (long) x) * x / 2;
    }
}
