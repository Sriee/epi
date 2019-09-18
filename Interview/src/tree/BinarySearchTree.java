package tree;

import list.ListNode;
import list.ListUtil;
import java.util.Stack;


public class BinarySearchTree {
	
	private ListNode<Integer> head = null;
	private int[] result = null;
	private int top = -1; 
	
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
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Above average run time. Iterative implementation 
	 * 
	 * Find kth smallest element in a Binary Search Tree
	 * 
	 * @param root of the BST
	 * @param k 
	 * @return kth smallest element
	 */
	public int kthSmallestIterative(TreeNode root, int k) {
		if(root == null)
			return 0;
		
		int[] result = new int[k];
		int top = -1;
		Stack<TreeNode> stack = new Stack<>();
		
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			
			TreeNode node = stack.pop();
			result[++top] = node.val;
			
			if(top == k - 1)
				break;
			root = node.right;
		}
		
		return result[k - 1];
	}
	
	/**
	 * In-order traversal of BST. Quits if we have visited k smallest elements
	 * 
	 * @param node
	 * @param k
	 */
	private void krecursive(TreeNode node, int k) {
		if(node == null)
			return;
		
		this.krecursive(node.left, k);
		
		if(this.top == k - 1)
			return;
		
		this.result[++this.top] = node.val;
		this.krecursive(node.right, k);
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 0 ms. Optimal run time. Recursive implementation
	 * 
	 * @param root of BST
	 * @param k
	 * @return kth smallest element in BST
	 */
	public int kthSmallest(TreeNode root, int k) {
		this.result = new int[k];
		this.krecursive(root, k);
		return this.result[k - 1];
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		TreeNode root = new TreeNode(3);
		TreeNode one = new TreeNode(1);
		one.right = new TreeNode(2);
		root.left = one;
		root.right = new TreeNode(4);
		
		System.out.println(bst.kthSmallest(root, 2));
	}
}
