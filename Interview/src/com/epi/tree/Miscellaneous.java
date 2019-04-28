package com.epi.tree;

public class Miscellaneous {
	int rootToLeafSum = 0;
	
	/**
	 * Current node of the tree
	 * 
	 * @param node current traversal node
	 * @param current the sum till traversed  
	 */
    public void rootToLeaf(TreeNode node, int current) {
        if(node == null)
            return;
        
        current = (current * 10) + node.val;
        
        if(node.left == node.right)
            this.rootToLeafSum += current;
        
        this.rootToLeaf(node.left, current);
        this.rootToLeaf(node.right, current);
    }
    
    /**
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path represent a number.
     * An example is the root-to-leaf path 1->2->3 which represents the number 123. 
     *
     * Compute the total sum of root-to-leaf path
     * 
     * Leet code. Solution -> accepted
     * 
     * @param root of the tree
     * @return sum of root to leaf members in a tree 
     */
    public int sumNumbers(TreeNode root) {
        this.rootToLeaf(root, 0);
        
        return this.rootToLeafSum;
    }
}
