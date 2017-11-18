#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void print(int *arr, int len);

/* Find the first non-recurring character in a string. 
 * i.e. input "abbcdcaea" would return "d"
 */
int main(){
	char *s = "";

	if(s == NULL){
		printf("NULL\n");
		exit(EXIT_FAILURE);
	}

	if(strlen(s) == 0){
		printf("NULL\n");
		exit(EXIT_FAILURE);
	}
	
	int *occurance;
	int i, found = 0, length = strlen(s); 

	// Initialize occurance
	occurance = (int *) calloc(length, sizeof(int));	

	for(i = 0; i < length; i++){
		occurance[s[i] - 'a'] += 1;
	}

	print(occurance, length);
	
	i = 0;
	while(found == 0 && i < length){
		if(occurance[s[i] - 'a'] == 1){
			found = 1;
			continue;
		}

		i++; 
	}	
	
	if(i == length)
		printf("Not found\n");
	else
		printf("First non repetive character %c\n", s[i]);
	
	free(occurance); 
	return EXIT_SUCCESS;
}

void print(int *arr, int len){
	int i;
	for(i = 0; i < len; i++) printf("%d ", arr[i]);
	printf("\n");
}