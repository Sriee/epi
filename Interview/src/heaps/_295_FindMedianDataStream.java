package heaps;

import java.util.*;

/**
 * Approach 1: Using Two Heaps
 *
 * We will maintain two heaps
 *   -> left will be a Max Heap. It will contain n / 2 + 1 elements that will form the lower part of the array
 *   -> right will be a Min Heap. It will contain n / 2 elements that will form the higher part of the array
 *
 * After adding the members we will re-balance the two heaps such that (left heap size) - (right heap size) = 0 or 1
 *
 * TC:
 *
 *   addNum
 *   ======
 *      Insert into heap (left or right) -> O(log n)
 *      re-balance -> remove + add ->  2(log n)
 *      Total -> 3 O(log n) = O(log n)
 *
 *  findMedian() - O(1)
 *
 * SC: O(n) - Space for holding n elements.
 */
public class _295_FindMedianDataStream {

    PriorityQueue<Integer> leftMaxHeap, rightMinHeap;

    public _295_FindMedianDataStream() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (leftMaxHeap.isEmpty() || num <= leftMaxHeap.peek()) {
            leftMaxHeap.add(num);
        } else {
            rightMinHeap.add(num);
        }

        rebalance();
    }

    private void rebalance() {
        if (leftMaxHeap.size() == rightMinHeap.size())
            return;

        // NOTE: Using offer and poll methods are increasing the run time of the solution. Stick to add & remove methods
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.add(leftMaxHeap.remove());
        } else if (leftMaxHeap.size() < rightMinHeap.size()){
            leftMaxHeap.add(rightMinHeap.remove());
        }
    }

    public double findMedian() {
        return leftMaxHeap.size() == rightMinHeap.size() ? leftMaxHeap.peek() / 2.0 + rightMinHeap.peek() / 2.0 : leftMaxHeap.peek();
    }

    /*
     * Approach 2: Using insertion sort
     *
     * We assume that the current list is already sorted. When a new number comes, we have to add it at it's right position
     * How do we find the right position?
     *   Since the list is sorted we use binary search to find the position.
     *
     * TC: O(log n) + O(n) = O(n)
     *     O(log n) - For binary search.
     *     O(n) - The elements in the list should be shifted to make room for the new element.
     * SC: O(n) - Space for holding n elements.
     * /
    List<Integer> lst;

    public _295_FindMedianDataStream() {
        lst = new ArrayList<>();
    }

    public void addNum(int num) {
        /* Note: Collections.binarySearch work in a weird way.
         *   if (element present in lst)
         *      return its idx;
         *   else
         *      return -( insertion position ) - 1
         *
         * For example, lst = [1, 2, 3, 10, 20]
         * Collections.binarySearch(lst, 10) will give 3
         * Collections.binarySearch(lst, 13) will give -5
         *      13 should be inserted at idx 4
         *      - (insertion position) - 1  = -(4) - 1 = -5
         * /
        int idx = Collections.binarySearch(lst, num);
        if (idx < 0) {
            idx = Math.abs(idx) - 1;
        }

        lst.add(idx, num);
    }

    public double findMedian() {
        int n = lst.size();

        return (n & 1) == 1 ? lst.get(n / 2) : lst.get(n / 2 - 1) / 2.0 + lst.get(n / 2) / 2.0;
    }
    */

    public static void main(String[] args) {
        _295_FindMedianDataStream fmds = new _295_FindMedianDataStream();
        fmds.addNum(1);
        // arr = [1], expected = 1.000
        System.out.println(fmds.findMedian());

        fmds.addNum(3);
        fmds.addNum(-1);
        // arr = [-1, 1, 3], expected = 1.000
        System.out.println(fmds.findMedian());

        fmds.addNum(0);
        fmds.addNum(5);
        fmds.addNum(8);
        // arr = [-1, 0, 1, 3, 5, 8], expected = 2.00
        System.out.println(fmds.findMedian());
    }
}
