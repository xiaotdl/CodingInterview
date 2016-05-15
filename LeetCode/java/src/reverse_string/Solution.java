package reverse_string;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    public String reverseString(String s) {
        if (s == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(s.length() - 1 - i));
        }

        return sb.toString();
    }
}
