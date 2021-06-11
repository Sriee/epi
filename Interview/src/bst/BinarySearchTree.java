package bst;

import java.util.*;

public class BinarySearchTree {
    private BSTNode root;

    public void add(int key) {
        root = insert(root, key);
    }

    private BSTNode insert(BSTNode node, int val) {
        if (node == null)
            return new BSTNode(val);

        if (val > node.val)
            node.right = insert(node.right, val);
        else if (val < node.val)
            node.left = insert(node.left, val);

        return node;
    }

    public boolean contains(int key) {
        return contains(root, key);
    }

    private boolean contains(BSTNode node, int val) {
        if (node == null)
            return false;

        if (val > node.val)
            return contains(node.right, val);
        else if (val < node.val)
            return contains(node.left, val);
        else
            return true;
    }

    public void remove(int key) {
        root = remove(root, key);
    }

    private BSTNode remove(BSTNode node, int val) {
        if (node == null)
            return null;

        if (val > node.val)
            node.right = remove(node.right, val);
        else if (val < node.val)
            node.left = remove(node.left, val);
        else {
            if (node.right == null)
                return node.left;
            else if (node.left == null)
                return node.right;
            else {
                node.val = getMin(node.right);
                node.right = remove(node.right, node.val);
            }
        }

        return node;
    }

    private int getMin(BSTNode node) {
        while (node.left != null)
            node = node.left;

        return node.val;
    }

    public List<BSTNode> getNodes() {
        Queue<BSTNode> queue = new LinkedList<>();
        List<BSTNode> result = new ArrayList<>();

        if (root == null)
            return result;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                BSTNode node = queue.poll();

                result.add(node);
                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);
            }
        }

        return result;
    }
}
