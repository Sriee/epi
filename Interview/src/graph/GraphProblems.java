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
	public Vertex cloneGraph(Vertex node) {
		if (node == null)
			return null;

		Map<Vertex, Vertex> map = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();
		
		Vertex cloned = new Vertex(node.val, new LinkedList<>()), current = null;
		map.put(node, cloned);
		queue.add(node);

		while (!queue.isEmpty()) {
			current = queue.remove();
			for (Vertex nbr : current.neighbors) {
				
				if (!map.containsKey(nbr)) {
					map.put(nbr, new Vertex(nbr.val, new LinkedList<>()));
					queue.add(nbr);
				}
				
				map.get(current).neighbors.add(map.get(nbr));
				
			}
		}
		return cloned;
	}

	
	public static void main(String[] args) {
		Vertex v = new Vertex(1);
		v.addNeighbor(new Vertex(2));
		v.addNeighbor(new Vertex(3));
		System.out.println(v);
	}

}
