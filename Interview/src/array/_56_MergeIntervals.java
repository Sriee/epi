package array;

import java.util.*;

public class _56_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;

        // Sort Intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        int i;
        int[] prev = intervals[0];
        for (i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];

            if (curr[0] > prev[1]) {
                res.add(new int[]{prev[0], prev[1]});
                prev = curr;
            } else {
                prev[1] = Math.max(prev[1], curr[1]);
            }
        }
        // Last Interval
        res.add(new int[]{prev[0], prev[1]});


        // Result
        int[][] merged = new int[res.size()][2];
        i = 0;

        for (int[] ivl : res) {
            merged[i][0] = ivl[0];
            merged[i][1] = ivl[1];
            i++;
        }

        return merged;
    }

    public static void main(String[] args) {
        _56_MergeIntervals mi = new _56_MergeIntervals();
        int[][] merged, input;
        input = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        merged = mi.merge(input);
        System.out.println(Arrays.deepToString(merged));
    }
}
