package _24_game;

import java.util.*;

/**
 * Created by Xiaotian on 4/7/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/24-game/discuss/107673/JAVA-Easy-to-understand.-Backtracking.
    // tag: dfs, backtrack
    // time: O(6^C(4,2))
    // space: O(1)
    private final static double eps = 1e-8;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) list.add((double) num);
        return dfs(list);
    }

    private boolean dfs(List<Double> list) {
        System.out.println(list.size());
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24.0) < eps;
        }

        for (int i = 0; i < list.size(); i++) {
            Double a = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Double b = list.get(j);
                List<Double> pairRes = new ArrayList<>();
                pairRes.add(a + b);
                pairRes.add(-a + b);
                pairRes.add(a - b);
                pairRes.add(a * b);
                if (Math.abs(a) > eps) pairRes.add(b / a);
                if (Math.abs(b) > eps) pairRes.add(a / b);

                list.remove(j); // NOTE: remove larger index first
                list.remove(i);
                for (double r : pairRes) {
                    list.add(r);
                    if (dfs(list)) return true;
                    list.remove(list.size() - 1);
                }
                list.add(i, a); // NOTE: add smaller index first
                list.add(j, b);
            }
        }
        return false;
    }
}
