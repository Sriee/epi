package com.epi.tree;

import java.lang.Math;

public class TreeOperations {
    
	public boolean isSymmetric(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        
        if(p == null || q == null || p.val != q.val)
            return false;
        
        return this.isSymmetric(p.left, q.right) && this.isSymmetric(p.right, q.left);
	}	
	
	public boolean isSymmetric(TreeNode root) {
		if(root == null)
			return true;
		
		return this.isSymmetric(root.left, root.right);
	}
	
	public boolean isSame(TreeNode root) {
		if(root == null)
			return true;
		
		return this.isSame(root.left, root.right);
	}
	
	public boolean isSame(TreeNode p, TreeNode q) {
		if(p == null && q == null)
            return true;
        
        if(p == null || q == null || p.val != q.val)
            return false;
        
        return this.isSymmetric(p.left, q.left) && this.isSymmetric(p.right, q.right);
	}
	
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
	
	public boolean isBalanced(TreeNode root) {
		return this.checkBalanced(root).balanced;
	}
}
