package intervals;

import java.util.*;

public class _759_EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<Interval>(), intervals = new ArrayList<>();

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
}
