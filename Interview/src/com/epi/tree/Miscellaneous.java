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
    
    /**
     * Helper for adding a row to a tree
     * 
     * @param node Current traversed node 
     * @param v Value for the new node
     * @param currentDepth depth of the traversed node
     * @param toAddDepth depth at which new row should be inserted
     */
    public void addRowHelper(TreeNode node, int v, int currentDepth, int toAddDepth) {
        if(node == null)
            return;
        
        if(currentDepth >= toAddDepth)
            return;
        
        TreeNode currentLeft = node.left;
        TreeNode currentRight = node.right;
        if(currentDepth == toAddDepth - 1) {    
            node.left = new TreeNode(v);
            node.right = new TreeNode(v);
            node.left.left = currentLeft;
            node.right.right = currentRight;
        } else {
            this.addRowHelper(currentLeft, v, currentDepth + 1, toAddDepth);
            this.addRowHelper(currentRight, v, currentDepth + 1, toAddDepth);   
        }
    }
    
    /**
     * Add one row to the tree
     * 
     * At 2 nodes at depth d. 
     * Left subtree of node at depth d = left subtree of node at depth d - 1. It's right will be null
     * Right subtree of node at depth d = right subtree of node at depth d - 1. It's left will be null 
     * 
     * If depth to insert is at 1. The new root node will be the one to be inserted. 
     * New root left subtree would be the old root. 
     * New root right subtree would be null
     *  
     * Leet code. Solution -> Accepted
     * 
     * @param root of the tree
     * @param v value for the new node
     * @param d	depth at which the node to be inserted
     * @return new root of the tree
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(root == null)
            return null;
        
        if(d == 1) {
            TreeNode temp = root;
            root = new TreeNode(v);
            root.left = temp;
        } else {
            this.addRowHelper(root, v, 1, d);
        } 
            
        return root;    
    }
}
