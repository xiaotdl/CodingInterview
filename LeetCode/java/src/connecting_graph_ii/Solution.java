package connecting_graph_ii;

/**
 * Created by Xiaotian on 9/17/17.
 */
public class Solution {
    // NOTE: add up all other component's cnt in union operation
    // tag: union find
    // time:
    //   find: O(1)
    //   union: O(1)
    // space: O(n)
}
class ConnectingGraph2 {
    int[] parents;
    int[] componentCnt;
    /*
    * @param n: An integer
    */public ConnectingGraph2(int n) {
        parents = new int[n + 1];
        componentCnt = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            parents[x] = x;
            componentCnt[x] = 1;
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
     * @return: An integer
     */
    public int query(int a) {
        return componentCnt[find(a)];
    }

    private int find(int x) {
        if (parents[x] == x) return x;
        parents[x] = find(parents[x]);
        return parents[x];
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parents[rootA] = rootB;
            componentCnt[rootB] += componentCnt[rootA];
        }
    }
}
