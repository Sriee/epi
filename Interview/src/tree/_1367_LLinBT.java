package tree;

import linkedlist.ListNode;
import linkedlist.ListUtils;

public class _1367_LLinBT {

    /**
     * TC: O(N * min(L, H))
     * SC: O(H)
     *
     *  N - Number of nodes in the tree
     *  H - Height of the tree
     *  L - Length of the Linked List
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        return search(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean search(ListNode head, TreeNode root) {
        if (head == null)
            return true;

        if (root == null)
            return false;

        return head.val == root.val && (search(head.next, root.left) || search(head.next, root.right));
    }

    public static void main(String[] args) {
        _1367_LLinBT lb = new _1367_LLinBT();

        // Tree
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(4);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(2);

        root.left.right.left = new TreeNode(1);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(8);

        root.right.left.right.left = new TreeNode(1);
        root.right.left.right.right = new TreeNode(3);

        // Linked List
        int[] list = {4, 2, 8};
        ListNode head = ListUtils.construct(list);
        System.out.println(lb.isSubPath(head, root));
    }
}
