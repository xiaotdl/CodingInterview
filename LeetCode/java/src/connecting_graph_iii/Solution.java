package connecting_graph_iii;

/**
 * Created by Xiaotian on 9/17/17.
 */
public class Solution {
    // tag: union find
    // time:
    //   find: O(1)
    //   union: O(1)
    // space: O(n)
}
class ConnectingGraph3 {
    int[] parents;
    int componentsCnt;
    /*
    * @param n: An integer
    */public ConnectingGraph3(int n) {
        parents = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            parents[x] = x;
        }
        componentsCnt = n;
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
     * @return: An integer
     */
    public int query() {
        return componentsCnt;
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
            componentsCnt--;
        }
    }
}
