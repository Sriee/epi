package com.epi.list;

public class GroupList {
	
	private <T extends Comparable<T>> ListNode<T> swapPairs(ListNode<T> head) {
        if(head == null) return null;
        
        ListNode<T> first = head, second = head.next, temp = null, prev = null;
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
	
	private <T extends Comparable<T>> ListNode<T> groupOddEven(ListNode<T> head){
		if(head == null) return null;
		ListNode<T> odd = head, even = head.next, evenHead = even;
		while(even != null && even.next != null){
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
	
	public static void main(String[] args) {
		GroupList gl = new GroupList();
		ListNode<Integer> head = new ListNode<>(1);
		head.next = new ListNode<>(2);
		head.next.next = new ListNode<>(3);
		head.next.next.next = new ListNode<>(4);
		head.next.next.next.next = new ListNode<>(5);
		ListUtil.print(head);
		ListNode<Integer> gHead = gl.groupOddEven(head);
		ListUtil.print(gHead);
		
		System.out.println("\nSwap Pairs");
		ListNode<Integer> swapped = gl.swapPairs(head);
		ListUtil.print(swapped);
	}

}
