package hash_table;

import java.util.*;

/*
 * Approach 1: Doubly Linked List
 *
 * 17 / 19 test cases passes. getRandom returns elements in cyclic permutation. LC is running a Uniform Distribution
 * check on the result to make sure the elements are distributed without having a pattern.
 *
 */
class _380_RandomizedSet {

    private class DLLNode {
        int key, count;
        DLLNode prev, next;

        public DLLNode(int key, int count) {
            this.key = key;
            this.count = count;
        }
    }

    DLLNode head = new DLLNode(-1, Integer.MIN_VALUE), tail = new DLLNode(-1, Integer.MIN_VALUE);
    Map<Integer, DLLNode> map;
    Random rand;

    /**
     * Initialize your data structure here.
     */
    public _380_RandomizedSet() {
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        DLLNode node = new DLLNode(val, 0);
        insertNode(node, head);
        map.put(val, node);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;

        DLLNode node = map.get(val);
        removeNode(node);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        DLLNode node = head.next;
        DLLNode curr = node;
        node.count += rand.nextInt(79);

        while (curr.count <= node.count && curr != tail)
            curr = curr.next;

        removeNode(node);
        insertNode(node, curr.prev);
        return node.key;
    }

    private void insertNode(DLLNode node, DLLNode prev) {
        node.next = prev.next;
        node.next.prev = node;

        prev.next = node;
        node.prev = prev;
    }

    private void removeNode(DLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}