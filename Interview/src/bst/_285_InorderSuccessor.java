package bst;

import java.util.Random;

class _285_InorderSuccessor {
    Random rand = new Random();

    /**
     * Iterative Approach
     */
    public BSTNode inorderSuccessorIterative(BSTNode root, BSTNode p) {
        BSTNode iter = root, successor = null;

        while (iter != null) {
            if (iter.val > p.val) {
                successor = iter;
                iter = iter.left;
            } else {
                iter = iter.right;
            }
        }

        return successor;
    }

    /**
     * Recursive Approach
     */
    BSTNode successor = null;

    public BSTNode inorderSuccessorRecursive(BSTNode root, BSTNode p) {
        findSuccessor(root, p);
        return successor;
    }

    private void findSuccessor(BSTNode root, BSTNode p) {
        if (root == null)
            return;

        if (p.val < root.val) {
            successor = root;
            findSuccessor(root.left, p);
        } else {
            findSuccessor(root.right, p);
        }
    }

    public BSTNode inorderSuccessor(BSTNode root, BSTNode p) {
        BSTNode res = null;
        switch (rand.nextInt(2)) {
            case 0:
                System.out.println("Iterative Approach");
                res = inorderSuccessorIterative(root, p);
                break;
            case 1:
                System.out.println("Recursive Approach");
                res = inorderSuccessorRecursive(root, p);
                break;
        }

        return res;
    }

    public static void main(String[] args) {
        _285_InorderSuccessor is = new _285_InorderSuccessor();
        BSTNode root, res;

        // 1 [2, 1, 3]
        root = new BSTNode(2);
        root.left = new BSTNode(1);
        root.right = new BSTNode(3);
        res = is.inorderSuccessor(root, root.left);
        System.out.println(res.val);

        // 2 [5, 3, 6, 2, 4, null, null, 1]
        root = new BSTNode(5);
        root.left = new BSTNode(3);
        root.right = new BSTNode(6);
        root.left.left = new BSTNode(2);
        root.left.right = new BSTNode(4);
        root.left.left.left = new BSTNode(1);

        res = is.inorderSuccessor(root, root.right);
        System.out.println(res);

        res = is.inorderSuccessor(root, root.left.left);
        System.out.println(res.val);
    }
}