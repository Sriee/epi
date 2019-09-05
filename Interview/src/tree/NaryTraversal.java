package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTraversal {
	
	/**
	 * Depth First Search Recursive helper method
	 * 
	 * @param node of a n-ary tree 
	 * @param depth current depth
	 * @return maximum depth at ith node
	 */
    private int dfs(NaryNode node, int depth) {
        if(node == null)
            return depth;
        
        int curMax = -1;
        
        for(NaryNode child : node.children) {
            curMax = Math.max(curMax, this.dfs(child, depth + 1));
        }
        return (curMax == -1) ? depth : curMax; 
    }
    
    /**
     * Leet code. Solution -> Accepted
     * 
     * Depth of a n-ary tree.
     * 
     * @param root of the n-ary tree
     * @return maximum depth of the n-ary tree
     */
    public int depth(NaryNode root) {
        if(root == null)
            return 0;
        
        return this.dfs(root, 0);   
    }
    
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 3 ms. Above average run time.
	 * 
	 * Give the root of the n-ary node, retrieve the nodes at each level
	 * 
	 * @param root of the n-ary node
	 * @return list of node at each level of the n-ary tree
	 */
	public List<List<Integer>> levelOrder(NaryNode root) {
		Queue<NaryNode> queue = new LinkedList<>();
		List<List<Integer>> result = new LinkedList<>();
		List<Integer> level = new LinkedList<>();
		
		int current = 1, next = 0; 
		queue.add(root); 
		while(!queue.isEmpty()) {
			
			NaryNode node = queue.remove();
			current--;
			level.add(node.val);
			
			if(node != null) {
				next += node.children.size();
				queue.addAll(node.children);
			}
			
			if(current == 0) {
				result.add(level);
				current = next;
				next = 0;
				level = new LinkedList<>();
			}
		}
		
		return result;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Pre-order traversal of an n-ary tree. (Iterative Implementation)
	 * 
	 * @param root of the n-ary tree
	 * @return Pre-order traversal nodes
	 */
	public List<Integer> preOrder(NaryNode root) {
		List<Integer> result = new LinkedList<>();
		List<NaryNode> queue = new LinkedList<>();
		
		queue.add(root);
		
		while(!queue.isEmpty()) {
			NaryNode node = queue.remove(0);
			
			if(node != null) {
				result.add(node.val);
				node.children.addAll(queue);
				queue = node.children;
			}
		}
		
		return result;
	}
	
	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Post-order traversal of an n-ary tree. (Iterative Implementation)
	 * @param root of the n-ary tree
	 * @return Post-order traversal nodes
	 */
	public List<Integer> postOrder(NaryNode root) {
		Deque<NaryNode> queue = new LinkedList<>();
		List<Integer> res = new LinkedList<>();
		queue.add(root);		

		while(!queue.isEmpty()) {
			NaryNode node = queue.removeLast();
			if(node != null) {
				res.add(0, node.val);
				queue.addAll(node.children);
			}
		}
		
		return res;
	}
}
