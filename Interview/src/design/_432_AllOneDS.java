package design;

import java.util.*;

/**
 * The structure for the All One DS
 * <p>
 * <p>
 * head -> [1] -> [2] -> ... [n] -> tail -- Used Tree Map to maintain the sorted count order
 * |
 * head -> [k1, v1] -> [k2, v2] -> tail
 * <p>
 * Solution Accepted but the run time of the solution is 100 ms.
 */
public class _432_AllOneDS {

    Map<String, Node> keyMap;
    TreeMap<Integer, DoublyLinkedList> aoMap;

    /**
     * Initialize your data structure here.
     */
    public _432_AllOneDS() {
        keyMap = new HashMap<>();
        aoMap = new TreeMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            aoMap.get(node.value).delete(node);

            if (aoMap.get(node.value).isEmpty())
                aoMap.remove(node.value);

            node.value++;
            aoMap.putIfAbsent(node.value, new DoublyLinkedList());
            aoMap.get(node.value).addFirst(node);
        } else {
            Node newNode = new Node(key);
            aoMap.putIfAbsent(1, new DoublyLinkedList());

            keyMap.put(key, newNode);
            aoMap.get(1).addFirst(newNode);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        Node node = keyMap.get(key);

        if (node == null)
            return;

        aoMap.get(node.value).delete(node);

        if (aoMap.get(node.value).isEmpty())
            aoMap.remove(node.value);

        node.value--;

        if (node.value == 0)
            keyMap.remove(key);
        else {
            aoMap.putIfAbsent(node.value, new DoublyLinkedList());
            aoMap.get(node.value).addFirst(node);
        }

    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return aoMap.get(aoMap.lastKey()).firstKey();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return aoMap.get(aoMap.firstKey()).firstKey();
    }

    public class Node {
        String key;
        int value;

        Node next, prev;

        public Node(String key) {
            this.key = key;
            this.value = 1;
        }

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[ %s, %d ]", key, value);
        }
    }

    public class DoublyLinkedList {
        Node head = new Node("HEAD"), tail = new Node("TAIL");

        public DoublyLinkedList() {
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            node.next.prev = node;

            head.next = node;
            node.prev = head;
        }

        public void delete(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;
        }

        public String firstKey() {
            if (isEmpty())
                return "";

            return head.next.key;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }

        @Override
        public String toString() {
            Node iter = head;
            StringBuilder sb = new StringBuilder();

            while (iter != null) {
                sb.append(iter).append("--> ");
                iter = iter.next;
            }

            sb.append("null");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        _432_AllOneDS ao = new _432_AllOneDS();

        ao.inc("hello");
        ao.inc("hello");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
        ao.inc("leet");
        System.out.println(ao.getMinKey());
        ao.dec("leet");
        System.out.println(ao.getMaxKey());
    }
}
