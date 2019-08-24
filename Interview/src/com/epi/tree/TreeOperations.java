package com.epi.tree;

import java.lang.Math;
import java.util.Queue;
import java.util.LinkedList;


public class TreeOperations {
    
    /**
	 * Given a Binary Tree check whether its subtree's are symmetric
	 * 
	 * Leet code. Solution -> Accepted
	 * 
	 * @param root Root of the tree
	 * @return true if the sub tree's are symmetric, false otherwise
	 */
    public boolean isSymmetric(TreeNode root) {
		if(root == null)
			return true;
		
		return this.isSymmetric(root.left, root.right);
	}

	/**
	 * Helper method for checking tree symmetricity
	 */
	public boolean isSymmetric(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        
        if(p == null || q == null || p.val != q.val)
            return false;
        
        return this.isSymmetric(p.left, q.right) && this.isSymmetric(p.right, q.left);
	}	
	
	/**
	 * Given a Binary Tree check whether the subtree's are same
	 *
	 * Leet code. Solution -> Accepted
	 *
	 * @param root Root of the tree
	 * @return true if the sub tree's are same, false otherwise
	 */ 
	public boolean isSame(TreeNode root) {
		if(root == null)
			return true;
		
		return this.isSame(root.left, root.right);
	}
	
	/**
	 * Helper method for checking if sub tree's are same
	 */
	public boolean isSame(TreeNode p, TreeNode q) {
		if(p == null && q == null)
            return true;
        
        if(p == null || q == null || p.val != q.val)
            return false;
        
        return this.isSymmetric(p.left, q.left) && this.isSymmetric(p.right, q.right);
	}
	
	/**
	 * Helper method to see if the tree is balanced
	 */ 
	public BalanceStatus checkBalanced(TreeNode node) {
		if(node == null)
			return new BalanceStatus(true, -1);
		
		// left subtree
		BalanceStatus leftChild = this.checkBalanced(node.left);
		if(!leftChild.balanced)
			return leftChild;	// At this stage we won't traverse through right sub tree
		
		// right subtree
		BalanceStatus rightChild = this.checkBalanced(node.right);
		if(!rightChild.balanced)
			return rightChild;
		
		return new BalanceStatus(Math.abs(leftChild.height - rightChild.height) <= 1, 1 + Math.max(leftChild.height, rightChild.height));
	}

	/**
	 * Given a Binary Tree check is it is Balanced. 
	 * 	 
	 * A Tree is balanced if the diff(leftSubtree.height - rightSubtree.height) <= 1.
	 * 
	 * Leet Code. Solution -> Accepted
	 * 
	 * Note: 
	 *	The fastest run time solution in leet code uses a global boolean variable to store the result. Also in that approach
	 *  you will be visiting O(n) nodes for both best & worst case scenarios, whereas in the following approach you will be
	 *  visiting O(h) for best case and O(n) for worst case scenario. This is because, while traversing if any of the subtree is not
	 *  balanced, you won't be traversing through the other subtree.
	 *
	 * @param root root of the tree
	 * @return true if the tree is balanced, false otherwise
	 */	
	public boolean isBalanced(TreeNode root) {
		return this.checkBalanced(root).balanced;
	}

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 14 ms. Below Average run time. Most run time is the use of variables  
	 * 
	 * Maximum Level Sum of Binary Tree
	 * 
	 * @param root of the binary tree
	 * @return maximum sum in a level, tie break is on level
	 */
	public int maxLevelSum(TreeNode root) {
		int level = 1, sum = 0, current = 1, next = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		int[] currentMax = new int[] {0, Integer.MIN_VALUE};
		
		while(!queue.isEmpty()) {
			TreeNode node = queue.remove();
			current--;
			
			if(node != null) {
				sum += node.val;
				queue.add(node.left);
				queue.add(node.right);
				
				next += 2;
			}
			if(current == 0) {
				if(sum > currentMax[1]) {
					currentMax[0] = level;
					currentMax[1] = sum;
				}
				current = next;
				next = 0;
				sum = 0;
				level++;
			}
		}
		
		return currentMax[0]; 
	} 
	
	/**
	 * Merge tree's
	 * 
	 * Conditions: 
	 *     - If there are nodes in the same position for both the tree's; the merged node should contain the sum value of both nodes 
	 *     - If a node is missing in one of the tree's the merged tree should contain the node which is present
	 *	
	 * Leet code. Solution -> Accepted
	 * 
	 * @param tree1 root node of first tree
	 * @param tree2 root node of second tree
	 * @return root node of merged tree
	 */
	public TreeNode merge(TreeNode tree1, TreeNode tree2) {
		if(tree1 == null && tree2 == null)
			return null;

		if(tree1 == null)
			return tree2;

		if(tree2 == null)
			return tree1;

		// Choosing tree1 to hold the result of the merged tree
		tree1.left = this.merge(tree1.left, tree2.left);
		tree1.right = this.merge(tree1.right, tree2.right);
		tree1.val += tree2.val;

		return tree1;  
	}

	/**
	 *  Helper function for path to sum caculation
	 *
	 * @param node current traversed node
	 * @param sum	sum to compare
	 * @param soFar	value up to so far traversed node
	 * @return	true if root to leaf path has the sum, false otherwise
	 */
	private boolean pathDFS(TreeNode node, int sum, int soFar) {
		if(node == null)
			return false;
		soFar += node.val;
		return node.left == node.right ? soFar == sum : this.pathDFS(node.left, sum, soFar) || this.pathDFS(node.right, sum, soFar);
	}

	/**
	 *	Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
	 *	values along the path equals the given sum.
	 *
	 *	Leet code. Solution -> accepted
	 *
	 * @param root root node of a tree
	 * @param sum root to leaf path sum to compare
	 * @return true if root to leaf path has the sum, false otherwise
	 */
	public boolean pathSum(TreeNode root, int sum) {
		if(root == null)
			return false;
		return this.pathDFS(root, sum, 0);
	}

	/**
	 * Helper method for sum of binary tree computation
	 *
	 * @param node current traversed node
	 * @param val sum up to current node
	 * @return sum of binary numbers from root to leaf in base 10
	 */
	private int dfs(TreeNode node, int val) {
		if(node == null)
			return 0;
		val = val * 2 + node.val;
		return node.left == node.right ? val : this.dfs(node.left, val) + this.dfs(node.right, val);
	}

	/**
	 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
	 * the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in
	 * binary, which is 13.
	 *
	 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
	 *
	 * Leet code. Solution -> accepted
	 * @param root root node of a tree
	 * @return the sum of these numbers
	 */
	public int sumOfBinaryTree(TreeNode root) {
		if(root == null)
			return 0;
		return this.dfs(root, 0);
	}
	
	/**
	 * Helper for inverting tree  
	 * 
	 * @param node Traversed node  
	 */
	public void invert(TreeNode node) {
		if(node == null)
			return;
		
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		this.invert(node.left);
		this.invert(node.right);
	}
	
	/**
	 * Invert a binary tree
	 * 
	 * Leet code. Solution -> Accepted
	 * 
	 * @param root root of a tree 
	 * @return root of inverted tree
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null)
			return null;
		this.invert(root);
		return root;
	}
	
	/**
	 * Helper method for find the sum of left leaves
	 * 
	 * @param node of a tree 
	 * @param isLeft true if the node a left child of parent false otherwise
	 * @return sum of the left leaves
	 */
	public int sum(TreeNode node, boolean isLeft) {
		if(node == null)
			return 0;
		if(node.left == node.right)
			return (isLeft) ? node.val : 0;
		
		return this.sum(node.left, true) + this.sum(node.right, false);
	}
	
	/**
	 * Find the sum of all the left leaves in a binary tree
	 * 
	 * Leet code. Solution -> Accepted
	 * @param root node of the tree
	 * @return sum of the left leaves 
	 */
	public int sumOfLeftLeaves(TreeNode root) {
		if(root == null)
			return 0;
		return this.sum(root.left, true) + this.sum(root.right, false);
	}

	/**
	 * Given a binary tree, flatten it to a linked list in-place.
	 * 
	 * Example: 
	 * 	   1
	 *    /
	 *   3
	 *  /
	 * 5  
	 * 
	 * should be flattened as
	 * 
	 * 1 
	 *  \
	 *   3
	 *    \
	 *     5
	 *     
	 * @param root of the binary tree
	 */
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        flatten(root.left);
        flatten(root.right);
        
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        
        TreeNode current = root;
        while(current.right != null)
            current = current.right;
        
        current.right = temp;
    }
    
    /**
     * Size of the tree
     * 
     * Leet code. Solution -> Accepted
     * 
     * @param root of the tree 
     * @return number of nodes in a tree
     */
    public long countNodes(TreeNode root) {
    	if(root == null)
    		return 0;
    	return 1 + this.countNodes(root.left) + this.countNodes(root.right);
    }
    
    /**
     * Given two non-empty binary trees s and t, check whether tree t has exactly
     * the same structure and node values with a subtree of s. A subtree of s is a 
     * tree consists of a node in s and all of this node's descendants. The tree s could 
     * also be considered as a subtree of itself.
     *  
     * @param s root of the tree 
     * @param t root of the subtree 
     * @return true if t is a subtree of s, false otherwise
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t == null)
            return true;
        
        if(s == null || t == null)
            return false;
        
        boolean res = false;
        if(s.val == t.val)
            res = this.isSame(s, t);
        
        return res || this.isSubtree(s.left, t) || this.isSubtree(s.right, t);
    }
}
