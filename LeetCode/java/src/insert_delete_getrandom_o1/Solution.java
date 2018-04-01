package insert_delete_getrandom_o1;

import java.util.*;

/**
 * Created by Xiaotian on 9/10/17.
 */
public class Solution {
    // tag: array, hash
    // time:
    //   insert: O(1)
    //   remove: O(1)
    //   getRandom: O(1)
    // space: O(n)
}

class RandomizedSet {
    List<Integer> nums;        // index2num
    Map<Integer, Integer> map; // num2index
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        nums.add(val);
        map.put(val, nums.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        if (map.get(val) != nums.size() - 1) {
            swap(map.get(val), nums.size() - 1);
        }

        nums.remove(nums.size() - 1);
        map.remove(val);
        return true;
    }

    private void swap(int i1, int i2) {
        map.put(nums.get(i1), i2);
        map.put(nums.get(i2), i1);

        int tmp = nums.get(i1);
        nums.set(i1, nums.get(i2));
        nums.set(i2, tmp);
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

class RandomizedSetII {
    // implementation without swap
    // tag: array, hash
    // time:
    //   insert: O(1)
    //   remove: O(1)
    //   getRandom: O(1)
    // space: O(n)
    List<Integer> list;
    Map<Integer, Integer> map; // num2idx
    Random rand;
    public RandomizedSetII() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int currIdx = map.get(val);
        int lastIdx = list.size() - 1;
        if (currIdx != lastIdx) {
            // move lastVal to currIdx in list
            int lastVal = list.get(lastIdx);
            list.set(currIdx, lastVal);
            // update lastVal's idx in map
            map.put(lastVal, currIdx);
        }
        // remove last (swapped curr val) in list
        list.remove(lastIdx);
        // remove curr val in map
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int i = rand.nextInt(list.size());
        return list.get(i);

    }
}
