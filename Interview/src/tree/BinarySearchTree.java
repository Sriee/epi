package tree;

public class BinarySearchTree {

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

	public static void main(String[] args) {
	}

}
