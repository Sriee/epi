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

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 4 ms. Optimal Run Time.
	 * 
	 * Given a linked list, find the next largest element for each node.  
	 *  
	 * @param head of the Linked List
	 * @return array of next greater elements
	 */
	private <T extends Comparable<T>> int[] nextLargerNodes2(ListNode<T> head) {
		int len = 0, pos = -1;
		
		// Find the length of Linked List
		ListNode<T> current = head;
		while(current != null) {
			current = current.next;
			len++;
		}
		
		int[] res = new int[len];
		int[] stack = new int[len];
		int[] position = new int[len];
		int top = -1;
		current = head;
		
		while(current.next != null) {
			pos++;
			int val = (int) current.data;
			while(top > -1 && stack[top] < val) {
				res[position[top]] = val;
				top--;
			}
			top++;
			stack[top] = val;
			position[top] = pos;
			current = current.next;
		}
		
		while(top > -1) {
			if((int) current.data > stack[top])
				res[position[top]] = (int) current.data;
			top--;
		}
		return res;	
	}	
	
	private void copyRandomList(ListRandom head) {
		ListRandom node = head, nxt;
		
		while(node != null) {
			nxt = node.next;
			ListRandom duplicate = new ListRandom(node.val);
			
			node.next = duplicate;
			duplicate.next = nxt;
			node = nxt;
		}
		
		node = head;
		while(node != null) {
			if(node.random != null)
				node.next.random = node.random.next;
			node = node.next.next;
		}
		
		node = head;
		ListRandom copyHead = new ListRandom(-1);
		ListRandom copyNode = copyHead;
		
		while(node != null) {
			copyNode.next = node.next;
			node.next = node.next.next;
			node = node.next;
			copyNode = copyNode.next; 
		}
		
		ListRandom current = copyHead.next;
		
		while(current != null) {
			System.out.println(current.val + " " + ((current.random != null) ? current.random.val : 0));
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		ListProblems lp = new ListProblems();
		
		ListRandom head = new ListRandom(1);
		head.next = new ListRandom(2);
		head.next.next = new ListRandom(3);
//		head.next.next.next = new ListRandom(4);
		head.random = head.next.next;
//		head.next.random = head.next.next.next;
		
		lp.copyRandomList(head);
	}

}
