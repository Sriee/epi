package design;

import java.util.*;

/**
 * The optimized structure for the All One DS
 * <p>
 * head -> [k1, v1] -> [k2, v2] -> ... [kn, vn]-> tail
 * where n is sorted according to its value
 */
public class _432_AllOneDS {

    Map<String, DLLNode> keyMap;
    DLLNode head = new DLLNode("", 0), tail = new DLLNode("", 0);

    /**
     * Initialize your data structure here.
     */
    public _432_AllOneDS() {
        keyMap = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public void insert(DLLNode node, DLLNode prev) {
        node.next = prev.next;
        node.next.prev = node;

        prev.next = node;
        node.prev = prev;
    }

    public void remove(DLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (keyMap.containsKey(key)) {
            DLLNode node = keyMap.get(key);
            DLLNode current = node;

            while (current.value == node.value)
                current = current.next;

            remove(node);
            node.value++;
            insert(node, current.prev);
        } else {
            DLLNode newNode = new DLLNode(key);
            keyMap.put(key, newNode);
            insert(newNode, head);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        DLLNode node = keyMap.get(key);
        if (node == null)
            return;

        remove(node);
        if (node.value == 1)
            keyMap.remove(key);
        else {
            DLLNode current = node;
            while (current.value == node.value)
                current = current.prev;

            node.value--;
            insert(node, current);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return tail.prev.key;
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return head.next.key;
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
