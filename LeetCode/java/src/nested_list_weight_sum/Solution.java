package nested_list_weight_sum;

/**
 * Created by Xiaotian on 10/7/17.
 */

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    // tag: dfs, recursion, top down
    // time: O(n)
    // space: O(1)
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.size() == 0) return 0;

        int sum = 0;
        for (NestedInteger e : nestedList) {
            if (e.isInteger()) {
                sum += depth * e.getInteger();
            } else {
                sum += dfs(e.getList(), depth + 1);
            }
        }
        return sum;
    }
}

class NestedInteger {
    // placeholder
    public NestedInteger() {}
    public boolean isInteger() {return false;}
    public Integer getInteger() {return 0;}
    public List<NestedInteger> getList() {return new ArrayList<NestedInteger>();}
}

class SolutionII {
    // Same as Solution
    // dfs, top down
    int sum;
    public int depthSum(List<NestedInteger> nestedList) {
        sum = 0;
        dfs(nestedList, 1);
        return sum;
    }

    private void dfs(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            }
            else {
                dfs(ni.getList(), depth + 1);
            }
        }
    }
}
