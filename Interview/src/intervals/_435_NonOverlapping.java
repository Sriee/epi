package intervals;

import java.util.Arrays;

public class _435_NonOverlapping {

    /**
     * Follows Merge Interval Pattern
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int eraseCount = 0;
        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            if (curr[0] < prev[1]) {
                eraseCount++;
                prev[1] = Math.min(prev[1], curr[1]);
            } else {
                prev = curr;
            }
        }
        return eraseCount;
    }

    public static void main(String[] args) {
        _435_NonOverlapping no = new _435_NonOverlapping();
        int[][][] input = {
                {
                        {1, 3}, {2, 6}, {8, 10}, {15, 18}
                },
                {
                        {15, 65}, {36, 98}, {4, 7}, {5, 7}, {68, 90}, {76, 125}, {637, 834}, {0, 3}, {90, 457},
                        {5, 14}, {536, 840}, {95, 256}
                },
                {
                        {7, 10}, {2, 4}
                },
                {
                        {0, 30}, {5, 10}, {15, 20}
                },
                {
                        {1, 2}, {2, 3}, {3, 4}, {1, 3}
                }
        };

        for (int[][] inp : input) {
            System.out.println(no.eraseOverlapIntervals(inp));
        }
    }
}
