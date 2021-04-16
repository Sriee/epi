package queue;

import java.util.*;

/**
 * Double ended Linked List Node Approach
 */
public class _232_QueueUsingStack {

    private class Node {
        int x;
        Node next;
        Node prev;

        public Node(int _x) {
            this.x = _x;
        }

        @Override
        public String toString() {
            return "" + this.x;
        }
    }

    Stack<Node> stack;
    Node front = null;

    /**
     * Initialize your data structure here.
     */
    public _232_QueueUsingStack() {
        stack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        Node node = new Node(x);

        if (front == null) {
            front = node;
            stack.push(node);
        } else {
            Node top = stack.peek();
            top.next = node;
            node.prev = top;

            stack.push(node);
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        int f = front.x;
        front.x = -1;

        front = front.next;
        return f;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return front.x;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        Stack<Node> temp = new Stack<>();
        while (!stack.isEmpty()) {
            if (stack.peek().x != -1)
                temp.push(stack.peek());

            stack.pop();
        }

        stack = temp;
        return stack.isEmpty();
    }
}
