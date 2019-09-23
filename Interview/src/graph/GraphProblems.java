package graph;

import java.util.*;

public class GraphProblems {

	/**
	 * Leet code. Solution -> Wrong Answer
	 * 
	 * Could not find out the reason
	 * 
	 * Clone a graph
	 * 
	 * @param node 
	 * @return cloned graph head
	 */
	public Node cloneGraph(Node node) {
		if (node == null)
			return null;

		Set<Node> isCloned = new HashSet<>();
		Map<Node, Node> map = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();

		Node current = null, clonedCurrent = null, newNbr = null;
		queue.add(node);

		while (!queue.isEmpty()) {
			current = queue.remove();

			if (map.containsKey(current)) {
				clonedCurrent = map.get(current);
			} else {
				clonedCurrent = new Node(current.val, new LinkedList<>());
				map.put(current, clonedCurrent);
			}

			for (Node nbr : current.neighbors) {
				if (!isCloned.contains(nbr)) {

					if (map.containsKey(nbr)) {
						newNbr = map.get(nbr);
						newNbr.neighbors.add(clonedCurrent);
						clonedCurrent.neighbors.add(newNbr);
					} else {
						newNbr = new Node(nbr.val, new LinkedList<>());
						newNbr.neighbors.add(clonedCurrent);
						clonedCurrent.neighbors.add(newNbr);
						map.put(nbr, newNbr);
					}

					queue.add(nbr);
				}
			}

			isCloned.add(current);
		}
		/*
		 * System.out.println("Given"); for(Map.Entry<Node, Node> entry: map.entrySet())
		 * { current = entry.getKey();
		 * 
		 * System.out.print(current.val + " " + current.toString() +
		 * " is connected to ["); for(Node nbr : current.neighbors)
		 * System.out.print(nbr.val + " " + nbr.toString() + " ");
		 * 
		 * System.out.println("]"); }
		 * 
		 * System.out.println("\nCloned"); for(Map.Entry<Node, Node> entry:
		 * map.entrySet()) { current = entry.getValue();
		 * 
		 * System.out.print(current.val + " " + current.toString() +
		 * " is connected to ["); for(Node nbr : current.neighbors)
		 * System.out.print(nbr.val + " " + nbr.toString() + " ");
		 * 
		 * System.out.println("]"); }
		 */

		return map.get(node);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
