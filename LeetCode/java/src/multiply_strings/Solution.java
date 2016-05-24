package multiply_strings;

/**
 * Created by Xiaotian on 5/24/16.
 */
public class Solution {
    // tag: string
    // time: O(m + n), reverse, then one pass through each string
    // space: O(m + n), used an array to save each digits
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "0";
        }

        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        // e.g. 99 * 99 < 10000
        int[] digits = new int[n1.length() + n2.length()];
        // e.g. 385 * 97, digits=5*7，tens=8*7+5*9, hundreds=3*7+8*9...
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                digits[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i] % 10;
            int carry = digits[i] / 10;
            sb.insert(0, digit);
            if (i < digits.length - 1) {
                digits[i + 1] += carry;
            }
        }

        // remove leading 0s
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
