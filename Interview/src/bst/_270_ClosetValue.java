package bst;

import tree.TreeNode;

/**
 * Should revisit this problem while covering Binary Search Tree
 */
public class _270_ClosetValue {

    int ans = -1;
    double diff = Double.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        if (root == null)
            return ans;

        search(root, target);
        return ans;
    }

    private void search(TreeNode node, double target) {
        if (node == null)
            return;

        double left = (double) node.val, right = target, ndiff;

        if (left < right) {
            left = target;
            right = node.val;
        }

        ndiff = left - right;

        if (ndiff < diff) {
            ans = node.val;
            diff = ndiff;
        }

        if (target > node.val)
            search(node.right, target);
        else
            search(node.left, target);
    }

}
