package linkedlist;

import util.PrintHypens;

import java.util.*;

public class _2487_RemoveNodes {
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();

        for (ListNode iter = head; iter != null; iter = iter.next) {
            while (!stack.isEmpty() && iter.val > stack.peek().val) {
                stack.pop();
            }

            stack.push(iter);
        }

        ListNode next = null;
        while(!stack.isEmpty()) {
            ListNode curr = stack.pop();
            curr.next = next;
            next = curr;
        }

        return next;
    }

    public static void main(String[] args) {
        _2487_RemoveNodes rn = new _2487_RemoveNodes();
        int[][] inputs = {
                {5, 2, 13, 3, 8},
                {1, 1, 1, 1}
        };
        for (int i = 0; i < inputs.length; i++) {
            ListNode inpHead = ListUtils.construct(inputs[i]);
            System.out.printf("%d. Input = ", (i + 1));
            ListUtils.print(inpHead);
            System.out.print("List with greater value removed on the right side = ");
            ListUtils.print(rn.removeNodes(inpHead));
            System.out.println(PrintHypens.generate());
            System.out.println();
        }
    }
}
