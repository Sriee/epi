package com.parser;

/**
 * A Tree Node for Abstract Syntax Tree
 * @author sriee
 */
public class TreeNode {
	public String name;		// Node data	
	TreeNode left;			// Left child 
	TreeNode right;			// Right child
	
	public TreeNode() {}
	
	/**
	 * @param name Node data
	 */
	public TreeNode(String name) {
		this.name = name;
	}

    /**
	 * @param name Node data
	 * @param left	left child 
	 * @param right	right child
	 */
	public TreeNode(String name, TreeNode left, TreeNode right) {
		this.name = name;
		this.left = left;
		this.right = right;
	}

	@Override
    public String toString() {
	    return "TreeNode [name= " + this.name + ", left= " + ((this.left == null) ? "null" : this.left.name) +
                ", right= " + ((this.right == null) ? "null" : this.right.name) + "]";
    }
}
