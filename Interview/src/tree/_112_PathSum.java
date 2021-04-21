package tree;

public class _112_PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        return inOrder(root, targetSum, 0);
    }

    private boolean inOrder(TreeNode node, int target, int soFar) {
        if (node == null)
            return false;

        soFar += node.val;

        if (node.left == null && node.right == null && soFar == target)
            return true;

        return inOrder(node.left, target, soFar) || inOrder(node.right, target, soFar);
    }
}
