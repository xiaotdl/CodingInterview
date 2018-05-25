class UnionFindSet {
    int[] parents;

    // constructor: O(n)
    UnionFindSet(int n) {
        parents = new int[n];
        for (int x = 1; x <= n; x++) {
            parents[x] = x;
        }
    }

    // find: amortized O(1)
    int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    // union: amortized O(1)
    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }
}


并查集原生操作：
    - 查询两个元素是否在同一个集合内
    - 合并两个元素所在的集合
并查集的派生操作：
    - 查询某个元素所在集合的元素个数
    - 查询当前所有集合的个数


## Questions
[Connecting Graph](http://www.lintcode.com/en/problem/connecting-graph/)
[Connecting Graph II](http://www.lintcode.com/en/problem/connecting-graph-ii/)
[Connecting Graph III](http://www.lintcode.com/en/problem/connecting-graph-iii/)
[Number of Islands](http://www.lintcode.com/en/problem/number-of-islands/)
[Number of Islands II](http://www.lintcode.com/en/problem/number-of-islands-ii/)
[Merge Contacts](https://stackoverflow.com/questions/39985191/algorithm-to-merge-contacts/)
[Graph Valid Tree](http://www.lintcode.com/en/problem/graph-valid-tree/)
