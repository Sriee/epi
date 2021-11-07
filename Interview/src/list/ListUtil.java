package list;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ListUtil {

    /**
     * Delete a node in a linked list
     * 
     * @param head     Linked List head
     * @param toDelete the node to be deleted
     */
    public static <D extends Comparable<D>> void delete(ListNode<D> head, ListNode<D> toDelete) {
        ListNode<D> cursor = head, previous = null;
        while (cursor != null && cursor != toDelete) {
            previous = cursor;
            cursor = cursor.next;
        }

        if (previous == null) {
            head = head.next;
        } else if (cursor == null) {
            System.out.println("Could not find the node to delete.");
        } else if (cursor.next == null) {
            previous.next = null;
        } else {
            previous.val = cursor.next.val;
            previous.next = cursor.next.next;
        }
    }

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public static <T extends Comparable<T>> ListNode<T> merge(ListNode<T> list1, ListNode<T> list2) {
        ListNode<T> dummy = new ListNode<>(null, null);
        ListNode<T> c1 = list1, c2 = list2, current = dummy;
        while (c1 != null && c2 != null) {
            if (c1.val.compareTo(c2.val) <= 0) {
                current.next = c1;
                c1 = c1.next;
            } else {
                current.next = c2;
                c2 = c2.next;
            }
            current = current.next;
        }
        current.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    /**
     * Leet code problem. Solution -> Accepted Solutions that did not work Trial 1 -
     * Using a class called Pair to hold [val, index]. While linking to cursor need
     * to create a new link node. Increases space complexity. Trial 2 - Wrote
     * Priority queue comparator for int[] instead of a class Trial 3 - Store
     * list[i]th element in priority queue and keep pointing the cursor to head of
     * the queue Storing all elements of the list in a queue gave a time limit
     * exceeded exception. But the solution worked
     * 
     * @param list Array of lists to be merged
     * @return merged list
     */
    public static <S extends Comparable<S>> ListNode<S> mergeKSortedList(ArrayList<ListNode<S>> list) {
        ListNode<S> dummyHead = new ListNode<>(null, null);
        ListNode<S> cursor = dummyHead;
        int j = 1;
        PriorityQueue<ListNode<S>> queue = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
            ListNode<S> temp = list.get(i);
            if (temp != null)
                queue.add(temp);
        }

        while (!queue.isEmpty()) {
            System.out.println(j++ + " " + queue);
            cursor.next = queue.poll();
            cursor = cursor.next;

            if (cursor.next != null)
                queue.add(cursor.next);
        }
        return dummyHead.next;
    }

    /**
     * Leetcode problem. Solution -> Accepted Solution does not uses recursion and
     * does not require the length of the lists to be known
     */
    public <I extends Comparable<I>> ListNode<I> getIntersectionNode(ListNode<I> headA, ListNode<I> headB) {
        ListNode<I> first = headA, second = headB;
        while (first != second) {
            first = (first == null) ? headB : first.next;
            second = (second == null) ? headA : second.next;
        }
        return first;
    }

    /**
     * Leetcode problem. Solution -> Accepted Solution uses recursion and does
     * require the length of the lists to be known
     * /
    public <I extends Comparable<I>> ListNode<I> intersection(ListNode<I> l1, ListNode<I> l2) {
        int len1 = ListUtils.length(l1), len2 = length(l2), diff = 0;
        if (len1 <= len2) {
            diff = len2 - len1;
            return this.intersectionHelper(l1, l2, diff);
        } else {
            diff = len1 - len2;
            return this.intersectionHelper(l2, l1, diff);
        }
    }
     */

    private <I extends Comparable<I>> ListNode<I> intersectionHelper(ListNode<I> first, ListNode<I> second, int diff) {
        if (diff != 0) {
            diff--;
            return this.intersectionHelper(first, second.next, diff);
        }

        if (first == second)
            return first;

        if (first.next == null && second.next == null)
            return null;

        return this.intersectionHelper(first.next, second.next, diff);
    }

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public static <S extends Comparable<S>> ListNode<S> reverseSublist(ListNode<S> head, int start, int finish) {
        ListNode<S> dummy = new ListNode<>(null, head);
        ListNode<S> sublistHead = dummy;
        int k = 1;
        while (k++ < start) {
            sublistHead = sublistHead.next;
        }
        ListNode<S> sublistIter = sublistHead.next, temp = null;

        while (start++ < finish) {
            temp = sublistIter.next;
            sublistIter.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }

        return dummy.next;
    }

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public static ListNode<Integer> reverseKSublist(ListNode<Integer> head, int k) {
        int length = length(head);
        int c = 0, subs = length / k;
        ListNode<Integer> dummyHead = new ListNode<>(null, head);
        ListNode<Integer> sublistHead = dummyHead;
        ListNode<Integer> sublistIterator = sublistHead.next, temp = null;
        while (c++ < subs) {
            int start = 0;
            while (start++ < k - 1 && sublistIterator.next != null) {
                temp = sublistIterator.next;
                sublistIterator.next = temp.next;
                temp.next = sublistHead.next;
                sublistHead.next = temp;
            }
            sublistHead = sublistIterator;
            sublistIterator = sublistHead.next;
        }
        return dummyHead.next;
    }

    /**
     * Leetcode problem. Solution -> Accepted
     */
    public static <S extends Comparable<S>> ListNode<S> reverse(ListNode<S> head) {
        ListNode<S> dummy = new ListNode<>(null, null);
        dummy.next = head;
        ListNode<S> iter = dummy.next, temp = null;
        while (iter.next != null) {
            temp = iter.next;
            iter.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return dummy.next;
    }

    /**
     * Recursivly reversing the list
     *
     * @param <S>
     * @param head
     * @return
     */
    public static <S extends Comparable<S>> ListNode<S> reverseRecursive(ListNode<S> head) {
        if (head == null || head.next == null)
            return head;

        ListNode<S> reverseNode = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return reverseNode;
    }

    /**
     * Utility function to print the list. The list will be printed in the following
     * format a -> b -> c -> d -> e
     *
     * @param head List head
     */
    public static <S extends Comparable<S>> void print(ListNode<S> head) {
        if (head == null)
            return;
        ListNode<S> cursor = head;
        while (cursor.next != null) {
            System.out.print(cursor.val + " -> ");
            cursor = cursor.next;
        }
        System.out.println(cursor.val);
    }

    /**
     * Utility function to calculate the length of a Linked List
     *
     * @param head Linked List head
     * @return length of the list
     */
    public static <T extends Comparable<T>> int length(ListNode<T> head) {
        ListNode<T> cursor = head;
        int count = 0;
        while (cursor != null) {
            count++;
            cursor = cursor.next;
        }
        return count;
    }

    /**
     * Leet code problem. Solution -> Accepted Assumed k < n. Previous solution
     * works if k < n
     * 
     * @param head list
     * @param k    should be shifted k times
     * @return list right shifted k times
     */
    public static <C extends Comparable<C>> ListNode<C> rightCyclicShift(ListNode<C> head, int k) {
        if (head == null)
            return head;

        ListNode<C> cursor = head;
        int len = 1;

        while (cursor.next != null) {
            ++len;
            cursor = cursor.next;
        }
        k %= len;

        if (k == 0) // No need to shift
            return head;

        cursor.next = head; // Making a cycle
        ListNode<C> tail = cursor;
        int stepsToNewHead = len - k;

        while (stepsToNewHead-- > 0) {
            tail = tail.next;
        }

        ListNode<C> newHead = tail.next;
        tail.next = null;

        return newHead;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 1 ms. Optimal Solution This
     * solution uses O(n) time & O(1) space complexity. Trivial solution would be to
     * store all the node up to mid point in a HashMap. Then travese the second,
     * keep checking the hashmap.
     * 
     * @param <C>  type
     * @param head list
     * @return true if list is a palindrome false otherwise
     */
    public <C extends Comparable<C>> boolean isPalindrome(ListNode<C> head) {
        ListNode<C> slow = head, fast = head;

        // Find mid
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Trick: When fast is null then we know that list has odd number of elements
        if (fast != null)
            slow = slow.next;

        // Another variation for reversing a list without using dummy head
        // Reverse second half
        ListNode<C> prev = null, temp;

        while (slow != null) {
            temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        slow = prev;
        fast = head;
        while (slow != null) {
            if (fast.val != slow.val)
                return false;

            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode<Integer> temp = new ListNode<>(25);
        ListNode<Integer> l1 = new ListNode<>(5);
        l1.next = new ListNode<>(3);
        l1.next.next = new ListNode<>(10);
        l1.next.next.next = new ListNode<>(1);
        l1.next.next.next.next = temp;
        l1.next.next.next.next.next = new ListNode<>(60);
        l1.next.next.next.next.next.next = new ListNode<>(77);

        /*
         * delete(l1, temp); print(l1); ListNode<Integer> reversed = reverseKSublist(l1,
         * 3); print(reversed); ListNode<Integer> l2 = new ListNode<>(5); l2.next = new
         * ListNode<>(3); ListNode<Integer> shifted = rightCyclicShift(l2, 3);
         * print(shifted);
         */
    }
}
