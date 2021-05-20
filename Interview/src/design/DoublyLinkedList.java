package design;

public class DoublyLinkedList {
    DLLNode head = new DLLNode("HEAD"), tail = new DLLNode("TAIL");

    public DoublyLinkedList() {
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(DLLNode node) {
        node.next = head.next;
        node.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    public void delete(DLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    public String firstKey() {
        if (isEmpty())
            return "";

        return head.next.key;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    @Override
    public String toString() {
        DLLNode iter = head;
        StringBuilder sb = new StringBuilder();

        while (iter != null) {
            sb.append(iter).append("--> ");
            iter = iter.next;
        }

        sb.append("null");
        return sb.toString();
    }
}
