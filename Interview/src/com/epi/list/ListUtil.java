package com.epi.list;

import java.util.ArrayList;
import java.util.PriorityQueue;

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
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Solutions that did not work
	 * 
	 * Trial 1 - Using a class called Pair to hold [data, index]. While linking to cursor need to create 
	 * 			 a new link node. Increases space complexity.
	 * Trial 2 - Wrote Priority queue comparator for int[] instead of a class
	 * Trial 3 - Store list[i]th element in priority queue and keep pointing the cursor to head of the queue
     *			 Storing all elements of the list in a queue gave a time limit exceeded exception. But the solution
     *			 worked
	 *  
	 * @param list Array of lists to be merged
	 * @return merged list
	 */
	public static <S extends Comparable<S>> ListNode<S> mergeKSortedList(ArrayList<ListNode<S>> list){
		ListNode<S> dummyHead = new ListNode<>(null, null);
		ListNode<S> cursor = dummyHead;
		int j = 1;
		PriorityQueue<ListNode<S>> queue = new PriorityQueue<>();
		for(int i = 0; i < list.size(); i++){
			ListNode<S> temp = list.get(i); 
			if(temp != null)
				queue.add(temp);
		}
		
		while(!queue.isEmpty()){
			System.out.println(j++ + " " + queue);
			cursor.next = queue.poll();
			cursor = cursor.next;

			if(cursor.next != null)
				queue.add(cursor.next);
		}
		return dummyHead.next;
	}
	
	/**
	 * Leetcode problem. Solution -> Accepted
	 */
	public static <S extends Comparable<S>> ListNode<S> reverseSublist(ListNode<S> head, int start, int finish){
		ListNode<S> dummy = new ListNode<>(null, head);
		ListNode<S> sublistHead = dummy;
		int k = 1;
		while(k++ < start){
			sublistHead = sublistHead.next;
		}
		ListNode<S> sublistIter = sublistHead.next, temp = null;
		
		while(start++ < finish){
			temp = sublistIter.next;
			sublistIter.next = temp.next;
			temp.next = sublistHead.next;
			sublistHead.next = temp;
		}
		
		return dummy.next;
	}
	
	/**
	 * Leetcode problem. Solution -> Accepted
	 */
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
		System.out.print("Reversed :");
		print(sub);
		
		ListNode<Integer> l2 = new ListNode<>(5);
		l2.next = new ListNode<>(6);
		l2.next.next = new ListNode<>(7);
			
		ListNode<Integer> l3 = new ListNode<>(3);
		l3.next = new ListNode<>(4);

		ListNode<Integer> l4 = new ListNode<>(0);
		l4.next = new ListNode<>(1);
		l4.next.next = new ListNode<>(2);
		
		ArrayList<ListNode<Integer>> list = new ArrayList<>();
		list.add(l2); list.add(l3); list.add(l4);
		
		ListNode<Integer> merged = mergeKSortedList(list);
		print(merged);

	}
}
