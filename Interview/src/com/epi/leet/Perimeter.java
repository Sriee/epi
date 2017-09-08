package com.epi.leet;

public class Perimeter {

	private static void print(int [][]mat) {
		int row = mat.length, column = mat[0].length;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static int perimeter(int [][]mat) {
		int row = mat.length, column = mat[0].length, perimeter = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(mat[i][j] == 0) continue;
				
				perimeter += 4;
				// Left
				if(!(i - 1 < 0) && (mat[i - 1][j] == 1)) perimeter -= 1;
				
				// Right
				if((i + 1 < row) && (mat[i + 1][j] == 1)) perimeter -= 1;
				
				// Up
				if( !(j - 1 < 0) && (mat[i][j - 1] == 1)) perimeter -= 1;
				
				// Down
				if((j + 1 < column) && (mat[i][j + 1] == 1)) perimeter -= 1;
			}
		}
		return perimeter;
	}
	
	public static void main(String[] args) {
		int mat[][] = {{0 ,1, 0, 0},
				{1, 1, 1, 0},
				{0, 1, 0, 0},
				{1, 1, 0, 0}};
		
		print(mat);
		System.out.println(perimeter(mat));
	}

}
