package word_ladder_ii;

import java.util.*;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // tag: str, ptr, bfs, dfs
    // time: O(n!), recursion tree
    // space: O(n^2)
    public List<List<String>> findLadders(String start, String end, Set<String> wordDict) {
        List<List<String>> ladders = new ArrayList<>(); // transformation word list/path from startWord to endWord
        Map<String, List<String>> map = new HashMap<>(); // nextWord2oneLetterDiffCurrWords
        Map<String, Integer> distance = new HashMap<>(); // word2word'sdistanceToStartWord

        wordDict.add(start);
        wordDict.add(end);

        bfs(map, distance, start, end, wordDict);

        List<String> path = new ArrayList<>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
                    String start, String end, Set<String> wordDict) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distance.put(start, 0);
        for (String word : wordDict) {
            map.put(word, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String curr = q.poll();
            for (String next : getNextWords(curr, wordDict)) {
                map.get(next).add(curr);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(curr) + 1);
                    q.offer(next);
                }
            }
        }
    }

    private List<String> getNextWords(String curr, Set<String> wordDict) {
        List<String> nextWords = new ArrayList<>();
        for (int i = 0; i < curr.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == curr.charAt(i)) continue;
                String next = curr.substring(0, i) + c + curr.substring(i + 1);
                if (wordDict.contains(next)) {
                    nextWords.add(next);
                }
            }
        }
        return nextWords;
    }

    private void dfs(List<List<String>> ladders, List<String> path, String curr,
                    String start, Map<String, Integer> distance,
                    Map<String, List<String>> map) {
        path.add(curr);
        if (curr.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        }
        else {
            for (String prev : map.get(curr)) {
                if (distance.containsKey(prev) && distance.get(curr) == distance.get(prev) + 1) {
                    dfs(ladders, path, prev, start, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}

class SolutionII {
    // credit: https://discuss.leetcode.com/topic/27504/my-concise-java-solution-based-on-bfs-and-dfs
    public List < List < String >> findLadders(String start, String end, List < String > wordList) {
        HashSet < String > dict = new HashSet < String > (wordList);
        List < List < String >> res = new ArrayList < List < String >> ();
        HashMap < String, ArrayList < String >> nodeNeighbors = new HashMap < String, ArrayList < String >> (); // Neighbors for every node
        HashMap < String, Integer > distance = new HashMap < String, Integer > (); // Distance of every node from the start node
        ArrayList < String > solution = new ArrayList < String > ();

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set < String > dict, HashMap < String, ArrayList < String >> nodeNeighbors, HashMap < String, Integer > distance) {
        for (String str: dict)
            nodeNeighbors.put(str, new ArrayList < String > ());

        Queue < String > queue = new LinkedList < String > ();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList < String > neighbors = getNeighbors(cur, dict);

                for (String neighbor: neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) { // Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor)) // Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    // Find all next level nodes.
    private ArrayList < String > getNeighbors(String node, Set < String > dict) {
        ArrayList < String > res = new ArrayList < String > ();
        char chs[] = node.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Set < String > dict, HashMap < String, ArrayList < String >> nodeNeighbors, HashMap < String, Integer > distance, ArrayList < String > solution, List < List < String >> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList < String > (solution));
        } else {
            for (String next: nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }
}
