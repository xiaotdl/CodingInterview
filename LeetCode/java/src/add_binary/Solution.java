package add_binary;

/**
 * Created by xili on 5/18/16.
 */
public class Solution {
    // tag: str, ptr
    // time: O(m + n), one pass through each string
    // space: O(1), no additional space used
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            char charA = (i >= 0) ? a.charAt(i) : '0';
            char charB = (j >= 0) ? b.charAt(j) : '0';
            int sum = (int)(charA - '0') + (int)(charB - '0') + carry;
            sb.insert(0, sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }

        if (carry == 1) {
            sb.insert(0, 1);
        }

        return sb.toString();
    }
}

class SolutionII {
    // same as SolutionI
    // tag: str, ptr
    // time: O(m + n), one pass through each string
    // space: O(1), no additional space used
    public String addBinary(String a, String b) {
        a = (a == null || a.length() == 0) ? "0" : a;
        b = (b == null || b.length() == 0) ? "0" : b;

        StringBuffer sb = new StringBuffer();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry
                    + (i >= 0 ? a.charAt(i--) - '0' : 0)
                    + (j >= 0 ? b.charAt(j--) - '0' : 0);
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.insert(0, carry);
        return sb.toString();
    }
}
