package behavior_tree;

import java.util.*;

/**
 * Created by Xiaotian on 4/15/18.
 */
class Log {
    int user_id;
    long timestamp;
    char action;
    public Log(int user_id, long timestamp, char action) {
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.action = action;
    }
}

class TrieNode {
    char c;
    int hitCnt;
    Map<Character, TrieNode> next;
    public TrieNode(char c) {
        this.c = c;
        hitCnt = 0;
        next = new LinkedHashMap<>(); // keep insertion order
    }

    public TrieNode append(char c) {
        if (!next.containsKey(c)) {
            next.put(c, new TrieNode(c));
        }
        next.get(c).hitCnt++;
        return next.get(c);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", c, hitCnt);
    }
}

class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode('/');
    }

    public void print() {
        for (Map.Entry<Character, TrieNode> e : root.next.entrySet()) {
            dfs(e.getValue(), "");
        }
    }

    private void dfs(TrieNode root, String indent) {
        System.out.println(indent + root);
        for (Map.Entry<Character, TrieNode> e : root.next.entrySet()) {
            dfs(e.getValue(), indent + "  ");
        }
    }
}


class Solution {
    // question: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=300818&highlight=pinterest
    // user_id, timestamp, action
    // 100, 1000, A
    // 200, 1003, A
    // 300, 1009, B
    // 100, 1026, B
    // 100, 1030, C
    // 200, 1109, B
    // 200, 1503, A

    // tag: trie, hash, dfs
    public void behaviorTree(List<Log> logs) {
        // Collections.sort(logs); // sort by timestamp
        Trie trie = new Trie();
        Map<Integer, TrieNode> m = new HashMap<>(); // user_id2user_last_action_node
        for (Log log : logs) {
            int user_id = log.user_id;
            char action = log.action;
            if (!m.containsKey(user_id)) {
                m.put(user_id, trie.root.append(action));
            }
            else {
                m.put(user_id, m.get(user_id).append(action));
            }
        }
        System.out.println(m);
        trie.print();
    }

    public static void main(String[] args) {
        List<Log> logs = new ArrayList<>(Arrays.asList(
                new Log(100, 1000, 'A'),
                new Log(200, 1003, 'A'),
                new Log(300, 1008, 'B'),
                new Log(100, 1026, 'B'),
                new Log(100, 1030, 'C'),
                new Log(200, 1100, 'B'),
                new Log(200, 1502, 'A')
        ));
        new Solution().behaviorTree(logs);
    }
}
