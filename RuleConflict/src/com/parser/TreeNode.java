package com.parser;


public class TreeNode {
	public String name;
	TreeNode left;
	TreeNode right;
	
	public TreeNode() {}
	public TreeNode(String name){ this.name = name; }
	
	public TreeNode(String name, TreeNode left, TreeNode right){
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
