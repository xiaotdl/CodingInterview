package sentence_similarity_ii;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // tag: union find, hash
    // time: O(n)
    // space: O(n)
    static class UnionFindSet {
        int[] parents;
        public UnionFindSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] == x) return x;
            parents[x] = find(parents[x]);
            return parents[x];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parents[rootA] = rootB;
            }
        }
    }
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;

        UnionFindSet ufs = new UnionFindSet(2*pairs.length);
        Map<String, Integer> m = new HashMap<>(); // word2index
        int wordCnt = 0;
        for (String[] p : pairs) {
            String w1 = p[0];
            String w2 = p[1];
            if (!m.containsKey(w1)) {
                wordCnt++;
                m.put(w1, wordCnt - 1);
            }
            if (!m.containsKey(w2)) {
                wordCnt++;
                m.put(w2, wordCnt - 1);
            }
            ufs.union(m.get(w1), m.get(w2));
        }

        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) continue;
            if (m.containsKey(w1) && m.containsKey(w2) && ufs.find(m.get(w1)) == ufs.find(m.get(w2))) continue;
            return false;
        }
        return true;
    }
}
