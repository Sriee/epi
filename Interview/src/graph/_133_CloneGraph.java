package graph;

import java.util.*;

/**
 * Uses DFS Template
 */
public class _133_CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            Map<Integer, Node> map = new HashMap<>();
        }
    }

    Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        clone(node, new HashSet<>());
        return map.get(1);
    }

    private void clone(Node node, Set<Integer> visited) {
        Node newNode = new Node(node.val);
        map.put(node.val, newNode);
        visited.add(node.val);

        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor.val)) {
                clone(neighbor, visited);
            }

            newNode.neighbors.add(map.get(neighbor.val));
        }

    }
}
