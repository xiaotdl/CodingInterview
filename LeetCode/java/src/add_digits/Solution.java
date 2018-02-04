package add_digits;

/**
 * Created by Xiaotian on 2/4/18.
 */
public class Solution {
    // tag: math, 数位分离
    // time: O(n)
    // space: O(1)
    /*
     * @param num: a non-negative integer
     * @return: one digit
     */
    public int addDigits(int num) {
        if (num < 0) return num;

        while (num > 9) {
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }

        return num;
    }
}
