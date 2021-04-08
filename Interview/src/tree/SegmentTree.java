package tree;

public class SegmentTree {

    int[] st; // Data structure to hold the segment tree

    // Initialize the segment tree
    public SegmentTree(int capacity) { this.st = new int[capacity]; }

    private void build(int[] arr, int start, int end, int index) {
        if(start > end)
            return;

        if(start == end) {
            st[index] = start;
        } else {
            int mid = start + (end - start) / 2;
            build(arr, start, mid, 2 * index + 1);
            build(arr, mid + 1, end, 2 * index + 2);

            int left = st[2 * index + 1];
            int right = st[2 * index + 2];

            /* Change the below build logic based on the type of segment tree you are constructing.
             * Example:
             *   Sum queries: st[index] = arr[left] + arr[right];
             *   Max queries: st[index] = arr[left] > arr[right] ? left : right;
             *
             * Here we are constructing a segment tree to give us minimum value of a segment
             */
            st[index] = arr[left] < arr[right] ? left : right;
        }
    }

    public void buildSegmentTree(int[] arr) { build(arr, 0, arr.length - 1, 0); }

    private int findMinIndex(int[] arr, int qStart, int qEnd, int start, int end, int index) {
        /*
         * 4 cases of segment positions are considered below
         *
         * [qS, qE] | [qS, qE]    |    [qS, qE] |   [qS, qE]
         * [st, en] |    [st, en] | [st, en]    | [start,  end]
         */
        if(qStart <= start && qEnd >= end)
            return st[index];

        // [qS, qE] < [s, e] > [qS, qE]
        if(qStart > end || qEnd < start)
            return -1;

        int mid = start + (end - start) / 2;
        int left = findMinIndex(arr, qStart, qEnd, start, mid, 2 * index + 1);
        int right = findMinIndex(arr, qStart, qEnd, mid + 1, end, 2 * index + 2);

        // Note: we are returning the index since we store the indexes of the array elements in the segment tree
        // not the element themselves.
        if(left == -1)
            return right;
        else if(right == -1)
            return left;
        else
            return (arr[left] < arr[right]) ? left : right;
    }

    public int findMin(int[] arr, int start, int end) {
        int idx = findMinIndex(arr, start, end, 0, arr.length - 1, 0);
        return idx == -1 ? -1 : arr[idx];
    }
}
