package bst;

import java.util.Random;

class _99_RecoverTree {
    BSTNode first = null, second = null, pred = null;

    public void inorderApproach(BSTNode root) {
        inorder(root);

        // Swap
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(BSTNode node) {
        if (node == null)
            return;

        inorder(node.left);

        if (pred != null && node.val < pred.val) {
            second = node;
            if (first == null)
                first = pred;
            else
                return;
        }
        pred = node;

        inorder(node.right);
    }

    public void recoverTree(BSTNode root) {
        inorderApproach(root);
    }

    public static void main(String[] args) {
        _99_RecoverTree rt = new _99_RecoverTree();
        // [3,1,4,null,null,2]
        BSTNode root = new BSTNode(3);
        root.left = new BSTNode(1);
        root.right = new BSTNode(4);
        root.right.left = new BSTNode(2);

        rt.recoverTree(root);
        System.out.println(root);
    }
}