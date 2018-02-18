package sum_of_two_integers;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // iterative
    // a + b = digits + carries, save digits in a and carries in b to iterate
    // tag: bit
    // time: O(1)
    // space: O(1)
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = (carry << 1);
        }
        return a;
    }

    // credit: https://discuss.leetcode.com/topic/49771/java-simple-easy-understand-solution-with-explanation
    public int getSubtract(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }

}

class SolutionII {
    // recursive
    public int getSum(int a, int b) {
        if (b == 0) return a;
        return getSum(a ^ b, (a & b) << 1);
    }
}
