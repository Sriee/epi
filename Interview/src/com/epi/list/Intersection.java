package com.epi.list;

public class Intersection {

	public ListNode<Integer> intersection(ListNode<Integer> l1, ListNode<Integer> l2){
		int len1 = ListUtil.length(l1), len2 = ListUtil.length(l2), diff = 0;
		System.out.println(len1 + " " + len2);
		if(len1 <= len2){
			diff = len2 - len1;
			System.out.println("diff: " + diff);
			return this.intersectionHelper(l1, l2, diff);
		} else {
			diff = len1 - len2;
			System.out.println("diff: " + diff);
			return this.intersectionHelper(l2, l1, diff);
		}
	}
	
	private <S extends Comparable<S>> ListNode<S> intersectionHelper(ListNode<S> first, ListNode<S> second, int diff){
		if(diff != 0){
			diff--;
			return intersectionHelper(first, second.next, diff);
		}
		
		if(first == second) return first;
		
		if(first.next == null && second.next == null) return null;
		
		return intersectionHelper(first.next, second.next, diff);
	}
	
	public static void main(String[] args) {
		Intersection it = new Intersection();
		
		ListNode<Integer> l2 = new ListNode<>(5);
		l2.next = new ListNode<>(3);
		l2.next.next = new ListNode<>(10);
		l2.next.next.next = new ListNode<>(1);
		l2.next.next.next.next = new ListNode<>(25);
		
		ListNode<Integer> l1 = new ListNode<>(78);
		l1.next = new ListNode<>(73);
		l1.next.next = new ListNode<>(8712);
		l1.next.next.next = new ListNode<>(1238);
		l1.next.next.next.next = new ListNode<>(6);
		l1.next.next.next.next.next = new ListNode<>(6);
		l1.next.next.next.next.next.next = new ListNode<>(-1);
		l1.next.next.next.next.next.next.next = l2.next.next.next; 
		
		ListUtil.print(l1);
		ListUtil.print(l2);
		ListNode<Integer> intersected = it.intersection(l2, l1);
		System.out.println(intersected);
	}
}
