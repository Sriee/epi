package bst;

public class BSTNode {
    public int key, val;
    public BSTNode left, right;

    public BSTNode(int v) {
        this.key = -1;
        this.val = v;
    }

    public BSTNode(int k, int v) {
        this.key = k;
        this.val = v;
    }

    @Override
    public String toString() {
        return String.format("Node(%d = %d, [%s, %s])",
                this.key,
                this.val,
                this.left == null ? "null" : this.left.val,
                this.right == null ? "null" : this.right.val
        );
    }
}
