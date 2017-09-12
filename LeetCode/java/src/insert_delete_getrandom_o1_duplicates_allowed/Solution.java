package insert_delete_getrandom_o1_duplicates_allowed;

import java.util.*;

/**
 * Created by Xiaotian on 9/11/17.
 */
public class Solution {
    // tag: array, hash
    // time:
    //   insert: O(1)
    //   remove: O(1)
    //   getRandom: O(1)
    // space: O(n)
}

class RandomizedCollection {
    List<Integer> nums;             // index2num
    Map<Integer, Set<Integer>> map; // num2indexes
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        boolean isContained = map.containsKey(val);
        if (!isContained) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(nums.size() - 1);
        return !isContained;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int i = map.get(val).iterator().next();
        map.get(val).remove(i);
        if (i != nums.size() - 1) {
            int lastVal = nums.get(nums.size() - 1);
            nums.set(i, lastVal);
            map.get(lastVal).remove(nums.size() - 1);
            map.get(lastVal).add(i);
        }

        nums.remove(nums.size() - 1);
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

