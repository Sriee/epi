package bst;

class _703_KthLargest {

    /**
     * This approach illustrates a technique on making use of additional variable on the TreeNode to solve problems.
     */
    TreeNode root;
    final int k;

    public _703_KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i : nums)
            root = insert(root, i);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null)
            return new TreeNode(val);

        node.count++;
        if (val < node.val)
            node.left = insert(node.left, val);
        else
            node.right = insert(node.right, val);

        return node;
    }

    public int add(int val) {
        root = insert(root, val);
        return kthLargest();
    }

    private int kthLargest() {
        int ck = k;
        TreeNode iter = root;

        while (ck > 0) {
            int pos = 1 + (iter.right != null ? iter.right.count : 0);

            if (ck > pos) {
                ck -= pos;
                iter = iter.left;
            } else if (ck < pos) {
                iter = iter.right;
            } else {
                break;
            }
        }

        return iter.val;
    }

    static class TreeNode {
        int val, count = 1;
        TreeNode left = null, right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        _703_KthLargest kl = new _703_KthLargest(3, nums);
        System.out.println(kl.add(3));
    }
}