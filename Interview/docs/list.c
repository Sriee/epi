#include<stdio.h>
#include<stdlib.h>
#include<limits.h>

static int size;

typedef struct node{
	int value;
	struct node* next;
}node;

typedef enum boolean{false, true} bool;

node* append(node* head, int value){
	if(head == NULL){
		head = (node *)malloc(sizeof(node));
		head->value = value;
		size++;
		return head;
	}

	node* cursor = head;
	while(cursor->next != NULL){
		cursor = cursor->next;
	}
	node* new_node = (node *)malloc(sizeof(node));
	new_node->value = value;
	cursor->next = new_node;
	size++;
	return new_node;
}

node* insert(node* head, int value, int position){
	int current = 0;
	node* cursor = head;
	
	if(position < 0 || position > size){
		printf("Invalid insert position\n");
		return head; 
	}

	if(head == NULL && position > 0){
		printf("Invalid insert position\n");
		return head; 
	}

	if(head == NULL){
		return append(head, value);
	}

	node* new_node = (node *) malloc(sizeof(node));
	new_node->value = value;

	if(position == 0){
		new_node->next = head;
		size++;
		return new_node;
	}

	while((current + 1) < position){
		cursor = cursor->next;
		current++;
	}

	
	node* temp = cursor->next;
	cursor->next = new_node;
	new_node->next = temp;
	size++;
	return head;
}

void print(node* head){
	if(head == NULL) {
		printf("Null\n");
		return;
	}
	node* cursor = head;
	printf("[ ");
	while(cursor != NULL){
		printf("%d ", cursor->value);
		cursor = cursor->next;
	}
	printf("]\n");
}

node* rem(node* head, int position){
	int current = 1;
	if(head == NULL || position < 0 || position > size){
		printf("Invalid insert position\n");
		return head; 
	}

	if(head == NULL && position > 0){
		printf("Invalid insert position\n");
		return head; 
	}

	if(position == 0){
		node* temp = head;
		free(temp);
		head = head->next;
		size--; 
		return head; 
	}

	node* cursor = head->next, *previous = head;
	while(current != position){
		previous = cursor;
		cursor = cursor->next;
		current++;
	}

	node* temp = (cursor == NULL) ? NULL : cursor->next; 
	free(cursor);
	previous->next = temp;

	size--;
	return head;
}

node* reverse(node* head){
	if(head == NULL) return head;

	node* cursor = head, *previous = NULL, *temp;
	while(cursor != NULL){
		temp = cursor->next;
		cursor->next = previous;
		previous = cursor;
		cursor = temp;
	}

	return previous; 
}

int getn(node* head, int n){
	if(head == NULL || n < 0 || n > size) return -1;
	
	node* cursor = head, *reference = head;
	int count = 0;

	while(count < n){
		reference = reference->next;
		count++;
	}


	while(reference != NULL){
		reference = reference->next;
		cursor = cursor->next;
	} 
	return cursor->value;
}

node* mid(node* head){
	node* slow = head, *fast = head;
	while(slow != NULL && fast != NULL && fast->next != NULL){
		slow = slow->next;
		fast = fast->next->next;
	}
	
	return slow;
}

void delete_node(node* anode){
	if(anode == NULL) return;

	node *temp;

	if(anode->next == NULL){
		anode = NULL;
	} else {
		anode->value = anode->next->value;
		temp = anode->next;
		anode->next = anode->next->next;
		free(temp);
	}
}

void delete(node* head){
	if(head == NULL) return;

	node *cursor = head, *temp;
	
	while(cursor != NULL){
		temp = cursor->next;
		free(cursor);
		cursor = temp;
	}
}

int length(){ return size; }

bool is_empty(){ return (size == 0) ? true : false; }

/* Linked List
 * 1. Create a custom malloc and free function using linked lists
 * 2. Delete a node in linked list
 * 3. Add a new node to a linked list
 * 4. Reverse a linked list
 * 5. Return the n element from end of a linked list
 * 6. Delete an element in list without the head node
 * 7. Find the midpoint in a linked list
 */
int main(){
	node* head = NULL, *temp;
	int i = 0, n;

	head = append(head, 5);
	head->next = append(head, 13);
	head->next->next = append(head, 12);
	print(head);
	
	head = insert(head, 49, 1);
	head = insert(head, -1, 0);
	head = insert(head, 88, length());
	print(head);

	temp = mid(head);
	printf("Mid node: %d\n", temp->value);

	head = rem(head, 0);
	head = rem(head, length());
	print(head);

	n = getn(head, 4);
	printf("%d\n", n);

	delete_node(head->next->next);
	print(head);

	temp = reverse(head);
	print(temp);

	delete(head);
	return EXIT_SUCCESS;
}