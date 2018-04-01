package lru_cache;

import java.util.*;

/**
 * Created by Xiaotian on 2/9/18.
 */
public class Solution {
}

class LRUCache {
    // get O(1), set O(1), 每次get把node移到tail，超过capacity把head的node evict
    // set的时候利用get的move_to_tail来更新已有值
    // map<key, Node(key, value)> + doubleLinkedList

    class Node { // double linkedlist node
        int key;
        int value;
        Node prev;
        Node next;
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
            prev = next = null;
        }
    }

    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map; // key2node
    /*
    * @param capacity: An integer
    */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, Node>();
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node curr = map.get(key);

        // remove curr node
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        // move curr node to tail
        move_to_tail(curr);

        return curr.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
        if (this.get(key) != -1) {
            map.get(key).value = value;
            return;
        }

        if (map.size() == capacity) {
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node curr = new Node(key, value);
        map.put(key, curr);
        move_to_tail(curr);
    }

    private void move_to_tail(Node curr) {
        tail.prev.next = curr;
        curr.prev = tail.prev;
        curr.next = tail;
        tail.prev = curr;
    }
}

class LRUCacheII {
    // same as LRUCache
    // O(1) get, set => hashmap
    // O(1) add/remove => double linked list
    class DoubleLinkedNode {
        int key;
        int val;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
        DoubleLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
            prev = next = null;
        }
    }

    DoubleLinkedNode head;
    DoubleLinkedNode tail;
    Map<Integer, DoubleLinkedNode> map; // key2node
    int capacity;
    public LRUCacheII(int capacity) {
        this.capacity = capacity;
        head = new DoubleLinkedNode(-1, -1);
        tail = new DoubleLinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, DoubleLinkedNode>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DoubleLinkedNode node = map.get(key);
        remove(node);

        addToTail(node);

        return node.val;
    }

    public void put(int key, int val) {
        if (get(key) != -1) {
            map.get(key).val = val;
            return;
        }

        if (map.size() == capacity) {
            map.remove(head.next.key);
            remove(head.next);
        }

        DoubleLinkedNode node = new DoubleLinkedNode(key, val);
        addToTail(node);
        map.put(key, node);
    }

    private void remove(DoubleLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToTail(DoubleLinkedNode node) {
        tail.prev.next = node;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
    }
}
