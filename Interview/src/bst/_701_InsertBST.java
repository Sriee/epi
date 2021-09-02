package bst;

import java.util.*;

public class _701_InsertBST {

    public BSTNode constructBST(int[] nums) {
        BSTNode root = null;

        for (int i : nums) {
            root = insertIntoBST(root, i);
        }

        return root;
    }

    private BSTNode insertIntoBST(BSTNode root, int val) {
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
        int[] nums = {4, 2, 7, 1, 3};
        BSTNode root = ib.constructBST(nums);

        BinarySearchTree bst = new BinarySearchTree();
        List<BSTNode> lst = bst.getNodes(root);
        for (BSTNode node : lst)
            System.out.print(node.val + " ");
    }
}
