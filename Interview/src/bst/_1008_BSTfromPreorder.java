package bst;

class _1008_BSTfromPreorder {

    /**
     * Since preorder traverses root, left, and right. Repeated call to insert to BST method gives us the BST.
     */
    public BSTNode bstFromPreorder(int[] preorder) {
        BSTNode root = null;

        for (int i : preorder)
            root = add(root, i);

        return root;
    }

    private BSTNode add(BSTNode node, int val) {
        if (node == null)
            return new BSTNode(val);

        if (val < node.val)
            node.left = add(node.left, val);
        else
            node.right = add(node.right, val);

        return node;
    }

    public static void main(String[] args) {
        _1008_BSTfromPreorder bfp = new _1008_BSTfromPreorder();
        int[] nums = {0};
        // 1
        BSTNode root = bfp.bstFromPreorder(nums);
        System.out.println(root);

        // 2
        nums = new int[]{8, 5, 1, 7, 10, 12};
        root = bfp.bstFromPreorder(nums);
        System.out.println(root);
    }
}