package group_anagrams;

import java.util.*;

/**
 * Created by xili on 5/18/16.
 */
public class Solution {
    // tag: str, hash
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

class SolutionII {
    // same as SolutionI
    // tag: str, hash
    // time: O(n*m*logm), where n is length of strs, m is average length of strings
    // space: O(n), used a hash to save all strings
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        Map<String, ArrayList<String>> m = new HashMap<>();
        for (String s : strs) {
            String sortedStr = sorted(s);
            if (!m.containsKey(sortedStr)) {
                m.put(sortedStr, new ArrayList<String>());
            }
            m.get(sortedStr).add(s);
        }
        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ArrayList<String>> pair = (Map.Entry) it.next();
            res.add(pair.getValue());
        }
        return res;
    }

    private String sorted(String s) {
        char[] S = s.toCharArray();
        Arrays.sort(S);
        return new String(S);
    }
}
