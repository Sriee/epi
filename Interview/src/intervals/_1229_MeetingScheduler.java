package intervals;

import java.util.*;

public class _1229_MeetingScheduler {

    /**
     * Merge interval template 2
     *
     * TC: O(m log m) + O(n log n)
     * SC: O(m) + O(n)
     *
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();

        Arrays.sort(slots1, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        int n = slots1.length, m = slots2.length, i = 0, j = 0;

        while (i < n && j < m) {
            int[] a = slots1[i], b = slots2[j];
            int start = Math.max(a[0], b[0]);
            int end = Math.min(a[1], b[1]);

            if (start <= end && end - start >= duration) {
                res.add(start);
                res.add(start + duration);
                break;
            }

            if (a[1] < b[1])
                i++;
            else
                j++;
        }

        return res;
    }

    public static void main(String[] args) {
        _1229_MeetingScheduler ms = new _1229_MeetingScheduler();
        int[][] slots1 = {{10,50},{60,120},{140,210}};
        int[][] slots2 = {{0,15},{60,70}};

        for (int d : new int[] {8, 12}) {
            List<Integer> lst = ms.minAvailableDuration(slots1, slots2, d);
            System.out.println(lst);
        }
    }
}
