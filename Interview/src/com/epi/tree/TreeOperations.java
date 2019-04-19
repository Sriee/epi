package com.epi.tree;

import java.lang.Math;

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
}
