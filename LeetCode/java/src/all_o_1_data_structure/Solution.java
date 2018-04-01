package all_o_1_data_structure;

import java.util.*;

/**
 * Created by Xiaotian on 3/30/18.
 */
public class Solution {
}

class AllOne {
    // 用Node来表示计数桶, inc/dec把key移到相邻的桶
    // 用Map<key, Node>来记录每个key现在在桶的位置
    // inc: O(1)
    // dec: O(1)
    // getMaxKey: O(1)
    // getMinKey: O(1)
    class DoubleLinkedNode { // cnt bucket with a bunch of keys
        int cnt;
        Set<String> keys;
        DoubleLinkedNode prev, next;
        public DoubleLinkedNode(int cnt) {
            this.cnt = cnt;
            this.keys = new LinkedHashSet<>();
            prev = next = null;
        }
    }

    Map<String, DoubleLinkedNode> map;
    DoubleLinkedNode head;
    DoubleLinkedNode tail;
    public AllOne() {
        map = new HashMap<>(); // key2bucket
        head = new DoubleLinkedNode(0);
        tail = new DoubleLinkedNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            DoubleLinkedNode bucket = head.next;
            if (bucket == tail || bucket.cnt != 1) {
                bucket = new DoubleLinkedNode(1);
                insertAfter(bucket, head);
            }

            bucket.keys.add(key);
            // update key's new bucket in map
            map.put(key, bucket);
            return;
        }

        DoubleLinkedNode bucket = map.get(key);
        DoubleLinkedNode nextBucket = bucket.next;
        if (nextBucket == tail || nextBucket.cnt != bucket.cnt + 1) {
            nextBucket = new DoubleLinkedNode(bucket.cnt + 1);
            insertAfter(nextBucket, bucket);
        }
        // move key from curr bucket to next bucket
        nextBucket.keys.add(key);
        bucket.keys.remove(key);
        if (bucket.keys.isEmpty()) remove(bucket);
        // update key's new bucket in map
        map.put(key, nextBucket);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;

        DoubleLinkedNode bucket = map.get(key);
        if (bucket.cnt == 1) {
            bucket.keys.remove(key);
            map.remove(key);
            if (bucket.keys.isEmpty()) remove(bucket);
            return;
        }
        DoubleLinkedNode prevBucket = bucket.prev;
        if (prevBucket == head || prevBucket.cnt != bucket.cnt - 1) {
            prevBucket = new DoubleLinkedNode(bucket.cnt - 1);
            insertAfter(prevBucket, bucket.prev);
        }
        // move key from curr bucket to prev bucket
        prevBucket.keys.add(key);
        bucket.keys.remove(key);
        if (bucket.keys.isEmpty()) remove(bucket);
        // update key's new bucket in map
        map.put(key, prevBucket);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail) return "";
        return tail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }

    private void insertAfter(DoubleLinkedNode newNext, DoubleLinkedNode curr) {
        DoubleLinkedNode oldNext = curr.next;
        curr.next = newNext;
        newNext.next = oldNext;
        oldNext.prev = newNext;
        newNext.prev = curr;
    }

    private void remove(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
