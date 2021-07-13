package bst;

class _110_isBalanced {
    boolean balanced = true;

    public boolean isBalanced(BSTNode root) {
        height(root);
        return balanced;
    }

    private int height(BSTNode node) {
        if (node == null)
            return 0;

        // Find the height of left and right subtrees
        int left = height(node.left);
        int right = height(node.right);

        /* Calculate the balance factor. A tree is said to balanced only if the height of the left subtree
         * and the right subtree differ by atmost 1.
         */
        int bf = left - right;
        if (bf < -1 || bf > 1)
            balanced = false;

        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        _110_isBalanced isb = new _110_isBalanced();
        BSTNode root = new BSTNode(2);
        root.right = new BSTNode(3);
        System.out.println(isb.isBalanced(root));

        root.left = new BSTNode(1);
        System.out.println(isb.isBalanced(root));

        root.right.right = new BSTNode(4);
        root.right.right.right = new BSTNode(5);
        System.out.println(isb.isBalanced(root));
    }
}