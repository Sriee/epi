package graph;

import java.util.List;

public class Node {
	
	public int val;
	public List<Node> neighbors;
	
	public Node() {}
	
	public Node(int _val, List<Node> _neighbors) {
		this.val = _val;
		this.neighbors = _neighbors;
	}
}
