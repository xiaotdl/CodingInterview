package insert_delete_getrandom_o1_duplicates_allowed;

import java.util.*;

/**
 * Created by Xiaotian on 9/11/17.
 */
class RandomizedCollection {
    // using a LinkedHashSet for O(1) iteration over large items.
    // An iterator over a normal HashSet is actually O(h/n), where h is table capacity.
    // So it is not a solution to our problem requiring O(1) time.
    // tag: array, hash
    // time:
    //   insert: O(1)
    //   remove: O(1)
    //   getRandom: O(1)
    // space: O(n)
    List<Integer> list;
    Map<Integer, Set<Integer>> map; // num2indexes
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        boolean isContained = map.containsKey(val);
        map.putIfAbsent(val, new LinkedHashSet<Integer>());

        list.add(val);
        map.get(val).add(list.size() - 1);
        return !isContained;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int currIdx = map.get(val).iterator().next();
        int lastIdx = list.size() - 1;
        if (currIdx != lastIdx) {
            if (list.get(currIdx) == list.get(lastIdx)) {
                // remove the largest idx of same value, otherwise IndexOutOfBoundsException
                currIdx = lastIdx;
            }
            else {
                // move lastVal to currIdx in list
                int lastVal = list.get(lastIdx);
                list.set(currIdx, lastVal);
                // update lastVal's idx in map
                map.get(lastVal).remove(lastIdx);
                map.get(lastVal).add(currIdx);
            }
        }
        // remove last (swapped curr val) in list
        list.remove(lastIdx);
        // remove curr val's idx in map
        map.get(val).remove(currIdx);
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int i = rand.nextInt(list.size());
        return list.get(i);
    }
}
