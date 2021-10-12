package intervals;

import java.util.*;

public class _759_EmployeeFreeTime {

    /**
     * Merge interval second template.
     *
     * TC: O(k log k)
     * SC: O(k)
     *      where k = mn; m intervals in n schedule.
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>(), intervals = new ArrayList<>();

        for (List<Interval> lst : schedule)
            intervals.addAll(lst);

        intervals.sort((a, b) -> a.start == b.start ? Integer.compare(a.end, b.end) : Integer.compare(a.start, b.start));
        Interval prev = null;

        for (Interval curr : intervals) {
            if (prev == null) {
                prev = curr;
            } else if (prev.end >= curr.start) {
                prev.end = Math.max(curr.end, prev.end);
            } else {
                res.add(new Interval(prev.end, curr.start));
                prev = curr;
            }
        }

        return res;
    }

    private List<List<Interval>> constructTestInput(int[][][] testCase) {
        List<List<Interval>> schedule = new ArrayList<>();

        for (int i = 0; i < testCase.length; i++) {
            List<Interval> empSchedule = new ArrayList<>();
            for (int j = 0; j < testCase[0].length; j++) {
                empSchedule.add(new Interval(testCase[i][j][0], testCase[i][j][1]));
            }
            schedule.add(empSchedule);
        }

        return schedule;
    }

    private void print(List<Interval> intervals) {
        for (Interval it : intervals)
            System.out.print(it + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        _759_EmployeeFreeTime eft = new _759_EmployeeFreeTime();
        List<List<Interval>> schedule;
        List<Interval> result;

        // 1 Exp: [6, 8]
        schedule = eft.constructTestInput(new int[][][]{
            {{3, 5}, {8, 10}},
            {{4, 6}, {9, 12}},
            {{5, 6}, {8, 10}}
        });
        result = eft.employeeFreeTime(schedule);
        eft.print(result);

        // 2 Exp: [2, 3] [4, 5] [6, 7] [8, 9] [10, 11]
        schedule = eft.constructTestInput(new int[][][] {
            {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}},
            {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}},
            {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}},
            {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}}
        });
        result = eft.employeeFreeTime(schedule);
        eft.print(result);
    }
}
