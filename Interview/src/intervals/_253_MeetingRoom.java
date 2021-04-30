package intervals;

import java.util.*;

public class _253_MeetingRoom {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length < 2)
            return intervals.length;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Map<Integer, Deque<int[]>> map = new HashMap<>();

        for (int[] ivl : intervals) {
            boolean ins = false;
            for (int i = 1; (i <= map.size()) && !ins; i++) {
                int[] curr = map.get(i).peekLast();

                if (curr[1] <= ivl[0]) {
                    map.get(i).offer(ivl);
                    ins = true;
                }
            }

            if (!ins) {
                map.put(map.size() + 1, new ArrayDeque<>());
                map.get(map.size()).offer(ivl);
            }
        }

        /*
         * Print Map
        for (int key : map.keySet()) {
            System.out.print(key + " ");

            for (int[] ivl : map.get(key))
                System.out.print(Arrays.toString(ivl) + " ");
            System.out.println();
        }
        */
        return map.size();
    }

    /**
     * Approach 2: Chronological Ordering
     * <p>
     * In this approach, we separate the start times and end times of the meetings
     * We add a new room if previous rooms are unavailable. How do we know this?
     * When starting time < ending time
     * We can re-use a previous room if we are starting a meeting after the previous one
     * has ended.
     * <p>
     * By separating the start and end times aren't we disturbing the idea of a meeting?
     * Yes, but for this approach we don't care about the specifics of a meeting. All
     * we need to know is whether we need a new room for the meeting or reuse an existing
     * one.
     */
    public int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        int rooms = 0, endItr = 0;

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        for (int st : start) {
            if (st < end[endItr])
                rooms++;
            else
                endItr++;
        }

        return rooms;
    }

    public static void main(String[] args) {
        _253_MeetingRoom mr = new _253_MeetingRoom();
        int[][][] input = {
                {{0, 30}, {5, 10}, {15, 20}},
                {{7, 10}, {2, 4}},
                {{13, 15}, {1, 13}}
        };

        for (int[][] intervals : input) {
            System.out.println(mr.minMeetingRooms(intervals) + " " + mr.minMeetingRooms2(intervals));
        }
    }
}
