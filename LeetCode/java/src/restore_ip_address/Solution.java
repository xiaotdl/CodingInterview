package restore_ip_address;

import java.util.*;

/**
 * Created by Xiaotian on 5/24/16.
 */
public class Solution {
    // tag: string, pointer, recursion, backtracking
    // time: O(n!), recursion tree
    // space: O(n), tree height
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        helper(s, 0, new ArrayList<String>(), result);

        return result;
    }

    private void helper(String s, int pos, ArrayList<String> ipNums, List<String> result) {
        if (pos == s.length() && ipNums.size() == 4) {
            String ip = ipNums.get(0);
            for (int i = 1; i < ipNums.size(); i++) {
                ip += "." + ipNums.get(i);
            }
            result.add(ip);
            return;
        }

        String currNum = "";
        for (int i = pos; i < s.length() && i < pos + 3; i++) {
            currNum += Character.toString(s.charAt(i));
            if (isValid(currNum)) {
                ipNums.add(currNum);
                helper(s, i + 1, ipNums, result);
                ipNums.remove(ipNums.size() - 1);
            }
        }
    }

    private boolean isValid(String num) {
        if (num.length() == 0 || num.length() > 3) {
            return false;
        } else if (Integer.parseInt(num) < 0 || Integer.parseInt(num) > 255) {
            return false;
        } else if (num.length() > 1 && num.charAt(0) == '0') {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
    }
}
