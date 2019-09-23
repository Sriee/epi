package graph;

import java.util.List;

public class Node {
	
	public int val;
	public List<Integer> neighbors;
	
	public Node() {}
	
	public Node(int _val, List<Integer> _neighbors) {
		this.val = _val;
		this.neighbors = _neighbors;
	}
}
