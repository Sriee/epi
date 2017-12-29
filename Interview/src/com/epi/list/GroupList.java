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
	
	/**
	 * Leetcode problem. Solution -> Accepted
	 * 
	 * Deletes the duplicate nodes in a sorted linked list.
	 */
	private <R extends Comparable<R>> ListNode<R> deleteDuplicates(ListNode<R> head){
		ListNode<R> cursor = head;
		
		while(cursor.next != null){
			if(cursor.data == cursor.next.data){
				cursor.next = cursor.next.next;
			} else {
				cursor = cursor.next;
			}
		}
		return head;
	}

	/**
	 * Leetcode problem. Solution -> Accepted
	 * 
	 * Deletes all the duplicate nodes in a sorted linked list.
	 */
	private <R extends Comparable<R>> ListNode<R> deleteAllDuplicates(ListNode<R> head){
		ListNode<R> dummyHead = new ListNode<>(null, head);
		ListNode<R> previous = dummyHead, cursor = dummyHead.next, nextDistinct = null;
		
		while(cursor != null){
			boolean dirty = false;
			nextDistinct = cursor.next;
			while(nextDistinct != null && nextDistinct.data == cursor.data){
				dirty = true;
				nextDistinct = nextDistinct.next; 
			}
			
			if(dirty)
				previous.next = nextDistinct;
			else
				previous = cursor;
			
			cursor = nextDistinct;
		}
		return dummyHead.next;
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
		head.next.next = new ListNode<>(2);
		head.next.next.next = new ListNode<>(34);
		head.next.next.next.next = new ListNode<>(50);
		
		ListNode<Integer> headb = new ListNode<>(1);
		headb.next = new ListNode<>(2);
		headb.next.next = new ListNode<>(2);
		headb.next.next.next = new ListNode<>(34);
		headb.next.next.next.next = new ListNode<>(50);
		
		ListUtil.print(headb);

		ListNode<Integer> duplicatesRemoved = gl.deleteDuplicates(headb);
		System.out.println("\nDuplicates removed");
		ListUtil.print(duplicatesRemoved);
		
		ListNode<Integer> allDuplicatesRemoved = gl.deleteAllDuplicates(head);
		System.out.println("\nAll Duplicates removed");
		ListUtil.print(allDuplicatesRemoved);
		
		ListNode<Integer> gHead = gl.groupOddEven(head);
		System.out.println("\nGroup Odd Even");
		ListUtil.print(gHead);
		
		System.out.println("\nSwap Pairs");
		ListNode<Integer> swapped = gl.swapPairs(head);
		ListUtil.print(swapped);
	}
}
