package tree;

/**
 * We got this problem in our 2019 Goldman Sachs Interview.
 */
public class _84_LargestHistogram {

    /* Segment Tree Vertex. We will be using arrays to store the values of a segment tree instead of creating a new
     * Java object for every segment.
    private class SegmentTreeNode{
        public int start, end, minIndex;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }
    }
    */

    private int findMinIndex(int[] heights, int[] st, int start, int end, int queryStart, int queryEnd, int index) {
        if(queryStart <= start && queryEnd >= end)
            return st[index];

        if(end < queryStart || start > queryEnd)
            return -1;

        int mid = start + (end - start) / 2;
        int l = findMinIndex(heights, st, start, mid, queryStart, queryEnd, 2 * index + 1);
        int r = findMinIndex(heights, st, mid + 1, end, queryStart, queryEnd, 2 * index + 2);

        if(l == -1)
            return r;
        else if(r == -1)
            return l;
        else
            return (heights[l] < heights[r]) ? l : r;
    }

    private int calculateMax(int[] heights, int[] st, int start, int end) {
        if(start > end)
            return -1;

        if(start == end)
            return heights[start];

        int minIndex = findMinIndex(heights, st, 0, heights.length - 1, start, end, 0);
        int minArea = heights[minIndex] * (end - start + 1);

        int leftArea = calculateMax(heights, st, start, minIndex - 1);
        int rightArea = calculateMax(heights, st, minIndex + 1, end);

        return Math.max(Math.max(leftArea, rightArea), minArea);
    }

    private int buildSegmentTree(int[] heights, int[] st, int start, int end, int index) {
        if(start > end)
            return -1;

        if(start == end) {
            st[index] = start;
        } else {
            int mid = (start + end) / 2;
            buildSegmentTree(heights, st, start, mid, 2 * index + 1);
            buildSegmentTree(heights, st, mid + 1, end, 2 * index + 2);

            int l = st[2 * index + 1];
            int r = st[2 * index + 2];

            st[index] = (heights[l] < heights[r]) ? l : r;
        }

        return st[index];
    }

    /**
     * Approach 1: Divide and Conquer approach using Segment Tree's
     */
    public int largestRectangleAreaSG(int[] heights) {
        int n = heights.length;

        // Initialize segment tree arr
        int[] st = new int[4 * heights.length];

        // Construct segment tree
        buildSegmentTree(heights, st, 0, n - 1, 0);

        // find largest rectangle area
        return calculateMax(heights, st, 0, n - 1);
    }

    /** ==========================================================================
     *  Stack Approach
     *  ==========================================================================
     */

    public static void main(String[] args) {
        _84_LargestHistogram lha = new _84_LargestHistogram();

        int[] arr;
        arr = new int[] {0,1,2,3,4,5,6,7,8};
        System.out.println(lha.largestRectangleAreaSG(arr));

        arr = new int[] {2,1,5,6,2,3};
        System.out.println(lha.largestRectangleAreaSG(arr));

        arr = new int[] {2, 4};
        System.out.println(lha.largestRectangleAreaSG(arr));
    }
}
