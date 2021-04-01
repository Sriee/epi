package tree;

import java.util.List;
import java.util.LinkedList;

public class NaryNode {

    public int val;
    public List<NaryNode> children;

    public NaryNode() {
    }

    public NaryNode(int val) {
        this.val = val;
        this.children = new LinkedList<>();
    }

    public NaryNode(int val, List<NaryNode> children) {
        this.val = val;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node(" + this.val + ", children: " + this.children.size() + ")";
    }
}
