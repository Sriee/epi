package array;

import java.util.Arrays;
import java.util.Comparator;

public class _252_MeetingRoom {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length < 2)
            return true;

        // Sort Intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        _252_MeetingRoom mr = new _252_MeetingRoom();
        int[][][] input = {
                {{0, 30}, {5, 10}, {15, 20}},
                {{7, 10}, {2, 4}},
                {{13, 15}, {1, 13}}
        };

        for (int[][] intervals : input)
            System.out.println(mr.canAttendMeetings(intervals));
    }
}
