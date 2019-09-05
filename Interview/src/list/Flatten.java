package list;


public class Flatten {
	
	/**
	 * Recursive Helper method to flatten doubly linked list
	 * 
	 * @param head of the child pointer
	 * @return last non null node
	 */
	private <T> DoubleListNode<T> recurse(DoubleListNode<T> head) {
		DoubleListNode<T> childEnd = null, current = head;
		
		while(current != null) {
			if(current.child != null) {
				DoubleListNode<T> next = current.next;
				current.next = current.child;
				current.child.prev = current;
				
				DoubleListNode<T> end = this.recurse(current.child);
				end.next = next;
				
				if(next != null)
					next.prev = end;
				
				current = end;
			}
			childEnd = current;
			current = current.next;
		}
		return childEnd;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal Solution
	 * 
	 * Flatten a Doubly Linked List with child pointer.
	 * 
	 * Example:
	 * 		1 -> 2 -> 3 -> 4 -> 5 -> null
	 * 			 |
	 * 			 v
	 * 			 6 -> 7 -> 8 -> null
	 * 					   |
	 * 					   v
	 * 					   9 -> 10 -> 11 -> null
	 * 
	 * 	Returns: 1 -> 2 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 3 -> 4 -> 5 -> null
	 * 
	 * @param root root of the doubly linked list node
	 * @return head of flattened doubly
	 */
	private <T> DoubleListNode<T> flatten(DoubleListNode<T> head) {
		DoubleListNode<T> flattenHead = head;
		this.recurse(flattenHead);
		return flattenHead;
	}
	
	private <T> void print(DoubleListNode<T> root) {
		DoubleListNode<T> current = root;
		
		while(current != null) {
			System.out.println(((current.prev != null) ? current.prev.val : 0) + " " + current.val + " " + ((current.next != null) ? current.next.val : 0)
					);
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		Flatten fl = new Flatten();
		
		DoubleListNode<Integer> first = new DoubleListNode<>(1);
		DoubleListNode<Integer> second = new DoubleListNode<>(2); 
		DoubleListNode<Integer> third = new DoubleListNode<>(3);
		DoubleListNode<Integer> four = new DoubleListNode<>(4);
		DoubleListNode<Integer> five = new DoubleListNode<>(5);
		
		first.next = second;
		
		second.prev = first;
		second.next = third;
		
		third.prev = second;
		third.child = four;
		
		four.next = five;
		five.prev = four;
		
		DoubleListNode<Integer> flattened = first; 
		fl.flatten(flattened);
		fl.print(flattened);
	}
}
