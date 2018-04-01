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

class SolutionIII {
    // same as SolutionII, SolutionI
    // tag: str, hash
    // time: O(n*m*logm), where n is length of strs, m is average length of strings
    // space: O(n), used a hash to save all strings
    /**
     * @param strs: the given array of strings
     * @return: The anagrams which have been divided into groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(); // sortedStr2Strs
        for (String s : strs) {
            String sorted = sorted(s);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<String>());
            }
            map.get(sorted).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private String sorted(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}

class SolutionIV {
    // tag: str, hash
    // time: O(n*m), where n is length of strs, m is average length of strings
    // space: O(n*m), used a hash to save all strings
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        Map<String, List<String>> m = new HashMap<>(); // charCnt(e.g."2|1|...")2list(str)
        for (String s : strs) {
            int[] charCnt = new int[26];
            for (char c : s.toCharArray()) {
                charCnt[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(charCnt[0]);
            for (int i = 1; i < 26; i++) {
                sb.append("|"+charCnt[i]);
            }
            String key = sb.toString();
            m.putIfAbsent(key, new ArrayList<String>());
            m.get(key).add(s);
        }
        for (List<String> group : m.values()) res.add(group);
        return res;
    }
}
