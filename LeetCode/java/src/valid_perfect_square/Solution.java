package valid_perfect_square;

/**
 * Created by Xiaotian on 3/3/18.
 */
public class Solution {
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public boolean isPerfectSquare(int num) {
        long l = 1;
        long r = num - 1;
        while (l + 1 < r) {
            long m = l + (r - l) / 2;
            long mSq = m * m;
            if (mSq > num) {
                r = m;
            }
            else if (mSq < num) {
                l = m;
            }
            else {
                return true;
            }
        }
        if (l * l == num) return true;
        if (r * r == num) return true;
        return false;
    }
}
