package flatten_list;

import java.util.*;

/**
 * Created by Xiaotian on 10/6/17.
 */
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
    // tag: iterator
    // time: O(n)
    // space: O(n)
    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> res = new ArrayList<>();
        Stack<NestedInteger> stack = new Stack<>();
        for (NestedInteger e : nestedList) {
            stack.push(e);
        }

        while(!stack.isEmpty()) {
            NestedInteger e = stack.pop();
            if (e.isInteger()) {
                res.add(e.getInteger());
            } else {
                List<NestedInteger> list = e.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
            }
        }
        return res;
    }
}

class NestedInteger {
    public boolean isInteger() {return false;}
    public Integer getInteger() {return 0;}
    public List<NestedInteger> getList() {return new ArrayList<NestedInteger>();}

}
