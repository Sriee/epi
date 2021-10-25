package tree;

import linkedlist.ListNode;
import linkedlist.ListUtils;

public class _1367_LLinBT {

    /**
     * TC: O(N + L)
     * SC: O(H + L)
     *
     * where N - Number of nodes in the tree
     *       H - Height of the tree
     *       L - Length of the Linked List
     */
    private boolean search(ListNode head, TreeNode root) {
        int[] pattern = convertListToArr(head);
        int[] lps = computeLps(pattern);

        return search(root, pattern, lps, 0);
    }

    private boolean search(TreeNode root, int[] pattern, int[] lps, int idx) {
        if (idx == pattern.length)
            return true;

        if (root == null)
            return false;

        while (idx > 0 && root.val != pattern[idx])
            idx = lps[idx - 1];

        if (root.val == pattern[idx])
            idx++;

        return search(root.left, pattern, lps, idx) || search(root.right, pattern, lps, idx);
    }

    private int[] convertListToArr(ListNode head) {
        int len = ListUtils.length(head), i = 0;
        int[] arr = new int[len];
        ListNode iter = head;

        while (iter != null) {
            arr[i++] = iter.val;
            iter = iter.next;
        }

        return arr;
    }

    private int[] computeLps(int[] pattern) {
        int n = pattern.length, i = 1, j = 0;
        int[] lps = new int[n];

        while (i < n) {
            if (pattern[i] == pattern[j]) {
                lps[i] = ++j;
                i++;
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
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
        System.out.println(lb.search(head, root));


    }
}
