package bst;

import list.ListNode;

import java.util.Random;

class _109_BSTfromSortedList {
    Random rand = new Random();
    ListNode<Integer> lstNode;

    public BSTNode naiveApproach(ListNode<Integer> head) {
        int length = findLength(head);
        lstNode = head;

        return construct(0, length - 1);
    }

    private int findLength(ListNode<Integer> head) {
        ListNode<Integer> iter = head;
        int len = 0;

        /*
         * Technique to find the length sooner. We use a fast pointer to calculate
         * length of the linked list.
         *
         * Note: This did not improve the run time of the solution.
         */
        while (iter != null && iter.next != null) {
            len += 2;
            iter = iter.next.next;
        }

        return iter == null ? len : len + 1;
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

    /**
     * DSW Algorithm
     * <p>
     * See <a href="https://csactor.blogspot.com/2018/08/dsw-day-stout-warren-algorithm-dsw.html">DSW Algorithm</a>
     */
    public BSTNode dswAlgorithm(ListNode<Integer> head) {
        BSTNode dummy = new BSTNode(-1);
        ListNode<Integer> iter = head;
        BSTNode treeIter = dummy;

        // Step 1: Make Vine
        int n = 0;
        while (iter != null) {
            treeIter.right = new BSTNode(iter.val);
            iter = iter.next;
            treeIter = treeIter.right;
            n++;
        }

        // Height of the tree
        int h = (int) (Math.log(n + 1) / Math.log(2));

        // No of nodes required for a complete balanced tree
        int m = (int) (Math.pow(2, h) - 1);

        // Step 2: Construct the balanced binary search tree
        // Left shift n - m times to handle the out of balance tree's
        construct(dummy, n - m);

        for (m = m / 2; m > 0; m /= 2)
            construct(dummy, m);

        return dummy.right;
    }

    private void construct(BSTNode grand, int m) {
        BSTNode curr = grand.right;

        while (m-- > 0) {
            BSTNode temp = curr;
            curr = curr.right;
            grand.right = curr;

            temp.right = curr.left;
            curr.left = temp;

            grand = curr;
            curr = curr.right;
        }
    }

    public BSTNode sortedListToBST(ListNode<Integer> head) {
        BSTNode res = null;
        switch (rand.nextInt(2)) {
            case 0:
                System.out.println("Naive Approach");
                res = naiveApproach(head);
                break;
            case 1:
                System.out.println("DSW Algorithm Approach");
                res = dswAlgorithm(head);
                break;
        }

        return res;
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