package tree;

import java.util.*;


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
    
    /**
     * Leet code. Solution -> Accepted
     * 
     * Run Time: 5 ms. Below average solution. We take time to create a serialize
     * version of the tree before we do breath first search
     * 
     * Given the root & target nodes of the binary tree, find nodes that are k distance from the target
     * node. 
     * 
     * Approach:
     * 		1. Serialize the Binary Tree - Should find a way to reduce this time.
     * 		2. Do BFS
     * 
     * @param root of the tree
     * @param target node 
     * @param K distance
     * @return List of nodes that are k distance from the target node 
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        
        if(root == null || target == null)
            return result;
    
        if(K == 0) {
            result.add(target.val);
            return result;
        }
        
        Queue<Integer> populateQ = new LinkedList<>();
        Queue<int[]> searchQ = new LinkedList<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        
        /**
         * Why we use isVisited as map instead of an boolean array?
         * What would be the result of serialization for a skewed right tree? 
         */
        Map<Integer, Boolean> isVisited = new HashMap<>();
        
        int found = -1, left, right, parent;
        
        map.put(0, null);
        isVisited.put(0, true);
        
        map.put(1, root);
        isVisited.put(1, false);
        populateQ.add(1);
        
        while(!populateQ.isEmpty()) {
            int pos = populateQ.remove();
            TreeNode node = map.get(pos);
            
            if(node != null) {
                if(node.val == target.val)
                    found = pos;
                
                left = pos * 2;
                populateQ.add(left);
                map.put(left, node.left);
                isVisited.put(left, false);
                
                right = pos * 2 + 1;
                populateQ.add(right);
                map.put(right, node.right);
                isVisited.put(right, false);
            }
            
        }
        
        isVisited.put(found, true);
        searchQ.add(new int[] {found, 0});
        
        while(!searchQ.isEmpty()) {
            int[] current = searchQ.remove();
            TreeNode node = map.get(current[0]);
            isVisited.put(current[0], true);
            
            if(node != null) {
                if(current[1] == K) {
                    result.add(node.val);
                } else {
                    parent = current[0] / 2;
                    left = current[0] * 2;
                    right = current[0] * 2 + 1;
                    
                    if(!isVisited.get(parent))
                        searchQ.add(new int[] {parent, current[1] + 1});
                    
                    if(!isVisited.get(left))
                        searchQ.add(new int[] {left, current[1] + 1});
                
                    if(!isVisited.get(right))
                        searchQ.add(new int[] {right, current[1] + 1});    
                }
            }
        }
        
        return result;
    }

}
