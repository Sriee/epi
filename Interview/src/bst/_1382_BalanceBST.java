package bst;

import java.util.*;

class _1382_BalanceBST {

    /**
     * Naive Approach
     * 1. Do an inorder walk and store the sorted values in a list
     * 2. Construct BST from sorted array as in _108_ArrToBST.java
     */
    public BSTNode balanceBST(BSTNode root) {
        List<BSTNode> lst = new ArrayList<>();
        inorder(root, lst);
        return construct(lst, 0, lst.size() - 1);
    }

    private void inorder(BSTNode root, List<BSTNode> lst) {
        if (root == null)
            return;
        inorder(root.left, lst);
        lst.add(root);
        inorder(root.right, lst);
    }

    private BSTNode construct(List<BSTNode> lst, int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;
        BSTNode node = lst.get(mid);
        node.left = construct(lst, start, mid - 1);
        node.right = construct(lst, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        _1382_BalanceBST bb = new _1382_BalanceBST();
        BSTNode root = new BSTNode(1);
        root.right = new BSTNode(2);
        root.right.right = new BSTNode(3);
        root.right.right.right = new BSTNode(4);

        BSTNode balancedRoot = bb.balanceBST(root);
        System.out.println(balancedRoot);
    }
}