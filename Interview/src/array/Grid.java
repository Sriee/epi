package array;

import java.util.*;

public class Grid {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 16 ms. Above Average run time. Most of time is taken up by int
	 * array creation
	 * 
	 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
	 * The distance between two adjacent cells is 1.
	 * 
	 * Example:
	 * 	0, 0, 0  --> 0 0 0
	 *  0, 1, 0      0 1 0
	 *  1, 1, 1      1 2 1
	 *  
	 * @param matrix
	 * @return matrix with distance to zero for each cell
	 */
	private int[][] updateMatrix(int[][] matrix) {
		int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int m = matrix.length, n = matrix[0].length, i, j;
		Queue<int[]> queue = new LinkedList<>();
		
		for(i = 0; i < m; i++) {
			for(j = 0; j < n; j++) {
				if(matrix[i][j] == 0)
					queue.add(new int[] {i, j});
				else
					matrix[i][j] = Integer.MAX_VALUE;
			}
		}
		
		while(!queue.isEmpty()) {
			int[] current = queue.remove();
			
			for(int[] d : directions) {
				i = d[0] + current[0];
				j = d[1] + current[1];
				
				if(i < 0 || i >=m || j < 0 || j >= n || matrix[i][j] <= matrix[current[0]][current[1]] + 1)
					continue;
				
				queue.add(new int[] {i, j});
				matrix[i][j] = matrix[current[0]][current[1]] + 1;
			}
		}
		return matrix;
	} 
	
	public static void main(String[] args) {
		Grid g = new Grid();
		
		int[][] matrix = new int[][] {
			{0, 0, 0},
			{0, 1, 0},
			{1, 1, 1}
		};
		
		matrix = g.updateMatrix(matrix);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
