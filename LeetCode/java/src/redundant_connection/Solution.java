package redundant_connection;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // tag: union find
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

    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet ufs = new UnionFindSet(2000);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            if (ufs.find(u) == ufs.find(v)) return e;
            ufs.union(u, v);
        }
        return new int[]{-1, -1};
    }
}
