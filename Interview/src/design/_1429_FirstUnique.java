package design;

import java.util.*;

/**
 * Design: Using Doubly Linked List technique similar to the LRU Cache technique.
 */
public class _1429_FirstUnique {

    private static class DLLNode {
        int val;
        DLLNode prev, next;

        public DLLNode(int x) {
            this.val = x;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.format("%d <- %d -> %d",
                    prev == null ? null : prev.val, val,
                    next == null ? null : next.val);
        }
    }

    // Dummy head and tail trick. These two nodes act as boundary that will make inserting/deleting nodes easier.
    // Otherwise we need to perform null checks and make head/tail assignment properly.
    DLLNode head = new DLLNode(Integer.MIN_VALUE), tail = new DLLNode(Integer.MAX_VALUE);

    // We are maintaining this map to fetch a node in O(1) time. Since, in a Doubly Linked List we can insert/delete a
    // node independently.
    Map<Integer, DLLNode> map;

    public _1429_FirstUnique(int[] nums) {
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();

        for (int i : nums) {
            add(i);
        }
    }

    public int showFirstUnique() {
        return head.next == tail ? -1 : head.next.val;
    }

    public void add(int value) {
        if (map.containsKey(value)) {
            remove(map.get(value));
        } else {
            DLLNode node = new DLLNode(value);
            map.put(value, node);
            addLast(node);
        }
    }

    private void remove(DLLNode node) {
        if (node.prev == null || node.next == null)
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    private void addLast(DLLNode node) {
        node.next = tail;
        node.prev = tail.prev;
        node.prev.next = node;
        tail.prev = node;
    }

    private void print() {
        DLLNode iter = head.next;

        while (iter != tail) {
            System.out.print(iter.val + " -> ");
            iter = iter.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 5, 7};
        _1429_FirstUnique fq = new _1429_FirstUnique(nums);

        System.out.println(fq.showFirstUnique());
        fq.add(5);
        fq.print();

        System.out.println(fq.showFirstUnique());
        fq.add(3);
        fq.print();

        fq.add(7);
        System.out.println(fq.showFirstUnique());
    }
}
