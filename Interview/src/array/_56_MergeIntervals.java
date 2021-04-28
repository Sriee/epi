package array;

import java.util.*;

/**
 * Merge Interval Pattern
 *
 * @see <a href=https://www.educative.io/courses/grokking-the-coding-interview>
 * Grokking the Coding Interview: Patterns for Coding Questions</a>
 * @see <a href=https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution>
 * LC Discuss: Merge Interval Pattern</a>
 */
public class _56_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;

        // Sort Interval
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();

        // This trick will avoid the problem of dealing with the last interval
        int[] prev = intervals[0];
        result.add(prev);

        for (int[] ivl : intervals) {
            if (ivl[0] > prev[1]) { // Disjoint Intervals: Add new interval to the list
                prev = ivl;
                result.add(prev);
            } else {    // Overlapping intervals: Extend the end of current interval
                prev[1] = Math.max(prev[1], ivl[1]);
            }
        }

        // Built-in method to convert List<int[]> to 2D array
        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String[] args) {
        _56_MergeIntervals mi = new _56_MergeIntervals();
        int[][] merged;
        int[][][] input = {
                {
                        {1, 3},
                        {2, 6},
                        {8, 10},
                        {15, 18}
                },
                {
                        {15, 65},
                        {36, 98},
                        {4, 7},
                        {5, 7},
                        {68, 90},
                        {76, 125},
                        {637, 834},
                        {0, 3},
                        {90, 457},
                        {6, 6},
                        {5, 14},
                        {536, 840},
                        {95, 256}
                }
        };

        for (int[][] inp : input) {
            merged = mi.merge(inp);
            System.out.println(Arrays.deepToString(merged));
        }
    }
}
