package tree;

import java.util.Stack;

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
        if (queryStart <= start && queryEnd >= end)
            return st[index];

        if (end < queryStart || start > queryEnd)
            return -1;

        int mid = start + (end - start) / 2;
        int l = findMinIndex(heights, st, start, mid, queryStart, queryEnd, 2 * index + 1);
        int r = findMinIndex(heights, st, mid + 1, end, queryStart, queryEnd, 2 * index + 2);

        if (l == -1)
            return r;
        else if (r == -1)
            return l;
        else
            return (heights[l] < heights[r]) ? l : r;
    }

    private int calculateMax(int[] heights, int[] st, int start, int end) {
        if (start > end)
            return -1;

        if (start == end)
            return heights[start];

        int minIndex = findMinIndex(heights, st, 0, heights.length - 1, start, end, 0);
        int minArea = heights[minIndex] * (end - start + 1);

        int leftArea = calculateMax(heights, st, start, minIndex - 1);
        int rightArea = calculateMax(heights, st, minIndex + 1, end);

        return Math.max(Math.max(leftArea, rightArea), minArea);
    }

    private void buildSegmentTree(int[] heights, int[] st, int start, int end, int index) {
        if (start > end)
            return;

        if (start == end) {
            st[index] = start;
        } else {
            int mid = (start + end) / 2;
            buildSegmentTree(heights, st, start, mid, 2 * index + 1);
            buildSegmentTree(heights, st, mid + 1, end, 2 * index + 2);

            int l = st[2 * index + 1];
            int r = st[2 * index + 2];

            st[index] = (heights[l] < heights[r]) ? l : r;
        }
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

    /*
     * ==========================================================================
     * Previous/Next Smallest Element Approach
     * ==========================================================================
     */

    /**
     * Calculates the next smallest element in an array.
     */
    private void nextSmallestElement(int[] arr, int[] nextSmallest, Stack<Integer> stack) {
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nextSmallest[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) nextSmallest[stack.pop()] = arr.length;
    }

    /**
     * Calculates the previous smallest element in an array.
     */
    private void previousSmallestElement(int[] arr, int[] previousSmallest, Stack<Integer> stack) {
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                previousSmallest[stack.pop()] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) previousSmallest[stack.pop()] = -1;
    }

    private int largestRectangleAreaPrevNxtSmallestElement(int[] heights) {
        int area = -1, n = heights.length;
        int[] nextSmallest = new int[n];
        int[] previousSmallest = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Next Smallest element in an array
        nextSmallestElement(heights, nextSmallest, stack);

        // Previous Smallest element in an array
        previousSmallestElement(heights, previousSmallest, stack);

        // Calculate largest area of histogram
        for (int i = 0; i < n; i++) {
            int width = nextSmallest[i] - previousSmallest[i] - 1;
            area = Math.max(area, heights[i] * width);
        }

        return area;
    }

    /*
     * ==========================================================================
     * Stack Approach
     * ==========================================================================
     */

    private int largestRectangleAreaStack(int[] heights) {
        int area = -1, n = heights.length, h, width;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                h = heights[stack.pop()];
                width = i - stack.peek() - 1;
                area = Math.max(area, h * width);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            h = heights[stack.pop()];
            width = n - stack.peek() - 1;
            area = Math.max(area, h * width);
        }

        return area;
    }

    public static void main(String[] args) {
        _84_LargestHistogram lha = new _84_LargestHistogram();

        int[][] arr = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8},
                {2, 1, 5, 6, 2, 3},
                {2, 4},
        };

        for (int[] heights : arr) {
            System.out.println("Stack Approach:            " + lha.largestRectangleAreaStack(heights));
            System.out.println("Segment Tree Approach:     " + lha.largestRectangleAreaSG(heights));
            System.out.println("Smallest Element Approach: " + lha.largestRectangleAreaPrevNxtSmallestElement(heights));
            System.out.println();
        }

    }
}
