package array;

public class Matrix {
	
	public void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0ms. Optimal Solution
	 * 
	 * Give a matrix, rotate it by 90 degrees
	 * 
	 * @param matrix
	 */
	public void rotateMatrix(int[][] matrix) {
		int numLevels = matrix.length / 2, level = 0, end = matrix.length - 1, temp;
		
		while(level < numLevels) {
			for(int i = 0; i < end - level; i++) {
				// Clockwise cyclic replacement
				temp = matrix[end - i][level];
				matrix[end - i][level] = matrix[end][end - i];
				matrix[end][end - i] = matrix[level + i][end];
				matrix[level + i][end] = matrix[level][level + i];
				matrix[level][level + i] = temp;
			}
			level++; 
			end--;
		}
	}
	
	/**
	 * Transopse a matrix
	 * 
	 * @param matrix
	 */
	public void transpose(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = i; j < matrix.length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
	
	/**
	 * Flip matrix vertically. 
	 * 
	 * Similar to reversing an array using two pointer approach. 
	 * 
	 * @param matrix
	 */
	public void flipVertical(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			// We traverse till the middle
			for(int j = 0; j < matrix.length / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1];
				matrix[i][matrix.length - 1] = temp;
			}
		}
	}
	
	/**
	 * Flip matrix horizontally
	 * 
	 * @param matrix
	 */
	public void flipHorizontal(int[][] matrix) {
		for(int i = 0; i < matrix.length / 2; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[matrix.length - 1 - i][j];
				matrix[matrix.length - 1 - i][j] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		final int MATRIX_SIZE = 4;	
		int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
		int number = 1;
		Matrix m = new Matrix();
		
		// Populate the matrix
		for(int i = 0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				matrix[i][j] = number++;
			}
		}
		
		// Print matrix
		m.printMatrix(matrix);
		
		System.out.println("*******************************\n");
		// m.rotateMatrix(matrix);
		
		m.flipHorizontal(matrix);
		m.printMatrix(matrix);
		
		/*
		m.transpose(matrix);
		m.flipVertical(matrix);
		m.printMatrix(matrix);
		*/
	}
}
