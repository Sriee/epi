package com.epi.tree;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
	
	/**
	 * Binary Tree Level Order Traversal
	 * 
	 * Leet code. Solution -> Accepted
	 * 
	 * @param root Root of the tree
	 * @return List of nodes for each level 
	 */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> innerList = new LinkedList<>();
        
        int current = 1, next = 0; 
        queue.add(root);
        while(!queue.isEmpty()){
            
            TreeNode node = queue.remove();
            current--;
            if(node != null){
                innerList.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
                next += 2;
            }
            
            if(current == 0){
                if(!innerList.isEmpty()){
                    result.add(innerList);
                }
                innerList = new LinkedList<>();
                current = next;
                next = 0;
            }
        }
        return result;
    }

	/**
	 * Binary Tree Zig Zag Level Order Traversal
	 * 
	 * Leet code. Solution -> Accepted
	 * 
	 * @param root Root of the tree
	 * @return List of nodes for each level (zig-zag order)
	 */
    public List<List<Integer>> zigZag(TreeNode root){
    		Queue<TreeNode> queue = new LinkedList<>();
    		List<List<Integer>> result = new LinkedList<>();
    		List<Integer> innerList = new LinkedList<>();
    		int current = 1, next = 0, dir = 0;
    		queue.add(root);
    		
    		while(!queue.isEmpty()) {
    			TreeNode node = queue.remove();
    			current--;
    			
    			if(node != null) {
    				if(dir % 2 == 0) {
    					innerList.add(node.val);
    				} else {
    					innerList.add(0, node.val);
    				}
    				queue.add(node.left);
    				queue.add(node.right);
    				next += 2;
    			}
    			
    			if(current == 0) {
    				if(!innerList.isEmpty()) {
    					result.add(innerList);
    					innerList = new LinkedList<>();
    				}
    				current = next;
    				next = 0;
    				dir++;
    			}
    		}
    		return result;
    }
    
	/**
	 * Binary Tree Level Order Traversal (Recursive implementation)
	 * 
	 * Leet code. Solution -> Accepted
	 * 
	 * Runtime: 1ms
	 * 
	 * @param root Root of the tree
	 * @return List of nodes for each level 
	 */
    private void levelTraversal(TreeNode root, int depth, List<List<Integer>> res) {
    		if(root == null){
    			return;
    		}
    		
    		if(res.size() == depth)
    			res.add(new LinkedList<>());
    		
    		res.get(depth).add(root.val);
    		levelTraversal(root.left, depth + 1, res);
    		levelTraversal(root.right, depth + 1, res);
    }
    
    public List<List<Integer>> levelOrderRecursive(TreeNode root){
    		List<List<Integer>> result = new LinkedList<>();
    		levelTraversal(root, 0, result);
    		return result;
    }
    
    private TreeNode construct(int[] values, int idx){
        if(idx >= values.length)
            return null;

        TreeNode node = new TreeNode(values[idx]);
        node.left = this.construct(values, 2 * idx + 1);
        node.right = this.construct(values, 2 *idx + 2);
        return node;
    }

    public void inOrder(TreeNode root){
        if(root == null) return;

        this.inOrder(root.left);
        System.out.print(root.val + " ");
        this.inOrder(root.right);
    }

    public void preOrder(TreeNode root){
        if(root == null) return;

        System.out.print(root.val  + " ");
        this.preOrder(root.left);
        this.preOrder(root.right);
    }

    public void postOrder(TreeNode root){
        if(root == null) return;
        this.postOrder(root.left);
        this.postOrder(root.right);
        System.out.print(root.val  + " ");
    }

    public List<Integer> preOrderIteratively(TreeNode root){
    		List<Integer> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
            
        int current = 1, next = 0; 
        queue.add(root);
        while(!queue.isEmpty()){
                
        		TreeNode node = queue.remove();
            current--;
            
            if(node != null){
            	    result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
                next += 2;
            }
                
            if(current == 0){
               current = next;
               next = 0;
            }
        }
    		return result;
    }
    
    public static void main(String[] args) {
        Traversal s = new Traversal();
        int[] values = new int[3];

        for(int i = 1; i < 4; i++){
            values[i - 1]= i;
        }

        System.out.println(Arrays.toString(values));
        TreeNode root = s.construct(values, 0);
        System.out.print("InOrder: ");
        s.preOrder(root);

    }
}
