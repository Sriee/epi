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
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 34 ms. Above Average run time. Majority of the run time is because of
	 * using Stack collection.
	 * 
	 * Given a linked list, find the next largest element for each node.  
	 *  
	 * @param head of the Linked List
	 * @return array of next greater elements
	 */
	private <T extends Comparable<T>> int[] nextLargerNodes(ListNode<T> head) {
		int len = 0, pos = -1;
		
		// Find the length of Linked List
		ListNode<T> current = head;
		while(current != null) {
			current = current.next;
			len++;
		}
		
		int[] res = new int[len];
		Stack<int[]> stack = new Stack<>();
		current = head;
		
		while(current.next != null) {
			pos++;
			int val = (int) current.data;
			if(stack.isEmpty() || stack.peek()[1] >= val) {
				stack.push(new int[] {pos, val});
			} else {
				while(!stack.isEmpty() && stack.peek()[1] < val) {
					int[] top = stack.pop();
					res[top[0]] = val;
				}
				stack.push(new int[] {pos, val});
			} 
			current = current.next;
		}
		
		while(!stack.isEmpty()) {
			int[] top = stack.pop();
			if((int) current.data > top[1])
				res[top[0]] = (int) current.data;
		}
		return res;	
	}

}
