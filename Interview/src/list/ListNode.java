package list;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
        this.next = null;
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("%d -> ", val);
    }
}
