package bst;

import java.util.*;

class _235_LCAofBST {
    Random rand = new Random();

    /**
     * Approach 1: Storing visited nodes by node p in a set and traverse q. If q has the same ancestor as p, we save
     * that as we progress.
     */
    public BSTNode storeAncestorLCA(BSTNode root, BSTNode p, BSTNode q) {
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

    /**
     * Approach 2: This solution is the same as finding the LCA for a Binary Tree. The Disadvantage of using this
     * approach is that, in the worst case, when p and q are in the rightmost subtree, we will be traversing the entire
     * left subtree. This approach does not utilize the Binary Search Tree property to find the LCA.
     */
    public BSTNode recursiveLCA(BSTNode root, BSTNode p, BSTNode q) {
        if (root == null)
            return null;

        if (root == p || root == q)
            return root;

        BSTNode left = recursiveLCA(root.left, p, q);
        BSTNode right = recursiveLCA(root.right, p, q);

        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;
    }

    /**
     * Approach 3: Optimal solution which uses BST Property to find the LCA.
     */
    public BSTNode usingBSTProperty(BSTNode root, BSTNode p, BSTNode q) {
        if (root == null)
            return null;

        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }

    public BSTNode lowestCommonAncestor(BSTNode root, BSTNode p, BSTNode q) {
        BSTNode res = null;
        switch (rand.nextInt(3)) {
            case 0:
                System.out.println("Store Ancestor");
                res = storeAncestorLCA(root, p, q);
                break;
            case 1:
                System.out.println("Recursive Approach");
                res = recursiveLCA(root, p, q);
                break;
            case 2:
                System.out.println("Using BST Property");
                res = usingBSTProperty(root, p, q);
                break;
        }

        return res;
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