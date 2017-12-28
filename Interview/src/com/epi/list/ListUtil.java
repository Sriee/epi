package com.epi.list;

public class ListUtil {
	/**
	 * Leetcode problem. Solution -> Accepted
	 */
	public static <T extends Comparable<T>> ListNode<T> merge(ListNode<T> list1, ListNode<T> list2){
		ListNode<T> dummy = new ListNode<>(null, null);
		ListNode<T> c1 = list1, c2 = list2, current = dummy;
		while(c1 != null && c2 != null){
			if(c1.data.compareTo(c2.data) <= 0){
				current.next = c1;
				c1 = c1.next;
			} else {
				current.next = c2;
				c2 = c2.next;
			}
			current = current.next;
		}
		current.next = c1 != null ? c1 : c2;
		return dummy.next;
	}
	
	public static ListNode<Integer> reverseSublist(ListNode<Integer> head, int start, int finish){
		ListNode<Integer> dummy = new ListNode<>(0, head);
		ListNode<Integer> sublistHead = dummy;
		int k = 1;
		while(k++ < start){
			sublistHead = sublistHead.next;
		}
		ListNode<Integer> sublistIter = sublistHead.next;
		
		while(start++ < finish){
			ListNode<Integer> temp = sublistIter.next;
			sublistIter.next = temp.next;
			temp.next = sublistHead.next;
			sublistHead.next = temp;
		}
		
		return dummy.next;
	}
	
	public static <S extends Comparable<S>> ListNode<S> reverse(ListNode<S> head){
		ListNode<S> dummy = new ListNode<>(null, null);
		dummy.next = head;
		ListNode<S> iter = dummy.next, temp = null;
		while(iter.next != null){
			temp = iter.next;
			iter.next = temp.next;
			temp.next = dummy.next;
			dummy.next = temp;
		}
		return dummy.next;
	}
	
	/**
	 * Utility function to print the list. The list will be printed in the following format
	 * 
	 * 		a -> b -> c -> d -> e
	 *  
	 * @param head List head 
	 */
	public static <S extends Comparable<S>> void print(ListNode<S> head){
		if(head == null) return;
		ListNode<S> cursor = head;
		while(cursor.next != null){
			System.out.print(cursor.data + " -> ");
			cursor = cursor.next;
		}
		System.out.println(cursor.data);
	}
	
	/**
	 * Utility function to calculate the length of a Linked List
	 * @param head Linked List head
	 * @return length of the list
	 */
	public static <T extends Comparable<T>> int length(ListNode<T> head){
		ListNode<T> cursor = head;
		int count = 0;
		while(cursor != null){
			count++;
			cursor = cursor.next;
		}
		return count;
	}
	
	public static void main(String[] args) {
		ListNode<Integer> l1 = new ListNode<>(11);
		l1.next = new ListNode<>(3);
		l1.next.next = new ListNode<>(5);
		l1.next.next.next = new ListNode<>(7);
		l1.next.next.next.next = new ListNode<>(2);
		print(l1);
		
		ListNode<Integer> sub = reverse(l1);
		
		System.out.println();
		print(sub);
	}
}
