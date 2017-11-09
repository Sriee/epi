package com.epi.list;

public class GroupList {

	private ListNode insertionSort(ListNode head){
		if(head == null) return null;
		ListNode toInsert = null, cursor = head;
		int len = this.length(head);
		while(len != 0){
			toInsert = cursor;
			cursor = cursor.next;
			head = this.insert(head, toInsert);
			System.out.print(len + " ");
			this.print(head);
			len--;
		}
		
		return head;
	}
	
	private int length(ListNode head){
		ListNode cursor = head;
		int count = 0;
		while(cursor != null){
			cursor = cursor.next;
			count++;
		}
		return count;
	}
	private ListNode insert(ListNode head, ListNode toInsert){
		ListNode prev = null, cursor = head;
		boolean inserted = false;
		while(cursor != null && !inserted){
			if(toInsert.val < cursor.val){
				if(prev == null){
					toInsert.next = cursor;
					head = toInsert;
					inserted = true;
					continue;
				} else {
					prev.next = toInsert;
					toInsert.next = cursor;
					inserted = true;
					continue;
				}
			}
			prev = cursor;
			cursor = cursor.next;
		}
		
		if(!inserted) prev.next = toInsert;
		return head;
	}
	
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
		
		System.out.println("\nInsertion Sort.");
		ListNode toSort = new ListNode(56);
		toSort.next = new ListNode(26);
		toSort.next.next = new ListNode(93);
		toSort.next.next.next = new ListNode(17);
		toSort.next.next.next.next = new ListNode(77);
		toSort.next.next.next.next.next = new ListNode(31);
		toSort.next.next.next.next.next.next = new ListNode(44);
		toSort.next.next.next.next.next.next.next = new ListNode(55);
		
		ListNode sorted = gl.insertionSort(toSort);
		gl.print(sorted);
	}

}
