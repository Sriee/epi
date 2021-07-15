package bst;

import list.ListNode;

class _109_BSTfromSortedList {
    ListNode<Integer> lstNode;

    public BSTNode sortedListToBST(ListNode<Integer> head) {
        int length = findLength(head);
        lstNode = head;

        return construct(0, length - 1);
    }

    private int findLength(ListNode<Integer> head) {
        ListNode<Integer> iter = head;
        int len = 0;

        while (iter != null) {
            len++;
            iter = iter.next;
        }

        return len;
    }

    private BSTNode construct(int begin, int end) {
        if (begin > end)
            return null;

        int mid = begin + (end - begin) / 2;

        BSTNode left = construct(begin, mid - 1);
        BSTNode node = new BSTNode(lstNode.val);
        lstNode = lstNode.next;

        node.left = left;
        node.right = construct(mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        _109_BSTfromSortedList bsl = new _109_BSTfromSortedList();
        // [-10,-3,0,5,9]
        ListNode<Integer> dummy = new ListNode<>();
        ListNode<Integer> iter = dummy;
        int[] nums = {-10, -3, 0, 5, 9};

        for (int i : nums) {
            iter.next = new ListNode<>(i);
            iter = iter.next;
        }

        BSTNode root = bsl.sortedListToBST(dummy.next);
        System.out.println(root);
    }
}