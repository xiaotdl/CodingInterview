package count_and_say;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: str, iteration
    // time: O(n^2), each string wouldn't exceeds length of 2n, one pass on all strings, which is 2+..+2n => O(n^2).
    // space: O(n^2), ditto
    public String countAndSay(int n) {
        StringBuffer prevStr = new StringBuffer("1");
        StringBuffer currStr = new StringBuffer();
        while (n - 1 > 0) {
            int cnt = 1;
            char num = prevStr.charAt(0);
            for (int i = 1; i < prevStr.length(); i++) {
                if (prevStr.charAt(i) == num) {
                    cnt++;
                }
                else {
                    currStr.append(cnt);
                    currStr.append(num);
                    cnt = 1;
                    num = prevStr.charAt(i);
                }
            }
            currStr.append(cnt);
            currStr.append(num);
            prevStr = currStr;
            currStr = new StringBuffer();
            n--;
        }
        return prevStr.toString();
    }
}
