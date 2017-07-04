package multiply_strings;

/**
 * Created by Xiaotian on 5/24/16.
 */
public class Solution {
    // tag: str, math
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

class SolutionII {
    // tag: str, math
    // time: O(m + n), then one pass through each string
    // space: O(m + n), used an array to save each digits
    // num1[i] * num2[j] => res[i + j], res[i + j + 1]
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) return "0";

        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = product + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < res.length && res[i] == 0) i++;
        while (i < res.length) sb.append(res[i++]);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

