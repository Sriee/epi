package stack;

import java.util.*;

/**
 * Uses 2 queue approach
 */
public class _225_StackUsingQueue {
    Queue<Integer> fq, sq;

    /**
     * Initialize your data structure here.
     */
    public _225_StackUsingQueue() {
        fq = new LinkedList<>();
        sq = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        fq.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        top();
        return sq.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        if (!fq.isEmpty()) {
            while (!fq.isEmpty()) {
                sq.offer(fq.poll());

                // Reverse
                for (int i = 1; i < sq.size(); i++)
                    sq.offer(sq.poll());
            }
        }

        return sq.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return fq.isEmpty() && sq.isEmpty();
    }
}
