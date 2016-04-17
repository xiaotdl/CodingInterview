import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Anagrams {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */

    // V1, O(nklogk), n - chars, k - max string length
    // Hash
    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        if (strs == null || strs.length == 0) {
            return result;
        }

        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String str = new String(charArr);
            if (map.containsKey(str)) {
                map.get(str).add(strs[i]);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(str, list);
            }
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }
}
