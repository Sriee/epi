package com.epi.list;

public class ListSort {

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
 	
 	private <T extends Comparable<T>> ListNode<T> merge(ListNode<T> left, ListNode<T> right){
		if(left == null) return right;
		if(right == null) return left;

		ListNode<T> leftHead = new ListNode<>(null);
		leftHead.next = left;
		ListNode<T> prev = leftHead;

		while(left != null && right != null){
			if(left.data.compareTo(right.data) <= 0){
				prev = left;
				left = left.next;
			} else {
				prev.next = right;
				prev = prev.next;
				right = right.next;
				prev.next = left;
			}
		}

		if(right != null){
			prev.next = right;
		}
		
		return leftHead.next;
	}

 	
	public static void main(String[] args) {
		ListSort s = new ListSort();
		
		/*
		ListNode<Integer> toSort = new ListNode<>(Integer.MIN_VALUE);
		toSort.next = new ListNode<>(0);
		toSort.next.next = new ListNode<>(-41);
		toSort.next.next.next = new ListNode<>(Integer.MAX_VALUE);
		
		toSort.next.next.next = new ListNode<>(17);
		toSort.next.next.next.next = new ListNode<>(77);
		toSort.next.next.next.next.next = new ListNode<>(31);
		toSort.next.next.next.next.next.next = new ListNode<>(44);
		toSort.next.next.next.next.next.next.next = new ListNode<>(55);
		
		System.out.println("\nBefore Insertion Sort.");
		ListUtil.print(toSort);
		
		System.out.println("\nAfter Sorting.");
		ListNode<Integer> sorted = s.insertionSort(toSort);
		ListUtil.print(sorted);
		*/
		
		// Edge Cases
		ListNode<Integer> a = new ListNode<>(-1);
		ListNode<Integer> b = new ListNode<>(3);

		System.out.println("Edge cases.");
		ListUtil.print(s.merge(null, a));
		ListUtil.print(s.merge(b, null));
		ListUtil.print(s.merge(a, b));
		
		a.next = new ListNode<>(10);
		ListUtil.print(s.merge(a, b)); // -1 -> 3 -> 10
		
		// Correctness Test
		a = new ListNode<>(-1);
		a.next = new ListNode<>(10);
		a.next.next = new ListNode<>(27);
		a.next.next.next = new ListNode<>(783);
		
		b = new ListNode<>(Integer.MIN_VALUE);
		b.next = new ListNode<>(3);
		b.next.next = new ListNode<>(Integer.MAX_VALUE);
		
		System.out.println("Correctness cases.");
		ListUtil.print(a);
		ListUtil.print(b);
		
		ListUtil.print(s.merge(a, b)); // -21.. -> -1 -> 3 -> 10 -> 27 -> 783 -> 21..Ï
		
	}

}
