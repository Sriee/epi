package array;

import java.util.*;


public class Intervals {

	static class Interval {
		public int start;
		public int end;
		
		public Interval(int a, int b) {
			start = a;
			end = b;
		}
		
		@Override
		public String toString() {
			return "Interval(" + this.start + ", " + this.end + ")"; 
		}
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 6 ms. Above average solution. 
	 * 
	 * Merge interval pattern. 
	 * 
	 * {@link educative.io -> Interview Patterns -> Merge Interval}
	 * @param intervals
	 * @return
	 */
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return intervals;
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0], end = intervals[0][1];
        List<int[]> results = new ArrayList<>();

        for(int i = 1; i < intervals.length; i++) {
            if(end < intervals[i][0]) {
                results.add(new int[] {start, end});
                
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        
        results.add(new int[] {start, end});
        int[][] res = new int[results.size()][2];
        
        for(int i = 0; i < results.size(); i++)
            res[i] = results.get(i);

        return res;    
    }
    
	public static void main(String[] args) {
		Interval one = new Interval(6, 7);
		Interval two = new Interval(2, 6);
		Interval three = new Interval(5, 9);
		List<Interval> list = new LinkedList<>(); 
		
		list.add(one);
		list.add(two);
		list.add(three);
		
		Collections.sort(list, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return (a.start != b.start) ? a.start - b.start: a.end - b.end;
			}
		});
		
		for(Interval i : list) 
			System.out.println(i);
		
		int[][] intervals = new int[][] {{8,10}, {15, 18}, {1, 3}, {2, 6}};
		// int[][] results = new int[intervals.length][intervals[0].length];
		List<int[]> result = new LinkedList<>();
		
		Arrays.sort(intervals, (a, b) -> (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);
		
		int start = intervals[0][0], end = intervals[0][1], j = 0;
		
		for(int i = 1; i < intervals.length; i++) {
			if(intervals[i][0] <= end) {
				end = Math.max(end, intervals[i][1]);
			} else {
				result.add(new int[] {start, end});
				j++;
				start = intervals[i][0];
				end = intervals[i][1];
			}
		}
		
		result.add(new int[] {start, end});
		
		System.out.println("Sorted intervals:");
		for(int[] i : intervals)
			System.out.println(Arrays.toString(i));
		
		System.out.println("\nAfter merging:");
		
		int[][] res = result.toArray(new int[result.size()][]); 
		for(int[] i : res)
			System.out.println(Arrays.toString(i));
	}

}
