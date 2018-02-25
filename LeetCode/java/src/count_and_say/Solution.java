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

class SolutionII {
    // Same as Solution
    // tag: str, iteration
    // time: O(n^2)
    // space: O(1)
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prevNum = "1";
        String currNum = "0";
        for (int i = 2; i <= n; i++) { // i th digit
            currNum = count(prevNum);
            // System.out.println(currNum);
            prevNum = currNum;
        }
        return "" + currNum;
    }

    private String count(String num) {
        char prevDigit = num.charAt(0);
        int prevCnt = 1;
        String res = "";
        for (int i = 1; i < num.length(); i++) {
            char digit = num.charAt(i);
            if (digit == prevDigit) {
                prevCnt++;
            }
            else {
                res += "" + prevCnt + prevDigit;
                prevDigit = digit;
                prevCnt = 1;
            }
        }
        res += "" + prevCnt + prevDigit;
        return res;
    }
}
