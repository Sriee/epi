#include<stdio.h>
#include<stdlib.h>
#include "traversal.h"


// Stack functions
void push(struct stack_node** top, struct tree_node* t){
	struct stack_node *new_node = (struct stack_node *) malloc(sizeof(struct stack_node));

	if(new_node == NULL){
		printf("Stack Overflow.\n");
		exit(EXIT_FAILURE);
	}

	new_node->t = t;
	new_node->next = (*top);
	(*top) = new_node;
}

struct tree_node* pop(struct stack_node** top){
	if(is_empty(*top)){
		printf("Stack Overflow.\n");
		exit(EXIT_FAILURE);
	}
	struct tree_node* temp = (*top)->t;
	*top = (*top)->next;
	return temp;
}


int is_empty(struct stack_node* top){
	return (top == NULL) ? 1 : 0;
}

void print_stack(struct stack_node* top){
	struct stack_node* current = top;
	while(current != NULL){
		printf("%d\n", current->t->value);
		current = current->next;
	}
}

// Tree Functions 
void inorder(struct tree_node* root){
	if(root == NULL){
		printf("Root is NULL.\n");
		return;
	}

	int done = 0;
	struct stack_node* top = NULL;
	struct tree_node* current = root;

	while(!done){
		if(current != NULL){
			push(&top, current);
			current = current->left;
		} else {
			if(!is_empty(top)){
				current = pop(&top);
				printf("%d ", current->value);

				current = current->right;
			} else {
				done = 1;
			}			
		}
	}
	printf("\n");
}

struct tree_node* new_tree_node(int value){
	struct tree_node* new_node = (struct tree_node*) malloc(sizeof(struct tree_node));
	new_node->value = value;
	new_node->left = NULL;
	new_node->right = NULL;
}

int main(){
	struct tree_node* root = new_tree_node(1);
	root->left = new_tree_node(2); 
	root->right = new_tree_node(3);
	root->left->left = new_tree_node(4);
	root->left->right = new_tree_node(5);

	inorder(root);
	
	return EXIT_SUCCESS;
}