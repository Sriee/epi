package linkedlist;

public class ListUtils {

    /**
     * Calculate length of a LinkedList
     */
    public static int length(ListNode node) {
        ListNode fast = node;
        int len = 0;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            len += 2;
        }
        return fast == null ? len : len + 1;
    }

    /**
     * Utility function to print the list. The list will be printed in the following
     * format a -> b -> c -> d -> e
     *
     * @param head List head
     */
    public static void print(ListNode head) {
        if (head == null)
            return;

        ListNode cursor = head;

        while (cursor.next != null) {
            System.out.print(cursor.val + " -> ");
            cursor = cursor.next;
        }

        System.out.println(cursor.val);
    }
}
