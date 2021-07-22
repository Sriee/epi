package bst;

import java.util.*;

class _1586_BSTIterator2 {
    Deque<BSTNode> stack, prevStack;

    public _1586_BSTIterator2(BSTNode root) {
        stack = new ArrayDeque<>();
        prevStack = new ArrayDeque<>();

        BSTNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        BSTNode node = stack.pop();
        prevStack.push(node);

        BSTNode curr = node.right;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        return node.val;
    }

    public boolean hasPrev() {
        return prevStack.size() > 1;
    }

    public int prev() {
        BSTNode node = prevStack.pop();

        /*
         * There is a disadvantage in this step. We are removing elements on the stack
         * that we visited. On a next 'next' call we should again traverse the left subtree
         * to populate the values
         */
        BSTNode curr = node.right;
        while (curr != null) {
            stack.pop();
            curr = curr.left;
        }

        stack.push(node);
        assert prevStack.peek() != null;
        return prevStack.peek().val;
    }

    public static void main(String[] args) {
        // [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9]
        BSTNode root = new BSTNode(15);
        root.left = new BSTNode(6);
        root.right = new BSTNode(18);

        root.left.left = new BSTNode(3);
        root.left.right = new BSTNode(7);
        root.right.left = new BSTNode(17);
        root.right.right = new BSTNode(20);

        root.left.left.left = new BSTNode(2);
        root.left.left.right = new BSTNode(4);
        root.left.right.right = new BSTNode(13);

        root.left.right.left = new BSTNode(9);
        _1586_BSTIterator2 bi2 = new _1586_BSTIterator2(root);

        System.out.printf("%d %d %s %d %s ", bi2.next(), bi2.next(), bi2.hasPrev(), bi2.next(), bi2.hasNext());
        System.out.printf("%d %d %s %d %d\n", bi2.prev(), bi2.next(), bi2.hasNext(), bi2.next(), bi2.next());
    }
}