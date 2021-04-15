package stack;

import java.util.Stack;

class _155_MinStack {

    Stack<Integer> stack, minStack;

    public _155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty())
            minStack.push(val);
        else
            minStack.push((val < minStack.peek()) ? val : minStack.peek());
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        _155_MinStack ms = new _155_MinStack();

        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        ms.push(5);

        System.out.println(ms.top());
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.getMin());
        System.out.println(ms.getMin());
    }
}

