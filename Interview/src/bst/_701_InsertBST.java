package bst;

import java.util.*;

public class _701_InsertBST {
    public BSTNode insertIntoBST(BSTNode root, int val) {
        if (root == null)
            return new BSTNode(val);

        if (val < root.val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return root;
    }

    public static void main(String[] args) {
        _701_InsertBST ib = new _701_InsertBST();
        BSTNode root = null;

        int[] arr = {4, 2, 7, 1, 3};
        for (int i : arr) {
            root = ib.insertIntoBST(root, i);
        }

        BinarySearchTree bst = new BinarySearchTree();
        List<BSTNode> lst = bst.getNodes(root);
        for (BSTNode node : lst)
            System.out.print(node.val + " ");
    }
}
