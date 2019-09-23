package graph;

import java.util.*;

public class GraphProblems {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 2 ms. Average run time. Best run time uses DFS
	 * 
	 * Clone a graph
	 * 
	 * @param node 
	 * @return cloned graph head
	 */
	public Node cloneGraph(Node node) {
		if (node == null)
			return null;

		Map<Node, Node> map = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		
		Node cloned = new Node(node.val, new LinkedList<>()), current = null;
		map.put(node, cloned);
		queue.add(node);

		while (!queue.isEmpty()) {
			current = queue.remove();
			for (Node nbr : current.neighbors) {
				
				if (!map.containsKey(nbr)) {
					map.put(nbr, new Node(nbr.val, new LinkedList<>()));
					queue.add(nbr);
				}
				
				map.get(current).neighbors.add(map.get(nbr));
				
			}
		}
		return cloned;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
