package com.epi.list;

public class InsertionSort {

	/**
	 * Leet code Problem. Solution -> Accepted
	 * 
	 * @param head: Head of the list to be sorted
	 * @return sorted linked list
	 */
 	public <T extends Comparable<T>> ListNode<T> insertionSort(ListNode<T> head){
 		ListNode<T> newHead = new ListNode<>(), temp = head, toInsert = null, prev = null, iter = null;
 		boolean isInserted = false;

 		while(temp != null){
 			toInsert = temp;
 			temp = toInsert.next;
 			toInsert.next = null;

 			isInserted = false;
 			prev = newHead;
 			iter = newHead.next;

 			while(!isInserted){
 				if(iter == null){
 					prev.next = toInsert;
 					isInserted = true;
 				} else if(toInsert.data.compareTo(iter.data) <= 0){
 					prev.next = toInsert;
 					toInsert.next = iter;
 					isInserted = true;
 				} else {
 					prev = iter;
 					iter = iter.next;
 				}
 			}
 		}
 		return newHead.next;
 	}
 	
	public static void main(String[] args) {
		InsertionSort s = new InsertionSort();
		
		ListNode<Integer> toSort = new ListNode<>(Integer.MIN_VALUE);
		toSort.next = new ListNode<>(0);
		toSort.next.next = new ListNode<>(-41);
		toSort.next.next.next = new ListNode<>(Integer.MAX_VALUE);
		/*
		toSort.next.next.next = new ListNode<>(17);
		toSort.next.next.next.next = new ListNode<>(77);
		toSort.next.next.next.next.next = new ListNode<>(31);
		toSort.next.next.next.next.next.next = new ListNode<>(44);
		toSort.next.next.next.next.next.next.next = new ListNode<>(55);
		*/
		System.out.println("\nBefore Insertion Sort.");
		ListUtil.print(toSort);
		
		System.out.println("\nAfter Sorting.");
		ListNode<Integer> sorted = s.insertionSort(toSort);
		ListUtil.print(sorted);
	}

}
