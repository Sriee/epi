package tree;

class _1973_CountDependents {
    int count = 0;

    public int equalToDescendants(TreeNode root) {
        helper(root);
        return count;
    }

    // Post order traversal
    private int helper(TreeNode node) {
        if (node == null)
            return 0;

        int left = helper(node.left);
        int right = helper(node.right);

        if (left + right == node.val)
            count++;

        return left + right + node.val;
    }

    public static void main(String[] args) {
        _1973_CountDependents cd = new _1973_CountDependents();
        TreeUtils util = new TreeUtils();

        // 1
        Integer[] arr = {10, 3, 4, 2, 1};
        TreeNode root = util.construct(arr);
        System.out.println(cd.equalToDescendants(root));

        // 2
        Integer[] arr2 = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        root = util.construct(arr2);
        cd.count = 0;
        System.out.println(cd.equalToDescendants(root));
    }
}