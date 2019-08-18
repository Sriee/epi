package com.epi.list;

class Node {
    public int val;
    public Node prev = null;
    public Node next = null;
    
    public Node(int val) {
        this.val = val;
    }
}

/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 50 ms. Optimal solution
 * 
 * Double Linked List implementation with the following methods
 */
class MyDoubleLinkedList {

    private int count = 0;
    private Node head = null;
    private Node tail = null;
        
    /** Initialize your data structure here. */
    public MyDoubleLinkedList() {}
    
    /**
     * Get the value of the index-th node in the linked list.
     * @param index
     * @return node at index; -1 If the index is invalid
     */
    public int get(int index) {
        if(index + 1 > count || index < 0)
            return -1;
        
        Node current = this.head;
        
        for(int i = 0; i < index; i++)
            current = current.next;
        
        return current.val;
    }
    
    /**
     * Add a node of value val before the first element of the linked list. 
     * After the insertion, the new node will be the first node of the linked list.
     * 
     * @param val
     */
    public void addAtHead(int val) {
        Node newHead = new Node(val);
        if(this.count == 0) {
            this.head = this.tail = newHead;
        } else {
            newHead.next = this.head;
            this.head.prev = newHead;
            this.head = newHead;
        }
        
        this.count++;
    }
    
    
    /**
     * Append a node of value val to the last element of the linked list.
     * 
     * @param val
     */
    public void addAtTail(int val) {
        Node newTail = new Node(val);
        
        if(this.count == 0) {
            this.head = this.tail = newTail;
        } else {
            this.tail.next = newTail;
            newTail.prev = this.tail;
            this.tail = newTail;
        }
        
        this.count++;
    }
    
    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. 
     * If index is greater than the length, the node will not be inserted.
     * 
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if(index <= 0) {
            this.addAtHead(val);
            return;
        } else if(index == this.count) {
            this.addAtTail(val);
            return;
        } else if(index > this.count) {
            return;
        }
            
        int mid = this.count / 2;
        Node newNode = new Node(val), current = null;
        
        if(index > mid) {
            // Insert from the end
            current = this.tail;
            
            for(int i = this.count; i > index; i--)
                current = current.prev;    
        } else {
            // Insert from the start
            current = this.head;
        
            for(int i = 0; i < index - 1; i++)
                current = current.next;    
        }
        
        Node temp = current.next;
        current.next = newNode;
        newNode.prev = current;
            
        newNode.next = temp;
        temp.prev = newNode;
        this.count++;
    }
    
    /**
     * Delete the index-th node in the linked list, if the index is valid.
     * 
     * @param index
     */
    public void deleteAtIndex(int index) {
        if(index > this.count - 1 || index < 0)
            return;
        
        if(index == 0) {
            this.head = this.head.next;
            if(this.head != null)
                this.head.prev = null;
            this.count--;
            return;
        } else if(index == this.count - 1) {
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.count--;
            return;
        }
        
        int mid = this.count / 2;
        Node current = null;
        
        if(index > mid) {
            // Delete from the end
            current = this.tail;
            
            for(int i = this.count; i > index; i--)
                current = current.prev;
        } else {
            // Delete from the start
            current = this.head;
            for(int i = 0; i < index - 1; i++)
                current = current.next;
        }
        
        current.next = current.next.next;
        current.next.prev = current;
        this.count--;
    }
}
