package big_integer_addition;

/**
 * Created by Xiaotian on 2/3/18.
 */
public class Solution {
    // tag: math, 数位分离
    // time: O(n)
    // space: O(1)
    /*
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        String res = "";

        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum  = (i >= 0 ? num1.charAt(i) - '0' : 0)
                    + (j >= 0 ? num2.charAt(j) - '0' : 0)
                    + carry;
            res = (sum % 10) + res;
            carry = sum / 10;
        }

        if (carry == 1) {
            res = carry + res;
        }

        return res;
    }
}
