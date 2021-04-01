package graph;

import java.util.*;

public class Graph {
    public List<Vertex> adjList;

    public Graph() {
        this.adjList = new LinkedList<>();
    }

    public void addVertex(Vertex vtx) {
        this.adjList.add(vtx);
    }

    public void addEdge(Vertex from, Vertex to) {
        from.addNeighbor(to);
    }
}
