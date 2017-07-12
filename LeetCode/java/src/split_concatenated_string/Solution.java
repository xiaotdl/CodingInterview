package split_concatenated_string;

/**
 * Created by Xiaotian on 7/11/17.
 */
public class Solution {
    // tag: str
    // time: O(n * m), n: strs length, m: avg str length
    // space: O(n * m)
    public String splitLoopedString(String[] strs) {
        String sortedS = new String();
        for (int i = 0; i < strs.length; i++) {
            String reversedS = new StringBuffer(strs[i]).reverse().toString();
            strs[i] = strs[i].compareTo(reversedS) > 0 ? strs[i] : reversedS;
            sortedS += strs[i];
        }

        String res = new String();
        int next = 0; // points to next word of cutted word
        char maxChar = 'a';
        for (int i = 0; i < strs.length; i++) {
            int m = strs[i].length();
            next += m;
            String other_strs = sortedS.substring(next) + sortedS.substring(0, next - m);
            String reversedS = new StringBuffer(strs[i]).reverse().toString();
            for (int j = 0; j < m; j++) {
                if (strs[i].charAt(j) >= maxChar) {
                    maxChar = strs[i].charAt(j);
                    String cuttedS1 = strs[i].substring(j) + other_strs + strs[i].substring(0, j);
                    String cuttedS2 = reversedS.substring(m - 1 - j) + other_strs + reversedS.substring(0, m - 1 - j);
                    if (cuttedS1.compareTo(res) > 0) res = cuttedS1;
                    if (cuttedS2.compareTo(res) > 0) res = cuttedS2;
                }
            }
        }
        return res;
    }
}
