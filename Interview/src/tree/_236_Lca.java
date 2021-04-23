package tree;

public class _236_Lca {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null)
            return null;

        if (root == a || root == b)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, a, b);
        TreeNode right = lowestCommonAncestor(root.right, a, b);

        if (left == null)
            return right;
        if (right == null)
            return left;

        return root;
    }
}
