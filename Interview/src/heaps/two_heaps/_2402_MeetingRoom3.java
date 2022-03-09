package heaps.two_heaps;

import util.PrintHypens;
import java.util.*;

public class _2402_MeetingRoom3 {
    public int mostBooked(int rooms, int[][] meetings) {
        int[] counter = new int[rooms], roomInUse;
        int max = 0;

        Arrays.sort(meetings, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<int[]> used = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        // available & used [] = {room, end time}

        for (int i = 0; i < rooms; i++) {
            available.offer(new int[]{i, 0});
        }

        for (int[] meeting : meetings) {
            while (!used.isEmpty() && meeting[0] >= used.peek()[1]) {
                roomInUse = used.poll();
                roomInUse[1] = 0;
                available.offer(roomInUse);
            }

            if (available.isEmpty()) {
                roomInUse = used.poll();
                // Extend the end time
                meeting[1] = roomInUse[1] + meeting[1] - meeting[0];
                roomInUse[1] = 0;
                available.offer(roomInUse);
            }

            roomInUse = available.poll();
            roomInUse[1] = meeting[1];
            used.offer(roomInUse);

            // Update counter and max
            counter[roomInUse[0]]++;

            if (counter[roomInUse[0]] > max) max = counter[roomInUse[0]];
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
            int bookedRoom = mr.mostBooked(rooms[i], meetings[i]);
            System.out.println("\tRoom that held the most meetings: " + bookedRoom);
            System.out.println(PrintHypens.generate());
        }
    }
}
