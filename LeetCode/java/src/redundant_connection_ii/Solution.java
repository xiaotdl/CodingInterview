package redundant_connection_ii;

/**
 * Created by Xiaotian on 4/7/18.
 */
class UnionFindSet {
    int[] parents;
    public UnionFindSet(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
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

class Solution {
    // credit: http://www.jiuzhang.com/solution/redundant-connection-ii/
    // Case1: 没重复的父结点，有环，则纯的union find解法，找到环的最后一个node即可；
    // Case2: 有重复的父结点，没环，返回这个node即可；
    // Case3: 有重复的父结点，有环，返回{u1, v} or {u2, v}其中那个造成环的结点；
    // tag: union find
    // time: O(n)
    // space: O(n)
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length; // max vertice cnt
        UnionFindSet ufs = new UnionFindSet(n);
        int[] parents = new int[n + 1];

        int[] candidate1 = null;
        int[] candidate2 = null;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            if (parents[v] > 0) { // v node has >= 2 parents
                candidate1 = new int[]{parents[v], v};
                candidate2 = new int[]{e[0], e[1]};

                // Delete the later edge
                // 这里是算法关键，找到有两个父结点的结点的两条边，先试探性的删除其中一条，再在后面判断时候还有环存在，来断定删除是否正确，从而返回答案
                e[0] = e[1] = -1;
            }

            parents[v] = u;
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            if (u < 0 && v < 0) continue; // skip tentatively deleted edge

            if (ufs.find(u) == ufs.find(v)) { // circle detected
                if (candidate1 == null) return e; // Case1: no double parents, has circle; return current edge
                else return candidate1; // Case3: has double parents, has circle; since candidate2 has been deleted and didn't work, return candidate1
            }

            ufs.union(u, v);
        }

        return candidate2; // Case2: has double parents, no circle; return candidate2
    }
}
