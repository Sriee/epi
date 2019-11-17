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
	
	public static void main(String[] args) {
		final int MATRIX_SIZE = 2;	
		int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
		int number = 1;
		Matrix m = new Matrix();
		
		// Populate the matrix
		for(int i =0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				matrix[i][j] = number++;
			}
		}
		
		// Print matrix
		m.printMatrix(matrix);
	}
}
