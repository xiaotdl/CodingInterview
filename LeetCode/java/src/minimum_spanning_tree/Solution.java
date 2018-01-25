package minimum_spanning_tree;

import java.util.*;

/**
 * Created by Xiaotian on 1/25/18.
 */
class Connection {
  public String city1, city2;
  public int cost;
  public Connection(String city1, String city2, int cost) {
      this.city1 = city1;
      this.city2 = city2;
      this.cost = cost;
  }
}

class UnionFindSet {
    int[] parents;

    UnionFindSet(int n) {
        parents = new int[n + 1];
        for (int x = 1; x <= n; x++) {
            parents[x] = x;
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

public class Solution {
    // Kruscal Algorithm
    // 贪心选择最短边长
    // tag: union find
    // time: O(nlogn), n为点的数量
    // space: O(n)
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> res = new ArrayList<>();

        Collections.sort(connections, new Comparator<Connection>() {
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }
                if (a.city1.equals(b.city1)) {
                    return a.city2.compareTo(b.city2);
                }
                return a.city1.compareTo(b.city1);
            }
        });

        Map<String, Integer> cityRegistry = new HashMap<>(); // cityName2cityIndex

        for (Connection conn : connections) {
            String city1 = conn.city1;
            String city2 = conn.city2;
            if (!cityRegistry.containsKey(city1)) {
                cityRegistry.put(city1, cityRegistry.size() + 1);
            }
            if (!cityRegistry.containsKey(city2)) {
                cityRegistry.put(city2, cityRegistry.size() + 1);
            }
        }

        UnionFindSet ufs = new UnionFindSet(cityRegistry.size());
        for (Connection conn : connections) {
            int city1Idx = cityRegistry.get(conn.city1);
            int city2Idx = cityRegistry.get(conn.city2);
            if (ufs.find(city1Idx) != ufs.find(city2Idx)) {
                ufs.union(city1Idx, city2Idx);
                res.add(conn);
            }
        }

        if (res.size() != cityRegistry.size() - 1) { // 边数=点数-1 才是全连通
            return new ArrayList<Connection>();
        }
        return res;
    }
}
