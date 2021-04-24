package tree;

public class _1644_Lca2 {
    TreeNode parent = null;

    /**
     * This problem is similar to 236 LCA question with a caveat.
     * <p>
     * The solution is the same with one difference. We aren't using map or sending totalNodes
     * count. We didn't add them, just to illustrate how we can improve the solutions run time
     * on LC.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        return parent;
    }

    private int lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return 0;

        int left = lca(root.left, p, q);
        int right = lca(root.right, p, q);

        int ans = left + right;

        if (root == p || root == q)
            ans++;

        if (ans == 2) {
            if (parent == null) parent = root;
        }

        return ans;
    }

    public static void main(String[] args) {
        _1644_Lca2 lc = new _1644_Lca2();
        TreeUtils util = new TreeUtils();

        // Tree
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = util.construct(arr), ancestor, a, b;

        // TC 1
        a = root.right; // 1
        b = root.left.right; // 2
        ancestor = lc.lowestCommonAncestor(root, a, b);
        System.out.println(ancestor.val);

        // TC 2
        lc.parent = null;
        a = root.left.right; // 2
        b = new TreeNode(10); // Non-existent node
        ancestor = lc.lowestCommonAncestor(root, a, b);
        System.out.println(ancestor == null ? null : ancestor.val);
    }
}
