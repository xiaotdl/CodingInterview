package lfu_cache;

import java.util.*;

/**
 * Created by Xiaotian on 4/9/18.
 */
public class Solution {
    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1,1);
        lfu.put(1,1);
        lfu.put(1,1);
        lfu.put(2,2);
        lfu.put(2,2);
        lfu.put(2,2);
        lfu.put(3,3);
        System.out.println(lfu.k2cnt);
    }
}

class LFUCache {
    // credit: https://leetcode.com/problems/lfu-cache/discuss/94521/JAVA-O(1)-very-easy-solution-using-3-HashMaps-and-LinkedHashSet
    // Why we didn’t need to care about nextMin and we just could add 1 to min?
    // Now it makes sense that min will always reset to 1 when adding a new item.
    // And also, min can never jump forward more than 1 since updating an item only increments it’s count by 1.

    // tag: hash, bucket
    // time:
    //   get: O(1)
    //   put: O(1)
    // space: O(n)

    int capacity;
    int min;
    Map<Integer, Integer> k2v;
    Map<Integer, Integer> k2cnt;
    Map<Integer, LinkedHashSet<Integer>> cnt2keys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        min = 0;
        k2v = new HashMap<Integer, Integer>(); // key2v
        k2cnt = new HashMap<Integer, Integer>(); // key2cnt
        cnt2keys = new HashMap<Integer, LinkedHashSet<Integer>>(); // cnt2set(key), cnt bucket, set(key) is in LRU order
    }

    public int get(int key) {
        if (!k2v.containsKey(key))
            return -1;

        int v = k2v.get(key);
        int cnt = k2cnt.get(key);

        // remove key from old cnt bucket
        LinkedHashSet<Integer> oldKeys = cnt2keys.get(cnt);
        oldKeys.remove(key);
        if (oldKeys.isEmpty()) cnt2keys.remove(cnt);

        // update min
        if (cnt == min && oldKeys.isEmpty())
            min++;

        // put key into new cnt bucket
        cnt++;
        cnt2keys.putIfAbsent(cnt, new LinkedHashSet<Integer>());
        LinkedHashSet<Integer> newKeys = cnt2keys.get(cnt);
        newKeys.add(key);

        // update key's new cnt
        k2cnt.put(key, cnt);

        return v;


    }

    public void put(int key, int val) {
        if (capacity <= 0) return;

        if (k2v.containsKey(key)) {
            k2v.put(key, val);
            get(key);
            return;
        }

        if (k2v.size() == capacity) {
            LinkedHashSet<Integer> minKeys = cnt2keys.get(min);
            int keyToRemove = minKeys.iterator().next(); // points to LRU key in min cnt bucket, oldest->...->latest
            minKeys.remove(keyToRemove);
            if (minKeys.isEmpty()) cnt2keys.remove(min);
            k2cnt.remove(keyToRemove);
            k2v.remove(keyToRemove);
        }

        min = 1;
        cnt2keys.putIfAbsent(min, new LinkedHashSet<Integer>());
        LinkedHashSet<Integer> minKeys = cnt2keys.get(min);
        minKeys.add(key);
        k2cnt.put(key, min);
        k2v.put(key, val);
    }
}

/**
* Your LFUCache object will be instantiated and called as such:
* LFUCache obj = new LFUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/
