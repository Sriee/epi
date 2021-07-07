package bst;

class _450_DeleteBSTNode {
    public BSTNode deleteNode(BSTNode root, int key) {
        if (root == null)
            return null;

        if (key < root.val)
            root.left = deleteNode(root.left, key);
        else if (key > root.val)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                BSTNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        }

        return root;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    public static void main(String[] args) {
        _450_DeleteBSTNode dn = new _450_DeleteBSTNode();

        // [43,23,47,null,37,null,53,29,41,null,null,null,31]
        BSTNode root = new BSTNode(43);
        root.left = new BSTNode(23);
        root.right = new BSTNode(47);
        root.left.right = new BSTNode(37);
        root.right.right = new BSTNode(53);
        root.left.right.left = new BSTNode(29);
        root.left.right.right = new BSTNode(41);

        dn.deleteNode(root, 53);
    }
}