#include<stdio.h>
#include<stdlib.h>

typedef enum boolean{false, true} bool; 

bool is_power2(long number){
	long i = 1;
	number = abs(number);
	while(i < number){
		i <<= 1;
		// printf("%ld\n", i); 	
	}
	return (i == number) ? true : false;
}

int main(){
	int *array, a[5];
	printf("%d\n", is_power2(2147483648));
	a[4] = 0;

	return EXIT_SUCCESS;
}