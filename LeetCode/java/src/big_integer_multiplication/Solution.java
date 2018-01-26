package big_integer_multiplication;

/**
 * Created by Xiaotian on 12/13/17.
 */
public class Solution {
    // tag: math
    // time: O(n)
    // space: O(n)
    /*
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1 + l2 + 1];

        // calculate product for each digit
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                res[i + j] += (num1.charAt(l1 - 1 - i) - '0') * (num2.charAt(l2 - 1 - j) - '0');
            }
        }

        // calculate carry and digit
        for (int i = 0; i < l1 + l2; i++) {
            res[i + 1] += res[i] / 10;
            res[i] %= 10;
        }

        // skip leading zeroes
        int i = l1 + l2;
        while (res[i] == 0 && i >= 1) {
            i--;
        }

        // convert to string
        String s = "";
        while (i >= 0) {
            s += res[i--];
        }
        return s;
    }
}
