package nested_list_weigth_sum_ii;

import java.util.*;

/**
 * Created by Xiaotian on 3/3/18.
 */
class Solution {
    // tag: dfs, recursion, top down
    // time: O(n)
    // space: O(1)
    int sum;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        sum = 0;
        int maxDepth = dfsGetDepth(nestedList);
        dfsGetSum(nestedList, maxDepth);
        return sum;
    }

    private int dfsGetDepth(List<NestedInteger> nestedList) {
        int maxDepth = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                maxDepth = Math.max(maxDepth, 1);
            }
            else {
                maxDepth = Math.max(maxDepth, dfsGetDepth(ni.getList()) + 1);
            }
        }
        return maxDepth;
    }

    private void dfsGetSum(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            }
            else {
                dfsGetSum(ni.getList(), depth - 1);
            }
        }
    }
}

class NestedInteger {
    // placeholder
    public NestedInteger() {}
    public boolean isInteger() {return false;}
    public Integer getInteger() {return 0;}
    public List<NestedInteger> getList() {return new ArrayList<NestedInteger>();}
}
