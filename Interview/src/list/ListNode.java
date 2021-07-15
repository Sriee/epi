package list;

public class ListNode<T extends Comparable<T>> implements Comparable<ListNode<T>> {
    public T val;
    public ListNode<T> next;

    public ListNode() {
        this.val = null;
        this.next = null;
    }

    public ListNode(T val) {
        this.val = val;
    }

    public ListNode(T val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public int compareTo(ListNode<T> other) {
        return this.val.compareTo(other.val);
    }

    @Override
    public String toString() {
        return "ListNode(" + val + ")";
    }
}
