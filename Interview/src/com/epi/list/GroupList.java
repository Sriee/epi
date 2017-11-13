package com.epi.list;

public class GroupList {
	
	private ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        
        ListNode first = head, second = head.next, temp = null, prev = null;
        while(first != null && second != null){
        	temp = second.next;
            first.next = temp;
            second.next = first;
            if(prev == null) {
            	head = second;
            } else {
            	prev.next = second;
            }
            prev = first;
            first = (first != null) ? first.next : null;
            second = (first != null) ? first.next : null;
        } 
        
        return head;
    }
	
	private ListNode groupOddEven(ListNode head){
		if(head == null) return null;
		ListNode odd = head, even = head.next, evenHead = even;
		while(even != null && even.next != null){
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
	
	private void print(ListNode head){
		if(head == null) return;
		ListNode cursor = head;
		while(cursor.next != null){
			System.out.print(cursor.val + " -> ");
			cursor = cursor.next;
		}
		System.out.println(cursor.val);
	}
	
	public static void main(String[] args) {
		GroupList gl = new GroupList();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		gl.print(head);
		ListNode gHead = gl.groupOddEven(head);
		gl.print(gHead);
		
		System.out.println("\nSwap Pairs");
		ListNode swapped = gl.swapPairs(head);
		gl.print(swapped);
	}

}
