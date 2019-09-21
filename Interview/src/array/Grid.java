package array;

import java.util.*;

public class Grid {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 20 ms. Below Average run time. Note: Add integer instead of int[] is taking more time
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
		int m = matrix.length, n = matrix[0].length, i, j, ci, cj;
		Queue<Integer> queue = new LinkedList<>();
		
		for(i = 0; i < m; i++) {
			for(j = 0; j < n; j++) {
				if(matrix[i][j] == 0) {
					queue.add(i);
					queue.add(j);
				}
				else
					matrix[i][j] = Integer.MAX_VALUE;
			}
		}
		
		while(!queue.isEmpty()) {
			ci = queue.remove();
			cj = queue.remove();
			
			for(int[] d : directions) {
				i = d[0] + ci;
				j = d[1] + cj;
				
				if(i < 0 || i >=m || j < 0 || j >= n || matrix[i][j] <= matrix[ci][cj] + 1)
					continue;
				
				queue.add(i);
				queue.add(j);
				
				matrix[i][j] = matrix[ci][cj] + 1;
			}
		}
		return matrix;
	} 
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 20 ms. Above average run time.
	 * 
	 * Given a binary matrix find the shortest path from top-left to bottom right. Path is taken 
	 * via the cells which have 0 in them. Diagonal traversal of the cell is permitted. 
	 * 
	 * @param grid m x n matrix
	 * @return shortest distance from top-left to bottom right else - 1
	 */
	private int shortestPathBinaryMatrix(int[][] grid) {
		int m = grid.length, n = grid[0].length, i = 0, j = 0;
		
		if(grid[0][0] == 1 || grid[m - 1][n - 1] == 1)
			return -1;
		
		Queue<int[]> queue = new LinkedList<>();
		int[][] directions = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
		
		for(i = 0; i < m; i++) {
			for(j = 0; j < n; j++) {
				if(grid[i][j] == 1) {
					grid[i][j] = -1;
				}
			}
		}

		grid[0][0] = 1;
		queue.add(new int[] {0, 0});
		
		while(!queue.isEmpty()) {
			
			int[] current = queue.remove();
			
			for(int[] d : directions) {
				i = d[0] + current[0];
				j = d[1] + current[1];
				
				if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == -1) {
					continue;
				}
				
				if(grid[i][j] == 0 || grid[i][j] > grid[current[0]][current[1]] + 1) {
					grid[i][j] = grid[current[0]][current[1]] + 1;
					queue.add(new int[] {i, j});
				}
			}
		}
		
		return (grid[m - 1][n - 1] == 0) ? -1 : grid[m - 1][n - 1];
	}
	
	public static void main(String[] args) {
		Grid g = new Grid();
		
		int[][] matrix = new int[][] {
			{0, 0, 0, 0, 1, 0},
			{0, 1, 1, 0, 1, 0},
			{1, 0, 1, 1, 0, 1},
			{1, 0, 0, 1, 1, 0}
		};
		
		matrix = g.updateMatrix(matrix);
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(g.shortestPathBinaryMatrix(matrix));
	}
}
