package flatten_nested_list_iterator;

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
import java.util.Iterator;
import java.util.*;

class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
//        for (NestedInteger e : nestedList) {
//            stack.push(e);
//        }
        stack.push(nestedList.get(0)); // first is always a list
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if (hasNext()) {
            return stack.pop().getInteger();
        }
        return null;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) return false;

        NestedInteger top = stack.peek();
        if (top.isInteger()) {
            return true;
        } else {
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
            return hasNext();
        }

    }

    @Override
    public void remove() {
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v.add(i.next());
 */

class NestedInteger {
    public boolean isInteger() {return false;}
    public Integer getInteger() {return 0;}
    public List<NestedInteger> getList() {return new ArrayList<NestedInteger>();}
}


class NestedIteratorII implements Iterator<Integer> {

    Stack<NestedInteger> stack;
    public NestedIteratorII(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        pushToStack(nestedList);
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if (hasNext()) {
            return stack.pop().getInteger();
        }
        return null;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushToStack(stack.pop().getList());
        }

        return !stack.isEmpty();
    }

    @Override
    public void remove() {}

    // put list into stack reversely
    private void pushToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> tmpStack = new Stack<>();
        for (NestedInteger nested : nestedList) {
            tmpStack.push(nested);
        }

        while (!tmpStack.isEmpty()) {
            stack.push(tmpStack.pop());
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v.add(i.next());
 */

class NestedIteratorIII implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    public NestedIteratorIII(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return !stack.isEmpty();
    }
}
