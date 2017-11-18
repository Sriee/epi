#include<stdio.h>
#include<stdlib.h>

#define min(a, b) (a < b) ? a : b
#define swap(a, b) a = a + b; b = a - b; a = a - b;

float calculate_angle(int h, int m);

/* Find the degrees between the minute hand and hour hand when a clock is at 3:15 
 * Swap the values of two pointers without a temp variable 
 */
int main(){

	int a = 5, b = 10;
	int *ptr1 = &a, *ptr2 = &b;
	swap(*ptr1, *ptr2);
	printf("%d %d\n", *ptr1, *ptr2);
	printf("%.2f\n", calculate_angle(3, 10));
	printf("%.2f\n", calculate_angle(8, 15));
	printf("%.2f\n", calculate_angle(3, 55));

	return EXIT_SUCCESS;
}

float calculate_angle(int h, int m){
	float angle = -1, h_angle, m_angle;
	if(h < 0 || h > 12 || m < 0 || m > 60){
		printf("Invalid clock input.\n");
		return angle;
	}

	if(h == 12) h = 0;
	if(m == 60) m = 0;

	h_angle = 0.5 * (h * 60 + m);
	m_angle = 6 * m;

	angle = abs(h_angle - m_angle);
	
	angle = min(360 - angle, angle); 
	return angle;
}