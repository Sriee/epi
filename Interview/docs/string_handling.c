#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>


/**  
 *  Find the index of the first unique character. Unique character is the one which is not
 *  not repeated in a string. 
 * 
 *  Assumes that the strings are all upper case. 
 */
int firstUnique(char *string){

	if(string == NULL){
		printf("Got NULL string as input.\n");
		exit(EXIT_FAILURE);
	}

	int i, idx = -1, len = strlen(string), found = 0;
	int arr[26];
	if(len == 0) return 0;

	// Initialize array
	for (i = 0; i < 26; ++i) 
		arr[i] = -1;
	
	for (i = 0; i < len; ++i){
		if(string[i] == ' ') continue;
		arr[(string[i] - '0') - ('A' - '0')] += 1;
	} 
	
	for (i = 0; (i < len) && (found == 0); ++i){
		if(string[i] != ' ' && arr[(string[i] - '0') - ('A' - '0')] == 0){
			idx = i;
			found = 1;
		}
	}

	return idx; 
}

/**
 *	Finds the most occuring character in a string
 *
 *	Assumes the characters are only lower case. Note that we are iterating through the 
 *  array and then finding the character. We are not returning the index of the most 
 *  occuring character in the string. 
 */
char mostOccuring(char *string){
	int arr[26];
	int i, len = strlen(string), max = INT_MIN, idx = -1;

	if(string == NULL){
		printf("Got NULL string as input.\n");
		exit(EXIT_FAILURE);
	}

	if(len == 0) return ' ';

	for (i = 0; i < 26; ++i)
		arr[i] = 0;

	for (i = 0; i < len; ++i){
		if(arr[i] == ' ') continue;
		
		arr[(string[i] - '0')-('a' - '0')] += 1;
	}

	for (i = 0; i < 26; ++i){
		if(arr[i] != 0 && arr[i] > max){
			idx = i;
			max = arr[i];
		}
	}

	return ('0' + (idx + ('a' - '0')));
}

int length(char* sentence){
	if(sentence == NULL)
		return 0;
	
	int count = 0;
	while(sentence[count++] != '\0');
	return (count > 0) ? count - 1 : count;
}

void reverse(char *begin, char *end){
	char temp;
	while(begin < end){
		temp = *begin;
		*begin++ = *end;
		*end-- = temp;
	}
}

void reverse_sentence(char* sentence){
	char *begin = sentence, *temp = sentence;
	while(*temp){
		*temp++;
		if(*temp == '\0'){
			reverse(begin, temp - 1);
		} else if(*temp == ' '){
			reverse(begin, temp - 1);
			begin = temp + 1;
		}
	}

	reverse(sentence, temp - 1);
}

/**
 * Converts string to integer. Assumes the number will be in range of int.
 * Return 0 for all invalid input
 */

int atoint(char* s){
	if(s == NULL) {
		printf("Invalid NULL input.\n");
		return 0;
	}
	int i = 0, len = strlen(s), literal, total = 0, isNeg = 0;
	if(len == 0) return 0;

	// Handling negative number
	if(*s == '-') {
		isNeg = 1;
		i++;
	}

	while(i < len){
		literal = s[i] - '0';

		if(literal > 9) {
			printf("Invalid input.\n");
			return 0;
		}

		total = (total * 10) + literal;
		i++;
	}

	if(isNeg) total = 0 - total;
	return total;
}	

/**
 * 1) Find first unique character in a string
 * 2) Find the character which is most occuring
 * 3) Reverse a sentence inplace
 * 4) Length of a string
 * 5) atoi
 */
int main(){
	char *name = "dafsdukjhj", *cap = "GAF I E J   FIGA";
	char ch = mostOccuring(name);
	int idx = firstUnique(cap);

	printf("Most occuring character is %c\n", ch);
	printf("First unique character is %c\n", cap[idx]);
	char* sentence = "a word my friend";
	printf("Reversed: %s\n", sentence);
	
	printf("%d\n", atoint("-a27"));
	return EXIT_SUCCESS;
}