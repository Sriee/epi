package bst;

class _669_TrimBST {
    public BSTNode trimBST(BSTNode root, int low, int high) {
        if (root == null)
            return root;

        if (root.val >= low && root.val <= high) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        } else if (root.val < low) {
            return trimBST(root.right, low, high);
        } else {
            return trimBST(root.left, low, high);
        }
    }

    public static void main(String[] args) {
        _669_TrimBST tb = new _669_TrimBST();

        // [3,0,4,null,2,null,null,1]
        BSTNode root = new BSTNode(3);
        root.left = new BSTNode(0);
        root.right = new BSTNode(4);
        root.left.right = new BSTNode(2);
        root.left.right.left = new BSTNode(1);

        BSTNode newRoot = tb.trimBST(root, 1, 3);
        System.out.println(newRoot);
    }
}