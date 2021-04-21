package tree;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int value) {
        this.val = value;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
        this.val = x;
        this.left = left;
        this.right = right;
    }
}
