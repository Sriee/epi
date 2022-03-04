package list;

public class GroupList {

    /**
     * Leetcode problem. Solution -> Accepted Deletes the duplicate nodes in a
     * sorted linked list.
     */
    private <R extends Comparable<R>> ListNode deleteDuplicates(ListNode head) {
        ListNode cursor = head;

        while (cursor.next != null) {
            if (cursor.val == cursor.next.val) {
                cursor.next = cursor.next.next;
            } else {
                cursor = cursor.next;
            }
        }
        return head;
    }

    /**
     * Leetcode problem. Solution -> Accepted Deletes all the duplicate nodes in a
     * sorted linked list.
     */
    private <R extends Comparable<R>> ListNode deleteAllDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode previous = dummyHead, cursor = dummyHead.next, nextDistinct = null;

        while (cursor != null) {
            boolean dirty = false;
            nextDistinct = cursor.next;
            while (nextDistinct != null && nextDistinct.val == cursor.val) {
                dirty = true;
                nextDistinct = nextDistinct.next;
            }

            if (dirty)
                previous.next = nextDistinct;
            else
                previous = cursor;

            cursor = nextDistinct;
        }
        return dummyHead.next;
    }

    /**
     * Leet Code problem. Solution -> Accepted Given a linked list and a value x,
     * partition it such that all nodes less than x come before nodes greater than
     * or equal to x. You should preserve the original relative order of the nodes
     * in each of the two partitions. For example, Given 1->4->3->2->5->2 and x = 3,
     * return 1->2->2->4->3->5.
     * 
     * @param head  head of the list
     * @param pivot element
     * @return paritioned list with respect to pivot
     */
    private ListNode partition(ListNode head, int pivot) {
        if (head == null)
            return head;

        ListNode before = new ListNode(), after = new ListNode(), cursor = head;
        ListNode b = before, a = after;
        while (cursor != null) {
            if (cursor.val < pivot) {
                b.next = cursor;
                b = b.next;
            } else {
                a.next = cursor;
                a = a.next;
            }
            cursor = cursor.next;
        }

        b.next = after.next;
        a.next = null;
        return before.next;
    }

    /**
     * Leet Code problem. Solution -> Accepted
     */
    private <T extends Comparable<T>> ListNode groupOddEven(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    @SuppressWarnings("unchecked")
    public <P extends Comparable<P>> ListNode[] splitList(ListNode head, int k) {
        ListNode[] splits = new ListNode[k];

        int length = ListUtil.length(head);
        int i = 0, j = 0, n = length / k, r = length % k;
        ListNode prev = null, node = head;

        while (i < k && node != null) {
            splits[i++] = node;
            while (j < n + ((r > 0) ? 1 : 0)) {
                prev = node;
                node = node.next;
                j++;
            }
            prev.next = null;
            r--;
        }

        return splits;
    }

    public static void main(String[] args) {
        GroupList gl = new GroupList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(34);
        head.next.next.next.next = new ListNode(50);

        ListNode headb = new ListNode(21);
        headb.next = new ListNode(45);
        headb.next.next = new ListNode(34);
        headb.next.next.next = new ListNode(34);
        headb.next.next.next.next = new ListNode(50);

        ListUtil.print(headb);

        ListNode duplicatesRemoved = gl.deleteDuplicates(headb);
        System.out.println("\nDuplicates removed");
        ListUtil.print(duplicatesRemoved);

        ListNode allDuplicatesRemoved = gl.deleteAllDuplicates(head);
        System.out.println("\nAll Duplicates removed");
        ListUtil.print(allDuplicatesRemoved);

        ListNode gHead = gl.groupOddEven(head);
        System.out.println("\nGroup Odd Even");
        ListUtil.print(gHead);


        System.out.println("\nPartitioned");
        ListNode part = gl.partition(headb, 35);
        ListUtil.print(part);

        ListNode lst = head;
        ListUtil.print(lst);
        ListNode[] sp = gl.splitList(lst, 5);
        for (ListNode item : sp)
            ListUtil.print(item);
    }
}
