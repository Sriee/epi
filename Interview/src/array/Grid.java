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
	public int[][] updateMatrix(int[][] matrix) {
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
	public int shortestPathBinaryMatrix(int[][] grid) {
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
	
	/**
	 * Leet code. Solution -> TLE
	 * 
	 * The solution gives correct answer but LC time constraints caused it to TLE.  
	 * 
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		
		int numIslands = 0, m = grid.length, n = grid[0].length;
		
		Queue<int[]> queue = new LinkedList<>();
		int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		boolean[] island;
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == '1') {
					island = new boolean[] {false, false, false, false}; 
					queue.add(new int[] {i, j});
					
					while(!queue.isEmpty()) {
						int[] current = queue.remove();
						grid[current[0]][current[1]] = 'v';
						
						for(int d = 0; d < 4; d++) {
							int x = directions[d][0] + current[0];
							int y = directions[d][1] + current[1];
							
							if(x < 0 || x >= m || y < 0 || y >= n) {
								// left
								if(x < 0)
									island[0] = true;
								
								// right
								if(x >= m)
									island[1] = true;
								
								// up
								if(y < 0)
									island[2] = true;

								// down
								if(y >= n)
									island[3] = true;
								
							} else if(grid[x][y] == '1') {
								queue.add(new int[] {x, y});
							} else if(grid[x][y] == '0') {// 0
								island[d] = true;
							}
						}
					}
					
					if(island[0] && island[1] && island[2] && island[3])
						numIslands++;
				}
			}
		}
		
		return numIslands;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal solution
	 * 
	 * Given a sudoku board, verify whether it is valid or not. Given board is valid and is always 9x9
	 * 
	 * @param board 9x9 sudoku board
	 * @return true if the board is valid false otherwise
	 */
	public boolean isValidSudoku(char[][] board) {
		boolean[][] rows = new boolean[10][10];
		boolean[][] cols = new boolean[10][10];
		boolean[][] subs = new boolean[10][10];
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == '.') continue;
					
				// Row check
				if(rows[i][board[i][j] - '0'])
					return false;
				else
					rows[i][board[i][j] - '0'] = true;
				
				// Column check
				if(cols[j][board[i][j] - '0'])
					return false;
				else
					cols[j][board[i][j] - '0'] = true;
				
				// Sub matrix check
				int p = (i - i % 3) + (j / 3);
				if(subs[p][board[i][j] - '0'])
					return false;
				else
					subs[p][board[i][j] - '0'] = true;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Grid g = new Grid();
		/*
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
		
		char[][] m = new char[][] {
			{'1', '1', '0', '1', '0'},
			{'1', '1', '0', '1', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '0', '0', '1'}
		};
		
		System.out.println(g.numIslands(m));
		*/
		
		char[][] b = new char[][] {
			{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
			{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
			{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
			{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
			{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
			{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
			{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
			{'.', '.', '.', '4', '2', '9', '.', '.', '5'},
			{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};
		
		System.out.println(g.isValidSudoku(b));
	}
}
