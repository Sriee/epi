package com.epi.list;

public class ListUtil {
	
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
	
	public static <S extends Comparable<S>> void print(ListNode<S> head){
		if(head == null) return;
		ListNode<S> cursor = head;
		while(cursor.next != null){
			System.out.print(cursor.data + " -> ");
			cursor = cursor.next;
		}
		System.out.println(cursor.data);
	}
	
	public static void main(String[] args) {
		ListNode<Integer> l1 = new ListNode<>(17);
		l1.next = new ListNode<>(26);
		l1.next.next = new ListNode<>(56);
		l1.next.next.next = new ListNode<>(93);
		
		ListNode<Integer> l2 = new ListNode<>(17);
		l2.next = new ListNode<>(26);
		l2.next.next = new ListNode<>(56);
		l2.next.next.next = new ListNode<>(93);
		
		ListNode<Integer> merged = merge(l1, l2);
		print(merged);
	}
}
