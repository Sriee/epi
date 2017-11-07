#include <stdio.h>
#include <time.h>


int main (){

#ifdef HAVE_TIME_OF_DAY
	printf("Get time of day\n");
#else
	time_t sec;
    sec = time (NULL);
    printf ("Number of hours since January 1, 1970 is %ld \n", sec/3600);
#endif    
    printf("Hi\n");
    return 0;
}