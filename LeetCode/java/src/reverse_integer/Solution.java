package reverse_integer;

/**
 * Created by Xiaotian on 1/1/17.
 */
public class Solution {
    // tag: math, 数位分离
    // time: O(n)
    // space: O(1)
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int digit = x % 10;
            res = res * 10 + digit;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
            x /= 10;
        }
        return (int) res;
    }
}
