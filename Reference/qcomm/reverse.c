#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void rev(char *s, int len);
void wrdrev(char *s, int len);
int word_occurance(char *sentence, int sentence_len, char *word, int word_len);

/* Reverse string function, reverse words function
 * Count the occurrence of every word
 */
int main(){
	char inp[] = "Hello", line[] = "Hi How are you";
	char *s = "This is a word wordly words iline", *w = "i";
	rev(inp, 5);
	wrdrev(line, 14);
	printf("%s\n%s\n%d\n", inp, line, word_occurance(s, strlen(s), w, strlen(w)));

	return EXIT_SUCCESS;
}

void rev(char *s, int len){
	int i = 0, j = len - 1;
	char tmp;
	
	while(i < j){
		tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;

		i++;
		j--;
	}
}

void wrdrev(char *s, int len){
	int i = 0, start = 0;
	for(i = 0; i < len; i++){
		if(s[i] == ' '){
			rev((s + start), i - start);
			start = i + 1;
		}
	}
	rev((s + start), i - start);
}

int word_occurance(char *sentence, int sentence_len, char *word, int word_len){
	int count = 0, i, j = 0, prev = 0;
	for(i = 0; i < sentence_len; i++){
		if(sentence[i] == word[j]){
			j++;
			continue;
		}

		if(j == word_len){
			j = 0;
			count += 1;
		} else {
			j = 0;
		}
	}
	return count;
}