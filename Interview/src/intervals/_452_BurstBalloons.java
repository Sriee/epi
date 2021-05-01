package intervals;

import java.util.Arrays;

public class _452_BurstBalloons {

    /**
     * Follows Merge Interval Pattern
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length < 2)
            return 1;

        // We are sorting the intervals based on the end time
        // We are using Integer comparison because it was causing overflow errors for some inputs
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1;
        int[] prev = points[0];

        for (int i = 1; i < points.length; i++) {
            int[] curr = points[i];

            if (curr[0] > prev[1]) {
                count++;
                prev = curr;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        _452_BurstBalloons bb = new _452_BurstBalloons();

        int[][][] input = {
                {{3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}},
                {{1, 2}, {4, 5}, {1, 5}},
                {{-2147483646, -2147483645}, {2147483646, 2147483647}},
                {{1, 2}},
                {{1, 2}, {3, 4}, {5, 6}, {7, 8}}
        };

        for (int[][] inp : input)
            System.out.println(bb.findMinArrowShots(inp));
    }
}
