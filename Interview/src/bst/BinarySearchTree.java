package bst;

public class BinarySearchTree {

    private BSTNode root;

    public void put(int key, int val) {
        root = insert(root, key, val);
    }

    private BSTNode insert(BSTNode node, int key, int val) {
        if (node == null)
            return new BSTNode(key, val);

        if (key > node.key)
            node.right = insert(node.right, key, val);
        else if (key < node.key)
            node.left = insert(node.left, key, val);
        else
            node.val = val;

        return node;
    }

    public int get(int key) {
        return get(root, key);
    }

    private int get(BSTNode node, int key) {
        if (node == null)
            return -1;

        if (key > node.key)
            return get(node.right, key);
        else if (key < node.key)
            return get(node.left, key);
        else
            return node.val;
    }

    public void remove(int key) {
        root = remove(root, key);
    }

    private BSTNode remove(BSTNode node, int key) {
        if (node == null)
            return null;

        if (key > node.key)
            node.right = remove(node.right, key);
        else if (key < node.key)
            node.left = remove(node.left, key);
        else {
            if (node.right == null)
                return node.left;
            else if (node.left == null)
                return node.right;
            else {
                BSTNode minNode = getMin(node.right);
                node.key = minNode.key;
                node.val = minNode.val;
                node.right = remove(node.right, node.key);
            }
        }

        return node;
    }

    private BSTNode getMin(BSTNode node) {
        while (node.left != null)
            node = node.left;

        return node;
    }
}
