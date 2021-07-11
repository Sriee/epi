package bst;

import java.util.List;

class _108_ArrToBST {
    /**
     * Approach 1: Divide and Conquer
     */
    public BSTNode sortedArrayToBST(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private BSTNode construct(int[] nums, int left, int right) {
        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        BSTNode node = new BSTNode(nums[mid]);
        node.left = construct(nums, left, mid - 1);
        node.right = construct(nums, mid + 1, right);

        return node;
    }

    public static void main(String[] args) {
        _108_ArrToBST ab = new _108_ArrToBST();
        int[] nums = {-10, -3, 0, 5, 9};
        BSTNode root = ab.sortedArrayToBST(nums);
        BinarySearchTree bst = new BinarySearchTree();
        List<BSTNode> lst = bst.getNodes(root);

        for (BSTNode node : lst) {
            System.out.println(node);
        }
    }
}