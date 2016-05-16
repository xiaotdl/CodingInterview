package count_and_say;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: string, iteration
    // time: O(n^2), each string wouldn't exceeds length of 2n, one pass on all strings, which is 2+..+2n => O(n^2).
    // space: O(n^2), ditto
    public String countAndSay(int n) {
        if (n == 0) {
            return "";
        }

        String prevString = "1";
        for (int i = 1; i < n; i++) {
            String currString = "";
            char prevChar = prevString.charAt(0);
            int charCount = 1;
            for (int j = 1; j < prevString.length(); j++) {
                char currChar = prevString.charAt(j);
                if (currChar == prevChar) {
                    charCount++;
                } else {
                    currString += String.valueOf(charCount) + String.valueOf(prevChar);
                    charCount = 1;
                }
                prevChar = currChar;
            }
            currString += String.valueOf(charCount) + String.valueOf(prevChar);
            prevString = currString;
        }

        return prevString;
    }
}
