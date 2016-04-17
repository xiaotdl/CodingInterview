import java.util.HashMap;

public class LRUCache {

    // V1
    // Doubly LinkedList, Hash
    private class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hash = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    // @param capacity, an integer
    public Solution(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    // @return an integer
    public int get(int key) {
        if (!hash.containsKey(key)) {
            return -1;
        }

        // Unlink current node
        Node curr = hash.get(key);
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        // Move current node to tail
        this.add_to_cache(curr);

        return hash.get(key).value;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (this.get(key) != -1) {
            hash.get(key).value = value;
            return;
        }

        if (hash.size() == this.capacity) {
            hash.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node newNode = new Node(key, value);
        hash.put(key, newNode);
        add_to_cache(newNode);
    }

    private void add_to_cache(Node curr) {
        curr.prev = tail.prev;
        tail.prev = curr;
        curr.prev.next = curr;
        curr.next = tail;
    }
}

