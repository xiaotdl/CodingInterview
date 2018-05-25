package templates.data_structures.hashtable;

/**
 * Created by Xiaotian on 4/22/18.
 */
// credit: https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
// Java program to demonstrate implementation of our
// own hash table with chaining for collision detection
import java.util.ArrayList;
import java.util.List;


class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }
}

class HashTableImpl<K, V> {
    private final static float LOAD_FACTOR = 0.75f;

    // buckets is used to store array of chains
    private List<Node<K, V>> table;

    // Current capacity of nodes
    private int CAPACITY;

    // Current size of nodes
    private int size;

    // Constructor (Initializes capacity, size and empty chains.)
    public HashTableImpl() {
        table = new ArrayList<>();
        CAPACITY = 1 << 4;
        size = 0;

        // Create empty chains
        for (int i = 0; i < CAPACITY; i++) {
            table.add(null);
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    // This implements hash function to find index for a key
    private int hash(K key) {
        return key.hashCode() % CAPACITY;
    }

    // Method to remove a given key
    public V remove(K key) {
        // Apply hash function to find index for given key
        int bucketIndex = hash(key);

        // Get head of chain
        Node<K, V> head = table.get(bucketIndex);

        // Search for key in its chain
        Node<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) break;
            prev = head;
            head = head.next;
        }

        // If key was not there
        if (head == null) return null;

        // Reduce size
        size--;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else
            table.set(bucketIndex, head.next);

        return head.value;
    }

    // Returns value for a key
    public V get(K key) {
        // Find head of chain for given key
        int bucketIndex = hash(key);
        Node<K, V> head = table.get(bucketIndex);

        // Search key in chain
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }

        // If key not found
        return null;
    }

    // Adds a key value pair to hash
    public void add(K key, V value) {
        // Find head of chain for given key
        int bucketIndex = hash(key);
        Node<K, V> head = table.get(bucketIndex);

        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert key in chain
        size++;
        head = table.get(bucketIndex);
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        table.set(bucketIndex, newNode);

        // If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*size)/CAPACITY >= LOAD_FACTOR) {
            resize();
        }
    }

    private void resize() {
        List<Node<K, V>> temp = table;
        table = new ArrayList<>();
        CAPACITY = 2 * CAPACITY;
        size = 0;
        for (int i = 0; i < CAPACITY; i++) {
            table.add(null);
        }

        for (Node<K, V> node : temp) {
            while (node != null) {
                add(node.key, node.value);
                node = node.next;
            }
        }
    }

    public static void main(String[] args) {
        HashTableImpl<String, Integer> map = new HashTableImpl<>();
        map.add("this",1 );
        map.add("coder",2 );
        map.add("this",4 );
        map.add("hi",5 );
        System.out.println(map.get("coder"));
        System.out.println(map.size());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
    }
}

