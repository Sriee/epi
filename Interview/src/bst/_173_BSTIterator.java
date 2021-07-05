package bst;

import java.util.*;

class _173_BSTIterator {

    Stack<BSTNode> stack;

    public _173_BSTIterator(BSTNode root) {
        stack = new Stack<>();
        fillStack(root);
    }

    public int next() {
        BSTNode node = stack.pop();
        fillStack(node.right);
        return node.val;
    }

    private void fillStack(BSTNode root) {
        BSTNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        // [7,3,15,null,null,9,20]
        BSTNode root = new BSTNode(7);
        root.left = new BSTNode(3);
        root.right = new BSTNode(15);
        root.right.left = new BSTNode(9);
        root.right.right = new BSTNode(20);

        _173_BSTIterator bi = new _173_BSTIterator(root);
        System.out.printf("%d\n%d\n%s\n%d\n%s",
                bi.next(), bi.next(), bi.hasNext(),
                bi.next(), bi.hasNext()
        );
    }
}