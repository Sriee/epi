package com.epi.list;

public class InsertionSort {

	private <T extends Comparable<T>> ListNode<T> insertionSort(ListNode<T> head){
		if(head == null) return null;
		int pos = 0, len = this.length(head);
		ListNode<T> cursor = head;
		
		while(pos < len && cursor.next != null){
			if(cursor.data.compareTo(cursor.next.data) < 0){
				head = this.insertSortHelper(head, pos);
				cursor = head;
				pos = 0;
				continue;
			} 
			pos++;		
			cursor = cursor.next;
		}
		return head;
	}
	
	private <T extends Comparable<T>> ListNode<T> remove(ListNode<T> head, int pos){
		ListNode<T> prev = null, temp = null, cursor = head;
		while(pos != 0 && cursor != null){
			prev = cursor;
			cursor = cursor.next;
			pos--;
		}
		
		// Head is removed
		if(prev == null){
			temp = head;
			head = head.next;
		} else {
			temp = cursor;
			prev.next = cursor.next;
		}
		return temp;
	}
	
	private <T extends Comparable<T>> ListNode<T> insertSortHelper(ListNode<T> head, int pos){
		ListNode<T> prev = null, temp = null, cursor = head;
		while(pos != 0 && cursor != null){
			prev = cursor;
			cursor = cursor.next;
			pos--;
		}
		
		// Head is removed
		if(prev == null){
			temp = head;
			head = head.next;
		} else {
			temp = cursor;
			prev.next = cursor.next;
		}
		
		prev = null;
		cursor = head;
		temp.next = null;
		boolean inserted = false;
		while(cursor != null && !inserted){
			if(temp.data.compareTo(cursor.data) < 0){
				if(prev == null){
					temp.next = cursor;
					head = temp;
					inserted = true;
					continue;
				} else {
					prev.next = temp;
					temp.next = cursor;
					inserted = true;
					continue;
				}
			}
			prev = cursor;
			cursor = cursor.next;
		}
		
		if(!inserted) prev.next = temp;
		
		return head;
	}
	
	private <T extends Comparable<T>> int length(ListNode<T> head){
		ListNode<T> cursor = head;
		int count = 0;
		while(cursor != null){
			count++;
			cursor = cursor.next;
		}
		return count;
	}
	
	private <S extends Comparable<S>> void print(ListNode<S> head){
		if(head == null) return;
		ListNode<S> cursor = head;
		while(cursor.next != null){
			System.out.print(cursor.data + " -> ");
			cursor = cursor.next;
		}
		System.out.println(cursor.data);
	}
	
	private <T extends Comparable<T>> ListNode<T> insert(ListNode<T> head, ListNode<T> toInsert){
		ListNode<T> prev = null, cursor = head;
		boolean inserted = false;
		while(cursor != null && !inserted){
			if(toInsert.data.equals(cursor.data)){
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
	
	public static void main(String[] args) {
		InsertionSort s = new InsertionSort();
		
		ListNode<Integer> toSort = new ListNode<>(56);
		toSort.next = new ListNode<>(26);
		toSort.next.next = new ListNode<>(93);
		toSort.next.next.next = new ListNode<>(17);
		toSort.next.next.next.next = new ListNode<>(77);
		toSort.next.next.next.next.next = new ListNode<>(31);
		toSort.next.next.next.next.next.next = new ListNode<>(44);
		toSort.next.next.next.next.next.next.next = new ListNode<>(55);
		
		System.out.println("\nBefore Insertion Sort.");
		s.print(toSort);
		
		System.out.println("\nAfter Sorting.");
		ListNode<Integer> sorted = s.insertionSort(toSort);
		s.print(sorted);
		
		// Check insert & remove
		ListNode<Integer> ir = new ListNode<>(56);
		s.insert(ir, new ListNode<>(17));
		s.print(ir);
		
		s.remove(ir, 1);
		s.print(ir);
	}

}
