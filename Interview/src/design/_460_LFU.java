package design;

import java.util.*;

public class _460_LFU {

    private class DLLNode {
        public int key, value, freq;
        public DLLNode prev, next;

        public DLLNode(int k, int v) {
            this.key = k;
            this.value = v;
            this.freq = 1;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", this.key, this.value);
        }
    }

    private class DLL {
        public int key, size = 0;
        private final DLLNode head, tail;

        public DLL(int k) {
            this.key = k;
            this.head = new DLLNode(-1, -1);
            this.tail = new DLLNode(-1, -1);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(DLLNode DLLNode) {
            DLLNode.next = head.next;
            DLLNode.prev = head;

            DLLNode.next.prev = DLLNode;
            head.next = DLLNode;

            this.size++;
        }

        public DLLNode popTail() {
            if (this.size <= 0)
                return null;

            DLLNode tailNode = tail.prev;
            remove(tailNode);
            return tailNode;
        }

        private void remove(DLLNode DLLNode) {
            if (DLLNode.next == null || DLLNode.prev == null)
                return;

            DLLNode.prev.next = DLLNode.next;
            DLLNode.next.prev = DLLNode.prev;

            DLLNode.next = null;
            DLLNode.prev = null;

            this.size--;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void print() {
            System.out.println(this._print());
        }

        private StringBuilder _print() {
            StringBuilder sb = new StringBuilder();
            DLLNode iter = head.next;
            while (iter != tail) {
                sb.append(iter).append(" -> ");
                iter = iter.next;
            }

            sb.append("null");
            return sb;
        }

        @Override
        public String toString() {
            return this._print().toString();
        }
    }

    private int count = 0, min = 1;
    private final int capacity;
    private final Map<Integer, DLL> freqMap;
    private final Map<Integer, DLLNode> nodeMap;

    public _460_LFU(int capacity) {
        this.capacity = capacity;
        this.freqMap = new HashMap<>();
        this.nodeMap = new HashMap<>();
    }

    public int get(int key) {
        DLLNode DLLNode = nodeMap.get(key);
        if (DLLNode == null)
            return -1;

        update(DLLNode);
        return DLLNode.value;
    }

    private void update(DLLNode DLLNode) {
        DLL oldList = freqMap.get(DLLNode.freq);
        oldList.remove(DLLNode);

        if (min == DLLNode.freq && oldList.isEmpty())
            min++;

        DLLNode.freq++;
        DLL newList = freqMap.getOrDefault(DLLNode.freq, new DLL(DLLNode.freq));
        newList.addFirst(DLLNode);
        freqMap.put(DLLNode.freq, newList);
    }

    public void put(int key, int value) {
        if (capacity < 0)
            return;

        DLLNode DLLNode = nodeMap.get(key);
        if (DLLNode != null) {
            DLLNode.value = value;
            update(DLLNode);
        } else {
            DLLNode = new DLLNode(key, value);
            nodeMap.put(key, DLLNode);

            if (count == capacity) {
                DLL tail = freqMap.get(min);
                DLLNode t = tail.popTail();
                if (t != null)
                    nodeMap.remove(t.key);
                count--;
            }

            DLL dll = freqMap.getOrDefault(1, new DLL(1));
            dll.addFirst(DLLNode);
            freqMap.put(DLLNode.freq, dll);
            count++;
            min = 1;
        }
    }

    public static void main(String[] args) {
        _460_LFU lfu = new _460_LFU(4);
        lfu.put(10, 13);
        lfu.put(3, 17);
        lfu.put(6, 11);
        lfu.put(10, 5);
        lfu.put(9, 10);
        System.out.println(lfu.get(13) + " " + lfu.freqMap);

        lfu.put(2, 19);
        System.out.println(lfu.get(2) + " " + lfu.get(3) + " " + lfu.freqMap);

        lfu.put(5, 25);
        System.out.println(lfu.get(8));

        lfu.put(9, 22);
        lfu.put(1, 5);
        lfu.put(1, 30);
        System.out.println(lfu.get(11) + " " + lfu.freqMap + " " + lfu.min);
    }
}
