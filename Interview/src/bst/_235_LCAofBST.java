package bst;

import java.util.*;

class _235_LCAofBST {

    /**
     * Approach 1: Storing visited nodes by node p in a set and traverse q. If q has the same ancestor as p, we save
     * that as we progress.
     */
    public BSTNode lowestCommonAncestor(BSTNode root, BSTNode p, BSTNode q) {
        Set<BSTNode> seen = new HashSet<>();
        BSTNode iter = root, lca = null;
        while (iter != null) {
            seen.add(iter);
            if (p.val < iter.val)
                iter = iter.left;
            else if (p.val > iter.val)
                iter = iter.right;
            else
                break;
        }

        iter = root;
        while (iter != null) {
            if (seen.contains(iter))
                lca = iter;

            if (q.val < iter.val)
                iter = iter.left;
            else if (q.val > iter.val)
                iter = iter.right;
            else
                break;
        }

        return lca;
    }

    public static void main(String[] args) {
        _235_LCAofBST lb = new _235_LCAofBST();

        // [43,23,47,null,37,null,53,29,41,null,null,null,31]
        BSTNode root = new BSTNode(43);
        root.left = new BSTNode(23);
        root.right = new BSTNode(47);
        root.left.right = new BSTNode(37);
        root.right.right = new BSTNode(53);
        root.left.right.left = new BSTNode(29);
        root.left.right.right = new BSTNode(41);

        BSTNode lca = lb.lowestCommonAncestor(root, root.left, root.left.right);
        System.out.println(lca);
    }
}