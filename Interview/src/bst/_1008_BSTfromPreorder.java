package bst;

class _1008_BSTfromPreorder {

    /**
     * Since preorder traverses root, left, and right. Repeated call to insert to BST method gives us the BST.
     * <p>
     * The expected approach to construct BST from preorder traversal are as follows:
     * - Given preorder traversal = [8,5,1,7,10,12]
     * - We know that 8 will be the root node
     * TreeNode node = new TreeNode(preorder[start]);
     * <p>
     * - Next, we have to know the number of nodes that belong to the left subtree. Since this is a BST the right's
     * node val > current root's value.
     * int nextGreaterIdx = find(preorder, start + 1, preorder[start]); // 10
     * <p>
     * - In this case it's 10. So [5,1,7] should be in the left subtree of 8 and rest of the elements [10,12]
     * should be on the right subtree.
     * <p>
     * node.left = bstFromPreorderRecursive(preorder, start + 1, nextGreaterIdx - 1);
     * node.right = bstFromPreorderRecursive(preorder, nextGreaterIdx, end);
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