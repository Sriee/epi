package design;

import java.util.*;

public class _146_LRU {
    private static class DLLNode {
        int key, value;
        DLLNode prev, next;

        public DLLNode(int k, int v) {
            this.key = k;
            this.value = v;

            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", this.key, this.value);
        }
    }

    private int count = 0;       // Keep track of the number of elements in the cache
    private final int capacity;  // Cache capacity

    // Dummy head and tail trick. These two nodes act as boundary that will make inserting/deleting nodes easier.
    // Otherwise we need to perform null checks and make head/tail assignment properly.
    private final DLLNode head = new DLLNode(Integer.MIN_VALUE, -1), tail = new DLLNode(Integer.MAX_VALUE, -1);

    // We are maintaining this map to fetch a node in O(1) time. Since, in a Doubly Linked List we can insert/delete a
    // node independently.
    private final Map<Integer, DLLNode> cache;

    public _146_LRU(int capacity) {
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;

        cache = new HashMap<>();
    }

    private void addFirst(DLLNode node) {
        node.next = head.next;
        node.next.prev = node;

        node.prev = head;
        head.next = node;
    }

    private void remove(DLLNode node) {
        // The below prevents us deleting head / tail nodes because of how we initialize head/tail
        // We assign only next to head - head.next = tail
        // We assign only prev to tail - tail.prev = head
        if (node.next == null || node.prev == null)
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.next = null;
        node.prev = null;
    }

    /**
     * Helper method to move a node to the start of the list
     *
     * @param node Doubly Linked List Node
     */
    private void moveToFirst(DLLNode node) {
        remove(node);
        addFirst(node);
    }

    /**
     * Helper method to remove a tail. This method will be called to evict a node from our cache.
     *
     * @return Key of the popped tail node
     */
    private int popTail() {
        DLLNode tailNode = tail.prev;
        remove(tailNode);
        return tailNode.key;
    }

    /**
     * Utility method to print the current state of our Doubly Linked List
     */
    private void print() {
        DLLNode iter = head.next;

        while (iter != tail) {
            System.out.printf("[%d, %d] -> ", iter.key, iter.value);
            iter = iter.next;
        }

        System.out.println("null");
    }

    /**
     * Get method to retrieve the content of a cache.
     *
     * @param key Cache key
     * @return Value associated with cache key
     */
    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        DLLNode node = cache.get(key);
        // Since we have accessed this cache entry we are moving it to the start of our Doubly Linked List
        moveToFirst(node);

        return node.value;
    }

    public void put(int key, int value) {
        // If a cache entry is present we update the value associated with the cache key
        // and move the entry to the start since it is recently accessed.
        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.value = value;

            moveToFirst(node);
        } else { // Cache entry is not present
            // Create a new entry
            DLLNode newNode = new DLLNode(key, value);

            cache.put(key, newNode);
            addFirst(newNode);
            count++;

            // If the number of elements in the cache has exceeded the cache's capacity
            // the least recently used cache entry will be deleted.
            if (count > capacity) {
                int tailKey = popTail();
                cache.remove(tailKey);
                count--;
            }
        }
    }

    public static void main(String[] args) {
        _146_LRU lru = new _146_LRU(2);
        lru.put(2, 1);
        lru.put(1, 2);

        lru.print();
        lru.put(2, 3);
        lru.print();
        System.out.println(lru.cache);

        lru.put(4, 1);

        System.out.println(lru.get(1));
        System.out.println(lru.get(2));

        System.out.println(lru.cache);

        lru.put(3, 3);
        lru.print();
        System.out.println(lru.cache);

        lru.put(4, 4);
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }
}
