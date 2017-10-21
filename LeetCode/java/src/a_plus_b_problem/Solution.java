package a_plus_b_problem;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // a + b = (a ^ b) + (a & b << 1)
    // a' = a ^ b           表示不进位的值
    // b' = (a & b) << 1    表示进位的值
    // 那么我们把不进位的结果和进位的结果加起来，就是实际中a + b的和。
    // ==>
    // a + b = (a ^ b) + (a & b << 1) = a' + b'
    // 然后反复迭代，这个过程是在二进制下模拟加法的运算过程，进位不可能一直持续，所以b最终会变为0，也就是没有需要进位的了，最终求得a + b的值
    // tag: bit
    // time: O(n)
    // space: O(1)
    /*
     * @param : An integer
     * @param : An integer
     * @return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        while (b != 0) {
            int _a = a ^ b;
            int _b = (a & b) << 1;
            a = _a;
            b = _b;
        }
        return a;
    }
}
