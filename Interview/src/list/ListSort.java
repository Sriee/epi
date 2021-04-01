package list;

public class ListSort {

    /**
     * Leet code Problem. Solution -> Accepted Insertion sort implementation
     *
     * @param head: Head of the list to be sorted
     * @return sorted linked list
     */
    public <T extends Comparable<T>> ListNode<T> insertionSort(ListNode<T> head) {
        ListNode<T> newHead = new ListNode<>(), temp = head, toInsert = null, prev = null, iter = null;
        boolean isInserted = false;

        while (temp != null) {
            toInsert = temp;
            temp = toInsert.next;
            toInsert.next = null;

            isInserted = false;
            prev = newHead;
            iter = newHead.next;

            while (!isInserted) {
                if (iter == null) {
                    prev.next = toInsert;
                    isInserted = true;
                } else if (toInsert.data.compareTo(iter.data) <= 0) {
                    prev.next = toInsert;
                    toInsert.next = iter;
                    isInserted = true;
                } else {
                    prev = iter;
                    iter = iter.next;
                }
            }
        }
        return newHead.next;
    }

    /**
     * Leet code Problem. Solution -> Accepted Merge Sort Implementation
     * 
     * @param head: Head of the list to be sorted
     * @return sorted linked list
     */
    public <T extends Comparable<T>> ListNode<T> mergeSort(ListNode<T> head) {
        if (head == null || head.next == null)
            return head;

        ListNode<T> slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode<T> right = this.mergeSort(slow.next);
        slow.next = null;
        ListNode<T> left = this.mergeSort(head);

        return ListUtil.merge(left, right);
    }

    public static void main(String[] args) {
        ListSort s = new ListSort();
        System.out.println("Before Sorting:");
        // To sort
        ListNode<Integer> toSort = new ListNode<>(21);
        toSort.next = new ListNode<>(0);
        toSort.next.next = new ListNode<>(-41);
        toSort.next.next.next = new ListNode<>(-1);
        toSort.next.next.next.next = new ListNode<>(77);
        ListUtil.print(toSort);

        System.out.println("After sorted:");
        ListNode<Integer> sorted = s.mergeSort(toSort);
        ListUtil.print(sorted);
    }

}
