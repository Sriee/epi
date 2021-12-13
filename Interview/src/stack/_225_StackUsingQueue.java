package stack;

import java.util.*;

/**
 * Using single queue approach
 */
public class _225_StackUsingQueue {
    Deque<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public _225_StackUsingQueue() { queue = new ArrayDeque<>();}

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.offer(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() { return queue.removeLast(); }

    /**
     * Get the top element.
     */
    public int top() { return queue.getLast(); }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() { return queue.isEmpty(); }

    public static void main(String[] args) {
        _225_StackUsingQueue stack = new _225_StackUsingQueue();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.top());

        stack.push(40);
        System.out.println(stack.empty());
        System.out.println(stack.pop());
    }
}
