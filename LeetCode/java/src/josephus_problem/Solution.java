package josephus_problem;

import java.util.*;

/**
 * Created by Xiaotian on 3/15/18.
 */
public class Solution {
    // https://www.geeksforgeeks.org/josephus-problem-set-1-a-on-solution/
    // brutal force, iterative
    public int josephus(int n, int k) {
        Set<Integer> alive = new HashSet<>();
        for (int i = 1; i <= n; i++) alive.add(i);

        int pos = 1;
        while (alive.size() != 1) {
            int currK = k;
            for (int i = pos; i <= n + pos; i++) {
                int _i = i % n != 0 ? i % n : n;
                if (!alive.contains(_i)) continue;
                if (currK == 1) {
                    alive.remove(_i);
                    pos++;
                    pos = pos % n != 0 ? pos % n : n;
                    while (!alive.contains(pos)) {
                        pos++;
                        pos = pos % n != 0 ? pos % n : n;
                    }
                    break;
                }
                currK--;
            }
        }
        return alive.iterator().next();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().josephus(5, 2));
        System.out.println(new Solution().josephus(14, 2));
    }
}
