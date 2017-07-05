package simplify_path;

import java.util.*;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // tag: str, stack
    // time: O(n)
    // space: O(n)
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";

        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));

        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            else if (skip.contains(dir)) continue;
            else {
                stack.push(dir);
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            String dir = stack.pop();
            sb.insert(0, dir);
            sb.insert(0, "/");
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
