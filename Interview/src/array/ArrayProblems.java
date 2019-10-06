package array;

import java.util.*;

public class ArrayProblems {

	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int clockwise = 0, anti = 0, n = distance.length;
		
		if(start > destination) {
			int temp = start;
			start = destination;
			destination = temp;
		}
		
		for(int i = start; i < destination; i++) {
			clockwise += distance[i];
		}
		
		for(int i = destination; i != start; i = (i + 1) % n) {
			anti += distance[i];
		}
		
		return (clockwise < anti) ? clockwise : anti;
	}
	
	public void trapWater() {
		int[] A = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < A.length){
            if (s.isEmpty() || A[i]<=A[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(A[s.peek()],A[i])-A[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        System.out.println(maxWater);
	}
	
	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		int res = ap.distanceBetweenBusStops(new int[] {1, 2, 3, 4},  0, 2);
		System.out.println(res);
		
		int[] arr = new int[] {1, 2, 3, 46, 79, 34, 6, 7, 23, 4, 6, 0, 9, 4, 37, 4, 5, 64, 9, 80, 50, 8, 6};
		res = ap.distanceBetweenBusStops(arr,  18, 2);
		System.out.println(res);
		
		ap.trapWater();
		
		List<Integer>[] key = new LinkedList[5];
	}

}
