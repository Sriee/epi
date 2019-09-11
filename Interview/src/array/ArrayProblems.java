package array;

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
	
	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();
		int res = ap.distanceBetweenBusStops(new int[] {1, 2, 3, 4},  0, 2);
		System.out.println(res);
		
		int[] arr = new int[] {1, 2, 3, 46, 79, 34, 6, 7, 23, 4, 6, 0, 9, 4, 37, 4, 5, 64, 9, 80, 50, 8, 6};
		res = ap.distanceBetweenBusStops(arr,  18, 2);
		System.out.println(res);
		
		System.out.println(arr.length);
	}

}
