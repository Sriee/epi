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

	/**
	 * Helper utility to detect cycle in a directed graph. We do a Depth First Traversal
	 * on a vertex, if we reach a vertex which has already been visited 
	 * 
	 * @param v Vertex in a graph
	 * @return true if cycle is found false otherwise
	 */
	private boolean dagHasCycle(Vertex v, Set<Vertex> beingVisited) {
		/* Why do we need beingVisited set? Why can't we just rely on visited flag alone? 
		 * Note the location where we are setting the visited flag - Once we have visited all the
		 * neighbors for a vertex. If there is a loop, the stack will crash. 
		 * 
		 * Normally we would use a variable in vertex class itself such as:
		 * 		-1 - unvisited
		 * 		0 - being visited
		 * 		1 - visited
		 * 
		 * instead of an set. 
		 */
		beingVisited.add(v);
		
		for(Vertex neighbor : v.neighbors) {
			if(beingVisited.contains(neighbor))
				return true;
			else if(this.dagHasCycle(neighbor, beingVisited))
				return true;
		}
		
		beingVisited.remove(v);
		v.visited = true;
		return false;
	}
	
	/**
	 * Detect cycle in a directed graph
	 * 
	 * Why is there a wrapper ? Why can't we use the above method ?
	 * If the graph is disconnected, only calling hasCycle on a single vertex wouldn't work
	 * 
	 * @param g
	 * @return
	 */
	public boolean dagHasCycle(Graph g) {
		
		for(Vertex v : g.adjList) {
			if(!v.visited && this.dagHasCycle(v, new HashSet<>()))
				return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		GraphProblems gp = new GraphProblems();
		
		Vertex zero = new Vertex(0);
		Vertex one = new Vertex(1);
		Vertex two = new Vertex(2);
		Vertex three = new Vertex(3);
		
		zero.addNeighbor(one);
		zero.addNeighbor(two);
		
		one.addNeighbor(two);
		
		two.addNeighbor(three);
		two.addNeighbor(zero);
		
		three.addNeighbor(three);
		
		Graph g = new Graph();
		g.addVertex(zero);
		g.addVertex(one);
		g.addVertex(two);
		g.addVertex(three);
		
		System.out.println(gp.dagHasCycle(g));
		for(Vertex v : g.adjList)
			System.out.println(v);
	}

}
