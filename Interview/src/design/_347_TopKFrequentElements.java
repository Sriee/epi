package design;

import java.util.*;

/*
 * Top K frequent x problem's can be solved using Heap. But there are other two approaches as well. The other two
 * approaches have better Time and space complexity.
 *
 * Heap Approach (Default Approach):
 *  TC: O(n log k)
 *  SC: O(k)
 *
 * Doubly Linked List Approach (Implementation takes some time. We spent 1 hour to solve and write out the solution):
 *  TC: O(n) + O(k) = O(n)
 *  SC: O(n) + O(2 * m) (where m = max frequency) + O(k) (result) = O(n)
 *
 * Quick Select Approach (Optimal Approach)
 *  TC: O(n)
 *  SC: O(1)
 */
class _347_TopKFrequentElements {

    // Note: Not covering heap approach here. It is covered in a similar problem - _692_TopKFrequentWords
    private static class DLLNode {
        int val, freq;
        DLLNode prev, next;

        public DLLNode(int v, int f) {
            this.val = v;
            this.freq = f;
        }
    }

    private static class DList {
        DLLNode head = new DLLNode(-1, -1), tail = new DLLNode(-1, -1);

        public DList() {
            head.next = tail;
            tail.prev = head;
        }

        public void addLast(DLLNode node) {
            insert(node, this.tail.prev);
        }

        private void insert(DLLNode node, DLLNode prev) {
            node.next = prev.next;
            node.next.prev = node;

            prev.next = node;
            node.prev = prev;
        }

        public void remove(DLLNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }

    Map<Integer, DLLNode> nodeMap = new HashMap<>();
    Map<Integer, DList> freqMap = new HashMap<>();
    int max = Integer.MIN_VALUE;

    /*
     * Approach 1: Doubly Linked List Approach.
     */
    public int[] topKFrequent(int[] nums, int k) {
        freqMap.put(1, new DList());
        for (int i : nums)
            insert(i);

        System.out.println("After insertion to DLL List.");
        printList(freqMap);
        printNode(nodeMap);
        System.out.print("======================================\n");

        int[] res = new int[k];
        int j = 0;
        while (k > 0) {
            DList lst = freqMap.get(max);
            DLLNode iter = lst.head.next;

            while (iter != lst.tail & j <= k) {
                res[j++] = iter.val;
                k--;
                iter = iter.next;
            }

            max--;
        }

        return res;
    }

    private void insert(int num) {
        DLLNode node = nodeMap.get(num);

        if (node == null) {
            node = new DLLNode(num, 1);
            DList lst = freqMap.get(1);
            lst.addLast(node);

            nodeMap.put(num, node);
        } else {
            DList oldList = freqMap.get(node.freq);
            oldList.remove(node);

            node.freq++;

            DList newList = freqMap.getOrDefault(node.freq, new DList());
            newList.addLast(node);

            freqMap.put(node.freq, newList);
        }

        max = Math.max(node.freq, max);
    }

    private void reset() {
        freqMap.clear();
        nodeMap.clear();
        freqMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    private void printNode(Map<Integer, DLLNode> map) {
        for (Map.Entry<Integer, DLLNode> entry : map.entrySet()) {
            DLLNode node = entry.getValue();
            System.out.printf("%d = (%d, %d)\n", entry.getKey(), node.val, node.freq);
        }
    }

    private void printList(Map<Integer, DList> map) {
        for (Map.Entry<Integer, DList> entry : map.entrySet()) {
            DList lst = entry.getValue();
            System.out.printf("%d = [ ", entry.getKey());

            DLLNode iter = lst.head.next;
            while (iter != lst.tail) {
                System.out.printf("%d ", iter.val);
                iter = iter.next;
            }
            System.out.print("]\n");
        }
    }

    public static void main(String[] args) {
        _347_TopKFrequentElements tkfe = new _347_TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(tkfe.topKFrequent(nums, 2)));

        tkfe.reset();
        nums = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(tkfe.topKFrequent(nums, 2)));
    }
}