package tree;

import java.util.*;

public class _1676_Lca4 {
    TreeNode parent = null;
    Set<TreeNode> set;

    /**
     * Variation of 236 LCA of BT
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null)
            return null;

        set = new HashSet<>();

        for (TreeNode node : nodes) {
            if (root == node)
                return root;
            set.add(node);
        }


        lca(root, nodes.length);
        return parent;
    }

    private int lca(TreeNode node, int totalNodes) {
        if (node == null)
            return 0;

        int left = lca(node.left, totalNodes);
        int right = lca(node.right, totalNodes);

        int ans = left + right;

        if (set.contains(node))
            ans++;

        if (ans == totalNodes) {
            if (parent == null) parent = node;
        }

        return ans;
    }

    public static void main(String[] args) {
        _1676_Lca4 lc = new _1676_Lca4();
        TreeUtils util = new TreeUtils();

        // Tree
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = util.construct(arr), ancestor, a, b;

        // TC 1
        a = root.right; // 1
        b = root.left.right; // 2
        ancestor = lc.lowestCommonAncestor(root, new TreeNode[]{a, b});
        System.out.println(ancestor.val);

        // TC 2
        lc.parent = null;
        a = root.left;
        ancestor = lc.lowestCommonAncestor(root, new TreeNode[]{a.left, a.right, a.right.left});
        System.out.println(ancestor.val);
    }
}
