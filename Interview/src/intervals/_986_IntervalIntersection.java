package intervals;

import java.util.*;

public class _986_IntervalIntersection {

    /**
     * Approach 1: Merge sorted array
     *
     * We followed Merge Sorted array pattern to solve this problem. Since we are creating
     * new array for each interval the time taken by OA is more but the complexities are
     * similar to other merge interval problems.
     *
     * TC: O(m + n log n) - PQ insert = O(log n), We will be inserting m + n intervals to
     * the PQ.
     * SC: O(m + n + m + n) = O(m + n) - PQ + result
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0)
            return new int[][]{};

        List<int[]> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[2] == b[2] ? a[1] - b[1] : a[2] - b[2]);
        int i = 0, j = 0;

        pq.offer(new int[]{0, firstList[0][0], firstList[0][1]});
        pq.offer(new int[]{1, secondList[0][0], secondList[0][1]});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            //System.out.println(Arrays.toString(curr) + " " + Arrays.toString(pq.peek()));
            if (pq.peek() == null)
                continue;

            if (pq.peek()[1] <= curr[2]) {
                result.add(new int[]{
                        Math.max(curr[1], pq.peek()[1]),
                        Math.min(curr[2], pq.peek()[2])
                });
            }

            if (curr[0] == 0 && i + 1 < firstList.length) {
                i++;
                pq.offer(new int[]{0, firstList[i][0], firstList[i][1]});
            }

            if (curr[0] == 1 && j + 1 < secondList.length) {
                j++;
                pq.offer(new int[]{1, secondList[j][0], secondList[j][1]});
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * Approach 2: Two Pointer
     *
     * TC: O(n + m)
     * SC: O(m + n)
     */
    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0)
            return new int[][]{};

        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < firstList.length && j < secondList.length) {
            int[] a = firstList[i], b = secondList[j];
            int start = Math.max(a[0], b[0]);
            int end = Math.min(a[1], b[1]);

            if (start <= end)
                result.add(new int[] {start, end});

            // How do we know which two intervals to compare? By comparing their
            // end times
            if (a[1] < b[1])
                i++;
            else
                j++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        _986_IntervalIntersection ii = new _986_IntervalIntersection();
        int[][] first, second, result;

        // 1
        first = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        second = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        result = ii.intervalIntersection2(first, second);
        System.out.println(Arrays.deepToString(result));

        // 2
        second = new int[][]{{1, 5}, {8, 12}};
        result = ii.intervalIntersection(new int[][]{}, second);
        System.out.println(Arrays.deepToString(result));

        // 3
        first = new int[][]{{1, 5}, {8, 12}};
        result = ii.intervalIntersection2(first, new int[][]{});
        System.out.println(Arrays.deepToString(result));

        // 4
        first = new int[][]{{1, 7}};
        second = new int[][]{{3, 10}};
        result = ii.intervalIntersection(first, second);
        System.out.println(Arrays.deepToString(result));

        // 5
        first = new int[][]{{8, 15}};
        second = new int[][]{{2, 6}, {8, 10}, {12, 20}};
        result = ii.intervalIntersection2(first, second);
        System.out.println(Arrays.deepToString(result));
    }
}
