package heaps.two_heaps;

import util.PrintHypens;
import java.util.*;

public class _2402_MeetingRoom3 {

    private class MeetingRoom implements Comparable<MeetingRoom>{
        int roomNo, endTime;

        public MeetingRoom(int roomNo, int endTime) {
            this.roomNo = roomNo;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(MeetingRoom other) {
            return this.endTime == other.endTime ? this.roomNo - other.roomNo : this.endTime - other.endTime;
        }
    }

    /**
     * TC
     * ==
     * Sorting the meeting rooms based on start times => O(m log m) where m is the number of meeting rooms
     * We perform multiple push and pop operations for each room. Push/Pop in a heap = O(log n) where n is the number of
     * rooms.
     * We push and pop for each meeting => O(m log n)
     *
     * Total TC: O(m log m + m log n)
     *
     * SC
     * ==
     * Counter array = O(n)
     * Sorting the intervals = O(m)
     * Available & Used = In worst case, we can have n rooms in either heaps, So available heap size -> O(n) &
     * used heap size -> O(n)
     *
     * Total SC: O(m + n)
     */
    public int mostBooked(int rooms, int[][] meetings) {
        int[] counter = new int[rooms];
        int max = 0;
        Arrays.sort(meetings, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // Compare this version to the previous version where we use primitive int[]
        PriorityQueue<MeetingRoom> available = new PriorityQueue<>();
        PriorityQueue<MeetingRoom> used = new PriorityQueue<>();

        for (int i = 0; i < rooms; i++) {
            available.offer(new MeetingRoom(i, 0));
        }

        MeetingRoom room;
        for (int[] meeting : meetings) {
            while(!used.isEmpty() && meeting[0] >= used.peek().endTime) {
                room = used.poll();
                room.endTime = 0;
                available.offer(room);
            }

            if (available.isEmpty()) {
                room = used.poll();
                room.endTime += meeting[1] - meeting[0];
                available.offer(room);
            }

            room = available.poll();
            room.endTime = meeting[1];
            used.offer(room);

            counter[room.roomNo]++;
            max = Math.max(max, counter[room.roomNo]);
        }

        for (int i = 0; i < rooms; i++) {
            if (counter[i] == max) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        _2402_MeetingRoom3 mr = new _2402_MeetingRoom3();

        int[][][] meetings = {
                {{0, 10}, {1, 11}, {2, 12}, {3, 13}, {4, 14}, {5, 15}},
                {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}},
                {{1, 2}, {0, 10}, {2, 3}, {3, 4}},
                {{0, 2}, {1, 2}, {3, 4}, {2, 4}},
                {{1, 9}, {2, 8}, {3, 7}, {4, 6}, {5, 11}}
        };

        int[] rooms = {3, 3, 2, 4, 3};

        for (int i = 0; i < meetings.length; i++) {
            System.out.println((i + 1) + ".\tMeetings: " + Arrays.deepToString(meetings[i]));
            System.out.println("\tRooms: " + rooms[i]);
            System.out.println("\tRoom that held the most meetings: " + mr.mostBooked(rooms[i], meetings[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
