package graph;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public String getVal() { return Integer.toString(this.val); }
    public void addNeighbor(GraphNode node) {
        this.neighbors.add(node);
    }

    @Override
    public String toString() {
        return String.format("%s: [%s]", this.val, this.neighbors.stream()
                .map(GraphNode::getVal)
                .collect(Collectors.joining(", ")));
    }
}
