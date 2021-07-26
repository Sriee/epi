package bst;

import java.util.Random;

class _99_RecoverTree {
    Random rand = new Random();
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

    private void morrisTraversal(BSTNode root) {
        BSTNode predecessor, prev = null;

        while (root != null) {
            if (root.left == null) {

                if (prev != null && root.val < prev.val) {
                    second = root;
                    if (first == null)
                        first = prev;
                }
                prev = root;
                root = root.right;
            } else {

                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    predecessor.right = null;

                    if (prev != null && root.val < prev.val) {
                        second = root;
                        if (first == null)
                            first = prev;
                    }
                    prev = root;
                    root = root.right;
                }
            }
        }

        // Swap
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void recoverTree(BSTNode root) {
        switch (rand.nextInt(2)) {
            case 0:
                System.out.println("Traditional In-order traversal");
                inorderApproach(root);
                break;
            case 1:
                System.out.println("Morris traversal");
                morrisTraversal(root);
                break;
        }
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