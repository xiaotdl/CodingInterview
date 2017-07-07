package encode_and_decode_strings;

import java.util.*;

/**
 * Created by Xiaotian on 6/13/17.
 */
public class Solution {

    // tag: str
    // time: O(n)
    // space: O(1)
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length());
            sb.append("|");
            sb.append(str);
        }
        return sb.toString();
    }

    // tag: str
    // time: O(n)
    // space: O(1)
    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<String>();
        int i = 0;
        while (i < s.length()) {
            int delimiterPos = s.indexOf('|', i);
            int size = Integer.valueOf(s.substring(i, delimiterPos));
            i = delimiterPos + size + 1;
            res.add(s.substring(delimiterPos + 1, i));

        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
