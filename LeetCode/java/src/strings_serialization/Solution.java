package strings_serialization;

import java.util.*;

/**
 * Created by Xiaotian on 1/20/18.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(s.length() + "|");
            sb.append(s);
        }
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> res = new LinkedList<>();
        int i = 0;
        while (i < str.length()) {
            int dilimiter = str.indexOf('|', i);
            int len = Integer.parseInt(str.substring(i, dilimiter));
            res.add(str.substring(dilimiter + 1, dilimiter + len + 1));
            i = dilimiter + len + 1;
        }
        return res;
    }
}
