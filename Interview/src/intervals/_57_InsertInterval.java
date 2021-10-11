package intervals;

import java.util.*;

public class _57_InsertInterval {

    /*
     * TC: O(n)
     * SC: O(n)
     *
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0], end = newInterval[1];
        List<int[]> left = new ArrayList<>(), right = new ArrayList<>();

        for(int[] i : intervals) {
            if(i[1] < start)
                left.add(i);
            else if(i[0] > end)
                right.add(i);
            else {
                start = Math.min(start, i[0]);
                end = Math.max(end, i[1]);
            }
        }

        left.add(new int[] {start, end});
        left.addAll(right);

        return left.toArray(new int[left.size()][2]);
    }

    public static void main(String[] args) {
        _57_InsertInterval ii = new _57_InsertInterval();
        int[][] input = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] result = ii.insert(input, newInterval);
        System.out.println(Arrays.deepToString(result));
    }
}
