package bst;

public class BSTNode {
    int val;
    BSTNode left, right;

    public BSTNode(int _val) {
        this.val = _val;
    }

    @Override
    public String toString() {
        return String.format("Node(%d, [%s, %s])",
                this.val,
                this.left == null ? "null" : this.left.val,
                this.right == null ? "null" : this.right.val
        );
    }
}
