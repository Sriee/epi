package tree;

import java.util.*;

public class _236_Lca {

    TreeNode parent = null;
    Map<TreeNode, Boolean> map;

    /**
     * Approach 2: Making use of counters.
     * <p>
     * This approach is more intuitive and can be altered to other versions of the problem.
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        map = new HashMap<>();
        map.put(p, true);
        map.put(q, true);

        lca(root, 2);
        return parent;
    }

    private int lca(TreeNode node, int totalNodes) {
        if (node == null)
            return 0;

        int left = lca(node.left, totalNodes);
        int right = lca(node.right, totalNodes);

        /* Won't we have 3?
         * We won't remember we are checking against the node. Since there can't
         * be any duplicates
         */
        int ans = left + right;

        if (map.containsKey(node))
            ans++;

        if (ans == totalNodes) {
            if (parent == null) parent = node;
        }

        return ans;
    }

    /**
     * Approach 1: This approach is more of an hack. The solution won't work if either a or b is
     * not present in the tree.
     * <p>
     * Note: We are comparing nodes (Object.equals) not the values
     */
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

    public static void main(String[] args) {
        _236_Lca lc = new _236_Lca();
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
        a = root.left.right; // 2
        b = a.right; // 4
        ancestor = lc.lowestCommonAncestor2(root, a, b);
        System.out.println(ancestor.val);
    }
}
