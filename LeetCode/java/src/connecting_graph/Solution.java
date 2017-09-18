package connecting_graph;

/**
 * Created by Xiaotian on 9/17/17.
 */
public class Solution {
    // NOTE: update the root parent in union operation
    // tag: union find
    // time:
    //   find: O(1)
    //   union: O(1)
    // space: O(n)
}
class ConnectingGraph {
    int[] parents;
    /*
    * @param n: An integer
    */public ConnectingGraph(int n) {
        parents = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            parents[x] = x;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        union(a, b);
        return;
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        return find(a) == find(b);
    }

    private int find(int x) {
        if (parents[x] == x) return x;
        parents[x] = find(parents[x]); // 路径压缩
        return parents[x];
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }
}
