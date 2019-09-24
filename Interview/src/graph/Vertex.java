package graph;

import java.util.List;
import java.util.LinkedList;

public class Vertex {
	
	public int val;
	public List<Vertex> neighbors;
	public boolean visited;
	
	public Vertex() {}
	
	public Vertex(int _val) {
		this(_val, new LinkedList<>(), false);
	}
	
	public Vertex(int _val, List<Vertex> _neighbors) {
		this(_val, _neighbors, false);
	}
	
	public Vertex(int _val, List<Vertex> _neighbors, boolean _isVisited) {
		this.val = _val;
		this.neighbors = _neighbors;
		this.visited = _isVisited;
	}
	
	public void addNeighbor(Vertex neighbor) {
		this.neighbors.add(neighbor);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vertex(");
		sb.append(this.val + ", " + this.visited + ", [");
		for(Vertex v : this.neighbors)
			sb.append(v.val + ", ");
		
		if(!sb.substring(sb.length() - 1).equals("["))
			sb.delete(sb.length() - 2, sb.length());
		
		sb.append("])");
		
		return sb.toString();
	}
}
