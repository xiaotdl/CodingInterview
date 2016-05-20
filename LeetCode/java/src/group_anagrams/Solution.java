package group_anagrams;

import java.util.*;

/**
 * Created by xili on 5/18/16.
 */
public class Solution {
    // tag: string, hash table
    // time: O(n*m*logm), where n is length of strs, m is average length of strings
    // space: O(n), used a hash to save all strings
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        // key is sorted String, where anagrams will have the same sorted String.
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();

        for (String str: strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            if (!hm.containsKey(sortedStr)) {
                ArrayList<String> anagrams = new ArrayList<String>();
                hm.put(sortedStr, anagrams);
            }
            hm.get(sortedStr).add(str);
        }

        for (Map.Entry<String, ArrayList<String>> entry: hm.entrySet()) {
            ArrayList<String> anagrams = entry.getValue();
            Collections.sort(anagrams);
            result.add(anagrams);
        }

        return result;
    }
}
