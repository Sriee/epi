package list;

public class _19_RemNFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head, start = head;

        for (int i = 0; i < n && end != null; i++) end = end.next;
        if (end == null) {
            head = head.next;
        } else {
            for (; end != null && end.next != null; start = start.next, end = end.next);
            start.next = start.next.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        _19_RemNFromEnd re = new _19_RemNFromEnd();
        ListNode newHead = re.removeNthFromEnd(head, 2);
        ListUtil.print(newHead);
    }
}
