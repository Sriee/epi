package tree;

import list.ListNode;
import list.ListUtil;

public class BinarySearchTree {
	
	private ListNode<Integer> head = null;
	
	/**
	 * Helper method to insert elements to BST
	 * 
	 * @param nums given sorted integer array
	 * @param low
	 * @param high
	 * @return consturcted tree node
	 */
	private TreeNode helper(int[] nums, int low, int high) {
		if (low > high)
			return null;

		int mid = low + (high - low) / 2;
		TreeNode node = new TreeNode(nums[mid]);

		node.left = this.helper(nums, low, mid - 1);
		node.right = this.helper(nums, mid + 1, high);

		return node;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal solution
	 * 
	 * Given a sorted array create a height balanced binary search tree.
	 * 
	 * @param nums sorted integer array
	 * @return root of constructed binary search tree
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		return this.helper(nums, 0, nums.length - 1);
	}

	/**
	 * Helper method which simulates the in-order traversal
	 * 
	 * @param low
	 * @param high
	 * @return constructed node
	 */
	private TreeNode construct(int low, int high) {
		if (low > high)
			return null;

		int mid = low + (high - low) / 2;
		// This lets us to call left sub tree recursively
		// For the left most leaf left will become null
		TreeNode left = this.construct(low, mid - 1);

		TreeNode node = new TreeNode(this.head.data);
		this.head = this.head.next;	// Trick of moving the list pointer

		node.left = left;
		node.right = this.construct(mid + 1, high);

		return node;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Optimal solution
	 * 
	 * Given a linked list which is sorted create a height balanced binary search
	 * tree.
	 * 
	 * @param head sorted linked list
	 * @return root of constructed binary search tree
	 */
	public TreeNode sortedListToBST(ListNode<Integer> head) {
		int len = ListUtil.length(head);
		this.head = head;

		return this.construct(0, len - 1);
	}
	
	public static void main(String[] args) {
	}
}
