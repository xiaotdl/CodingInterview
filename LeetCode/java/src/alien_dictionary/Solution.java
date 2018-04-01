public class Solution {
    // bfs vertices that has indegree == 0 (no dependencies)
    // tag: topological sort
    // time: O(V+E)
    // space: O(V+E)
    public String alienOrder(String[] words) {
        String res = "";
        if (words == null || words.length == 0) return res;

        Map<Character, Set<Character>> graph = new HashMap<>(); // char2set(neighbor_char) u->v: u is a dependency to v
        Map<Character, Integer> indegree = new HashMap<>(); // char2indegree

        // init graph and indegree
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<Character>());
                    indegree.put(c, 0);
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int minLen = Math.min(curr.length(), next.length());
            for (int j = 0; j < minLen; j++) {
                char u = curr.charAt(j);
                char v = next.charAt(j);
                if (u == v) continue;

                if (graph.get(v).contains(u)) return ""; // invalid, e.g. a->b, b->a
                if (graph.get(u).contains(v)) break; // edge already recorded
                graph.get(u).add(v);
                indegree.put(v, indegree.get(v) + 1);
                break;
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (indegree.get(c) == 0) q.offer(c); // indegree == 0: c is not dependent on any other char
        }
        while (!q.isEmpty()) {
            char u = q.poll();
            res += u;
            for (char v : graph.get(u)) {
                indegree.put(v, indegree.get(v) - 1);
                if (indegree.get(v) == 0) q.offer(v);
            }
        }

        if (res.length() != graph.size()) return ""; // there are circles in graph, e.g. a->b, b->a
        return res;
    }
}
