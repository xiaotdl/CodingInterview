package number_of_atoms;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // tag: stack
    // time: O(n)
    // space: O(n)
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> m = new HashMap<>(); // element2cnt
        String element = "";
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if ('A' <= c && c <= 'Z') {
                element = "" + c;
                while (i + 1 < formula.length() && 'a' <= formula.charAt(i + 1) && formula.charAt(i + 1) <= 'z') {
                    element += formula.charAt(i + 1);
                    i++;
                }
                m.put(element, m.getOrDefault(element, 0) + 1);
            }
            else if ('0' <= c && c <= '9') {
                int num = c - '0';
                while (i + 1 < formula.length() && '0' <= formula.charAt(i + 1) && formula.charAt(i + 1) <= '9') {
                    num = 10 * num + formula.charAt(i + 1) - '0';
                    i++;
                }
                m.put(element, m.get(element) + num - 1);
            }
            else if (c == '(') {
                stack.push(m);
                m = new HashMap<>();
            }
            else if (c == ')') {
                int num = 0;
                while (i + 1 < formula.length() && '0' <= formula.charAt(i + 1) && formula.charAt(i + 1) <= '9') {
                    num = 10 * num + formula.charAt(i + 1) - '0';
                    i++;
                }
                if (num != 0) {
                    for (Map.Entry<String, Integer> entry : m.entrySet()) {
                        String e = entry.getKey();
                        int cnt = entry.getValue();
                        m.put(e, num * cnt);
                    }
                }
                m = merge(stack.pop(), m);
            }
        }
        List<String> elements = new ArrayList<>();
        for (String e : m.keySet()) elements.add(e);
        Collections.sort(elements);
        String res = "";
        for (String e : elements) {
            res += e + (m.get(e) > 1 ? m.get(e) : "");
        }
        return res;
    }

    private Map<String, Integer> merge(Map<String, Integer> m1, Map<String, Integer> m2) {
        for (Map.Entry<String, Integer> e : m2.entrySet()) {
            String element = e.getKey();
            int cnt = e.getValue();
            m1.put(element, m1.getOrDefault(element, 0) + cnt);
        }
        return m1;
    }
}
