import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    /**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return an integer
     */

    // V1, O(nlogn*26^(n/2))
    // BFS(Queue)
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        dict.remove(start);

        int length = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                String word = queue.poll();
                for (char c = 'a'; c <= 'z'; c++) {
                    for (int j = 0; j < word.length(); j++) {
                        if (c == word.charAt(j)) {
                            continue;
                        }
                        String tmp = replace(word, j, c);
                        if (tmp.equals(end)) {
                            return length + 1;
                        }
                        if (dict.contains(tmp)) {
                            queue.offer(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            length++;
        }

        return 0;
    }
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}

