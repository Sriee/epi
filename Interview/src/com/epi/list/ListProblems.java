package com.epi.list;

import java.util.*;


public class ListProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time 7 ms. Above average solution. The optimal solution uses boolean/integer array
	 * to keep track of elements present instead of Set which increases the run time.
	 * 
	 * Given an array which contains sub-set of the Linked List, find the number of connected
	 * components in the Linked List. The elemnts in the Linked List are unique.    
	 * 
	 * Example:
	 * 		0 -> 1 -> 2 -> 3 -> 4, [0, 4, 1, 2]
	 * 		2 components -> 0 -> 1 -> 2 & 4
	 * 
	 * @param head Head of a list node
	 * @param G subset 
	 * @return number of sub components
	 */
	private <T extends Comparable<T>> int numComponents(ListNode<T> head, int[] G) {
		int components = 0, run = 0;
		
		if(head == null || G.length == 0)
			return components;
		
		ListNode<T> current = head;
		Set<Integer> set = new HashSet<>();
		for(int i : G)
			set.add(i);
		
		while(current != null) {
			if(set.contains(current.data)) {
				run++;
			} else if(run > 0){
				run = 0;
				components++;
			}
			current = current.next;
		}
		
		if(run > 0)
			components++;
		
		return components;
	}
}