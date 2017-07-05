package restore_ip_address;

import java.util.*;

/**
 * Created by Xiaotian on 5/24/16.
 */
public class Solution {
    // tag: str, ptr, recursion, backtracking, dfs
    // time: O(n!), recursion tree
    // space: O(n), tree height
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return res;

        dfs(s, 0, new ArrayList<String>(), res);

        return res;
    }

    private void dfs(String s, int pos, ArrayList<String> ipNums, List<String> res) {
        if (ipNums.size() > 4) return;

        if (pos == s.length() && ipNums.size() == 4) {
            String ip = ipNums.get(0);
            for (int i = 1; i < ipNums.size(); i++) ip += '.' + ipNums.get(i);
            res.add(ip);
            return;
        }

        StringBuffer num = new StringBuffer();
        for (int i = pos; i < Math.min(s.length(), pos + 3); i++) {
            num.append(s.charAt(i));
            if (isValidIPNum(num.toString())) {
                ipNums.add(num.toString());
                dfs(s, i + 1, ipNums, res);
                ipNums.remove(ipNums.size() - 1);
            }
        }
    }

    private boolean isValidIPNum(String num) {
        if (num.length() == 0 || num.length() > 3) {
            return false;
        }
        else if (num.length() > 1 && num.charAt(0) == '0') {
            return false;
        }
        else if (Integer.parseInt(num) < 0 || Integer.parseInt(num) > 255) {
            return false;
        }
        return true;
    }
}

