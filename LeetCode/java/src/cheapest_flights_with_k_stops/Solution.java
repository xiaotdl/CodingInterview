package cheapest_flights_with_k_stops;

import java.util.*;

/**
 * Created by Xiaotian on 2/17/18.
 */
public class Solution {
    // TLE
    // tag: dfs
    // time: O(n^2)
    // space: O(1)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>(); // src2<dst2price>
        for (int i = 0; i < n; i++) {
            prices.put(i, new HashMap<Integer, Integer>());
        }
        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int price = flights[i][2];
            prices.get(from).put(to, price);
        }

        int minPrice = dfs(prices, src, dst, K + 1, 0, Integer.MAX_VALUE);

        return minPrice != Integer.MAX_VALUE ? minPrice : -1;
    }

    private int dfs(Map<Integer, Map<Integer, Integer>> prices, int src, int dst, int K, int price, int minSoFar) {
        if (K < 0) return Integer.MAX_VALUE;
        if (price > minSoFar) return Integer.MAX_VALUE;
        if (src == dst) return price;

        int minPrice = Integer.MAX_VALUE;
        for (Integer _dst : prices.get(src).keySet()) {
            minPrice = Math.min(minPrice, dfs(prices, _dst, dst, K - 1, price + prices.get(src).get(_dst), minPrice));
        }
        return minPrice;
    }
}


class SolutionII {
    // tag: bfs
    // time: O(n^2)
    // space: O(n)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // minCost[x]: min cost so far from city src to city x
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new HashMap<>());
        for (int[] f : flights) {
            int u = f[0]; int v = f[1]; int w = f[2];
            graph.get(u).put(v, w);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        int stops = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int u = q.poll();
                for (Map.Entry<Integer, Integer> e : graph.get(u).entrySet()) {
                    int v = e.getKey();
                    int w = e.getValue();
                    if (v != dst && stops == K) continue; // hit stops limit
                    if (minCost[u] + w < minCost[v]) {
                        minCost[v] = minCost[u] + w;
                        q.offer(v);
                    }
                }
            }
            stops++;
        }
        return minCost[dst] != Integer.MAX_VALUE ? minCost[dst] : -1;
    }
}

