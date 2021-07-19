package bst;

import java.util.*;

class _230_KthSmallest {

    public int kthSmallest(BSTNode root, int k) {
        // Note the use of deque for stack operations
        Deque<BSTNode> stack = new ArrayDeque<>();
        BSTNode curr = root;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            BSTNode node = stack.pop();
            k--;
            if (k == 0)
                return node.val;

            curr = node.right;
        }

        return -1;
    }

    public static void main(String[] args) {
        _230_KthSmallest ks = new _230_KthSmallest();

        // [3,1,4,null,2]
        BSTNode root = new BSTNode(3);
        root.left = new BSTNode(1);
        root.right = new BSTNode(4);
        root.left.right = new BSTNode(2);

        System.out.println(ks.kthSmallest(root, 3));
    }
}