#ifndef TRAVERSAL_H
#define TRAVERSAL_H

// Tree Node
struct tree_node {
	int value;
	struct tree_node* left;
	struct tree_node* right;
};

// Stack Node
struct stack_node{
	struct tree_node* t;
	struct stack_node* next;
};

void push(struct stack_node** top, struct tree_node* t);
struct tree_node* pop(struct stack_node** top);
int is_empty(struct stack_node* top);
void print_stack(struct stack_node* top);

struct tree_node* new_tree_node(int value);
void inorder(struct tree_node* root);

#endif