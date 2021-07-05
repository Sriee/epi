package bst;

class _700_SearchBST {
    public BSTNode searchBST(BSTNode root, int val) {
        BSTNode iter = root;

        while (iter != null) {
            if (val < iter.val)
                iter = iter.left;
            else if (val > iter.val)
                iter = iter.right;
            else
                break;
        }

        return iter;
    }

    public static void main(String[] args) {
        _700_SearchBST sb = new _700_SearchBST();

        // [43,23,47,null,37,null,53,29,41,null,null,null,31]
        BSTNode root = new BSTNode(43);
        root.left = new BSTNode(23);
        root.right = new BSTNode(47);
        root.left.right = new BSTNode(37);
        root.right.right = new BSTNode(53);
        root.left.right.left = new BSTNode(29);
        root.left.right.right = new BSTNode(41);

        System.out.println(sb.searchBST(root, 37));
        System.out.println(sb.searchBST(root, 46));
    }
}