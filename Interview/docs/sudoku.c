#include<stdio.h>
#include<stdlib.h>
#include<math.h>

int** init_2D(int r, int c){
	int **arr, i, j;
	
	// Memory allocate
	arr = (int **) malloc(r * sizeof(int *));
	for (i = 0; i < r; i++){
		arr[i] = (int *) malloc(c * sizeof(int));
	}

	// Init values
	for (i = 0; i < r; i++){
		for (j = 0; j < c; j++){
			arr[i][j] = 0;
		}
	}
	return arr;
}

int* init_1D(int length){
	int i;
	int *arr = (int *) malloc(length * sizeof(int));
	for(i = 0; i < length; i++){
		arr[i] = 0;
	}
	return arr;
}

void print_2D(int r, int c, int **arr){
	int i, j;
	for (i = 0; i < r; i++){
		for (j = 0; j < c; j++){
			printf("%d ", arr[i][j]);	
		}
		printf("\n");
	}
}

void print_1D(int length, int *arr){
	int j;
	for (j = 0; j < length; j++){
		printf("%d ", arr[j]);
	}
	printf("\n");
}

int check_1D(int length, int *arr){
	int i;
	for (i = 0; i < length; i++){
		if(arr[i] != 1) return 0;
	}
	return 1;
}

int check_2D(int r, int c, int **arr){
	int i, j;
	for (i = 0; i < r; i++){
		for (j = 0; j < c; j++){
			if(arr[i][j] != 1) return 0;
		}
	}
	return 1;
}

int main(){
	int i, j, m = 9, n = 9, failed = 0, si = 1, sj = 1;
	int sub_i = (int)sqrt(m), sub_j = (int)sqrt(n), *row_flag, **column_flag, **square_flag, arr[m][n]; 

	// Input
		// Row 0
	arr[0][0] = 5;
	arr[0][1] = 8;
	arr[0][2] = 1;
	arr[0][3] = 6;
	arr[0][4] = 7;
	arr[0][5] = 2;
	arr[0][6] = 4;
	arr[0][7] = 3;
	arr[0][8] = 9;

	// Row 1
	arr[1][0] = 7;
	arr[1][1] = 9;
	arr[1][2] = 2;
	arr[1][3] = 8;
	arr[1][4] = 4;
	arr[1][5] = 3;
	arr[1][6] = 6;
	arr[1][7] = 5;
	arr[1][8] = 1;

	// Row 3
	arr[2][0] = 3;
	arr[2][1] = 6;
	arr[2][2] = 4;
	arr[2][3] = 5;
	arr[2][4] = 9;
	arr[2][5] = 1;
	arr[2][6] = 7;
	arr[2][7] = 8;
	arr[2][8] = 2;

	// Row 4
	arr[3][0] = 4;
	arr[3][1] = 3;
	arr[3][2] = 8;
	arr[3][3] = 9;
	arr[3][4] = 5;
	arr[3][5] = 7;
	arr[3][6] = 2;
	arr[3][7] = 1;
	arr[3][8] = 6;

	// Row 4
	arr[4][0] = 2;
	arr[4][1] = 5;
	arr[4][2] = 6;
	arr[4][3] = 1;
	arr[4][4] = 8;
	arr[4][5] = 4;
	arr[4][6] = 9;
	arr[4][7] = 7;
	arr[4][8] = 3;

	// Row 5
	arr[5][0] = 1;
	arr[5][1] = 7;
	arr[5][2] = 9;
	arr[5][3] = 3;
	arr[5][4] = 2;
	arr[5][5] = 6;
	arr[5][6] = 8;
	arr[5][7] = 4;
	arr[5][8] = 5;

	// Row 6
	arr[6][0] = 8;
	arr[6][1] = 4;
	arr[6][2] = 5;
	arr[6][3] = 2;
	arr[6][4] = 1;
	arr[6][5] = 9;
	arr[6][6] = 3;
	arr[6][7] = 6;
	arr[6][8] = 7;

	// Row 7
	arr[7][0] = 9;
	arr[7][1] = 1;
	arr[7][2] = 3;
	arr[7][3] = 7;
	arr[7][4] = 6;
	arr[7][5] = 8;
	arr[7][6] = 5;
	arr[7][7] = 2;
	arr[7][8] = 4;

	// Row 8
	arr[8][0] = 6;
	arr[8][1] = 2;
	arr[8][2] = 7;
	arr[8][3] = 4;
	arr[8][4] = 3;
	arr[8][5] = 5;
	arr[8][6] = 1;
	arr[8][7] = 9;
	arr[8][8] = 8;


	// Row flag
	row_flag = init_1D(9);

	// Column flag
	column_flag = init_2D(n, 9);

	// Square flag
	square_flag = init_2D(sub_i * sub_j, 9);


	for (i = 0; i < m && !failed; i++){
		for (j = 0; j < n && !failed; j++){
			row_flag[arr[i][j] - 1] = 1;
			column_flag[j][arr[i][j] - 1] = 1;
			square_flag[si - 1][arr[i][j] - 1] = 1;
			
			if(i == (si * sub_i) - 1 && j == (sj * sub_j) - 1){
				if(check_1D(9, square_flag[si - 1])){
					sj++;
				} else {
					failed = 1;
					continue;
				}
			}

			if(sj == sub_j){
				si++;
				sj = 1;
			}
		}

		// Check row
		if(check_1D(9, row_flag)){
			free(row_flag);
			row_flag = init_1D(n);
		} else {
			failed = 1;
		}
	}

	// Check column 
	
	if(!failed){
		if(!check_2D(n, 9, column_flag)) failed = 1;
	}
	
	printf("%s\n", (failed) ? "Incorrect Sudoku" : "Correct Sudoku");

	// Free row flag
	free(row_flag);

	// Free column flag
	for (i = 0; i < m; i++){
		free(column_flag[i]);
	}
	free(column_flag);

	// Free square flag
	for (i = 0; i < sub_i; i++){
		free(square_flag[i]);
	}
	free(square_flag);

	return EXIT_SUCCESS;
}