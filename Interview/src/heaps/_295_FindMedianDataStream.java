package heaps;

import java.util.*;

/**
 * Approach 1: Sorting the input numbers
 *
 * Straight forward implementation. We sort the numbers stored in the internal array each time findMedian gets called.
 * Receiving TLE
 *
 * TC: O(n log n)
 * SC: O(n)
 */
public class _295_FindMedianDataStream {

    List<Integer> lst;

    public _295_FindMedianDataStream() {
        lst = new ArrayList<>();
    }

    public void addNum(int num) {
        lst.add(num);
    }

    public double findMedian() {
        Collections.sort(lst);
        int n = lst.size();

        return (n & 1) == 1 ? lst.get(n / 2) : lst.get(n / 2 - 1) / 2.0 + lst.get(n / 2) / 2.0;
    }

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
