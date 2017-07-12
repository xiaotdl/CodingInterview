package find_duplicate_file_in_system;

import java.util.*;

/**
 * Created by Xiaotian on 7/12/17.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(n)
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if (paths == null || paths.length == 0) return res;

        Map<String, Set<String>> map = new HashMap<>(); // content2filenames
        // path: "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
        for (String path : paths) {
            String[] strs = path.split("\\s+");
            for (int i = 1; i < strs.length; i++) {
                int x = strs[i].indexOf('(');
                String content = strs[i].substring(x + 1);
                String filename = strs[0] + "/" + strs[i].substring(0, x);
//                Set<String> filenames = map.getOrDefault(content, new HashSet<String>());
                Set<String> filenames =
                    map.containsKey(content) ? map.get(content) : new HashSet<String>();
                filenames.add(filename);
                map.put(content, filenames);
            }
        }

        for (String content : map.keySet()) {
            if (map.get(content).size() > 1) {
                res.add(new ArrayList<String>(map.get(content)));
            }
        }
        return res;
    }
}

