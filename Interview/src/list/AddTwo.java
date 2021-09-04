package list;

public class AddTwo {

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public boolean hasLoop(ListNode<Integer> head) {
        ListNode<Integer> slow = head, fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                this.removeLoop(head, slow);
                return true;
            }
        }
        return false;
    }

    private ListNode<Integer> removeLoop(ListNode<Integer> head, ListNode<Integer> node) {
        ListNode<Integer> first = head, second = node;

        while (second.next != first.next) {
            first = first.next;
            second = second.next;
        }
        second.next = null;
        return head;
    }

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public void reorderList(ListNode<Integer> head) {
        if (head == null || head.next == null)
            return;

        ListNode<Integer> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode<Integer> reversed = ListUtil.reverse(slow.next);

        slow.next = null;
        ListNode<Integer> reverseIter = reversed, iter = head, temp, temp2;
        while (reverseIter != null) {
            temp = iter.next;
            temp2 = reverseIter.next;
            iter.next = reverseIter;
            reverseIter.next = temp;
            iter = reverseIter.next;
            reverseIter = temp2;
        }
    }

    /**
     * Leetcode problem. Solution -> Accepted You are given two non-empty linked
     * lists representing two non-negative integers. The most significant digit
     * comes first and each of their nodes contain a single digit. Add the two
     * numbers and return it as a linked list. You may assume the two numbers do not
     * contain any leading zero, except the number 0 itself. Example: Input: (7 -> 2
     * -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7
     * 
     * @param l1 first list with numbers in MSB
     * @param l2 second list with numbers in MSB
     * @return head of the two list summed and in MSB
     */
    public ListNode<Integer> addMSB(ListNode<Integer> l1, ListNode<Integer> l2) {
        long lenA = 0, lenB = 0, diff = 0;
        ListNode<Integer> cursor = l1, a, b;
        while (cursor != null) {
            lenA++;
            cursor = cursor.next;
        }

        cursor = l2;
        while (cursor != null) {
            lenB++;
            cursor = cursor.next;
        }

        if (lenA > lenB) {
            diff = lenA - lenB;
            a = l1;
            b = l2;
        } else {
            diff = lenB - lenA;
            a = l2;
            b = l1;
        }

        ListNode<Integer> newHead = new ListNode<>(1);
        newHead.next = addHelper(a, b, diff);

        if (newHead.next.val > 9) {
            newHead.next.val -= 10;
        } else {
            newHead = newHead.next;
        }
        return newHead;
    }

    private ListNode<Integer> addHelper(ListNode<Integer> a, ListNode<Integer> b, long diff) {
        ListNode<Integer> temp = null;
        int carry = 0;

        if (diff > 0) {
            diff--;
            temp = addHelper(a.next, b, diff);
            b = null;
        } else if (a.next != null && b.next != null) {
            temp = addHelper(a.next, b.next, diff);
        }

        if (temp != null) {
            if (temp.val > 9) {
                carry = 1;
                temp.val -= 10;
            }
        }
        ListNode<Integer> newNode = new ListNode<>(((a == null) ? 0 : a.val) + ((b == null) ? 0 : b.val) + carry);
        newNode.next = temp;
        return newNode;
    }

    public static void main(String[] args) {
        AddTwo at = new AddTwo();
        ListNode<Integer> first = new ListNode<>(9);

        ListNode<Integer> second = new ListNode<>(1);
        second.next = new ListNode<>(9);
        second.next.next = new ListNode<>(9);
        second.next.next.next = new ListNode<>(9);
        second.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next.next = new ListNode<>(9);
        second.next.next.next.next.next.next.next.next.next = new ListNode<>(9);

        ListNode<Integer> looped = new ListNode<>(2);
        looped.next = new ListNode<>(567);
        looped.next.next = new ListNode<>(48);
        looped.next.next.next = new ListNode<>(74);
        looped.next.next.next.next = new ListNode<>(5);
        looped.next.next.next.next.next = new ListNode<>(9);
        looped.next.next.next.next.next.next = looped.next.next;

        System.out.println(at.hasLoop(looped));
        ListUtil.print(looped);

        System.out.println("\nAdd MSB");
        ListNode<Integer> msb = at.addMSB(first, second);
        ListUtil.print(msb);
    }
}
