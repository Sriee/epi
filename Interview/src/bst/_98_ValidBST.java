package bst;

import java.util.Random;

class _98_ValidBST {
    Random rand = new Random();

    /**
     * Approach 1: In-order traversal
     * <p>
     * Since in-order traversal of a BST yields a sorted array, we maintain a prev node reference.
     * If the previous value is >= current node's value then the value in the sorted array must be
     * out of place.
     */
    BSTNode prev = null;

    public boolean inorderValidateBST(BSTNode root) {
        if (root == null)
            return true;

        if (!inorderValidateBST(root.left))
            return false;

        if (prev != null && prev.val >= root.val)
            return false;

        prev = root;

        return inorderValidateBST(root.right);
    }

    public boolean isValidBST(BSTNode root) {
        boolean res = false;
        switch (rand.nextInt(3)) {
            case 0:
                System.out.println("In-order traversal");
                res = inorderValidateBST(root);
                break;
            case 1:
                System.out.println("Pre-order traversal");
                res = preorderValidateBST(root);
                break;
            case 2:
                System.out.println("Post-order traversal");
                res = postorderValidateBST(root);
                break;
        }

        return res;
    }

    /**
     * Approach 2: Pre-order traversal
     * <p>
     * We pass in a range (lower bound, upper bound) for each subtrees to check if the values lie
     * between this range. If a subtree's node falls outside this range, we can conclude that it's
     * not a valid BST.
     */
    public boolean preorderValidateBST(BSTNode root) {
        return areInRange(root, null, null);
    }

    private boolean areInRange(BSTNode node, Integer lower, Integer upper) {
        if (node == null)
            return true;

        if ((lower != null && node.val <= lower) || (upper != null && node.val >= upper))
            return false;

        return areInRange(node.left, lower, node.val) && areInRange(node.right, node.val, upper);
    }

    /**
     * Approach 3: Post-order traversal
     * <p>
     * Note: This approach increases the TC + It won't work for edge cases when the node's values are Integer.MIN_VALUE
     * or Integer.MAX_VALUE.
     * <p>
     * This approach is useful in problems like 333.Largest BST Subtree where repeated traversal will increase the
     * TC.
     * <p>
     * When asked to just validate whether a Tree is BST or not use the other two approaches outlined here.
     */
    public boolean postorderValidateBST(BSTNode root) {
        int[] res = validate(root);
        return !(res[0] == Integer.MIN_VALUE && res[1] == Integer.MAX_VALUE);
    }

    private int[] validate(BSTNode node) {
        if (node == null)
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

        int[] left = validate(node.left);
        int[] right = validate(node.right);

        if (left[1] < node.val && node.val < right[0]) {
            left[0] = Math.min(left[0], node.val);
            left[1] = Math.max(right[1], node.val);
        } else {
            left[0] = Integer.MIN_VALUE;
            left[1] = Integer.MAX_VALUE;
        }

        return left;
    }

    public static void main(String[] args) {
        _98_ValidBST vb = new _98_ValidBST();
        BSTNode root;

        // 1 [2, 1, 3]
        root = new BSTNode(2);
        root.left = new BSTNode(1);
        root.right = new BSTNode(3);

        System.out.println(vb.isValidBST(root));

        // 2 [5, 1, 4, null, null, 3, 6]
        root = new BSTNode(5);
        root.left = new BSTNode(1);
        root.right = new BSTNode(4);
        root.right.left = new BSTNode(3);
        root.right.right = new BSTNode(6);

        System.out.println(vb.isValidBST(root));
    }
}