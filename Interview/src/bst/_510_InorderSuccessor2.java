package bst;

class _510_InorderSuccessor2 {
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int v) {
            this.val = v;
        }
    }

    public Node inorderSuccessor(Node node) {
        Node successor = null, iter;

        if (node.right == null) {
            iter = node.parent;
            while (iter != null) {
                if (iter.val > node.val) {
                    return iter;
                }
                iter = iter.parent;
            }
        } else {
            iter = node.right;
            while (iter != null) {
                successor = iter;
                iter = iter.left;
            }
        }

        return successor;
    }

    public static void main(String[] args) {
        _510_InorderSuccessor2 is = new _510_InorderSuccessor2();

        Node root = new Node(5);
        root.left = new Node(3);
        root.left.parent = root;

        root.right = new Node(6);
        root.right.parent = root;

        root.left.left = new Node(2);
        root.left.left.parent = root.left;

        root.left.right = new Node(4);
        root.left.right.parent = root.left;

        root.left.left.left = new Node(1);
        root.left.left.left.parent = root.left.left;

        Node res;
        // 1
        res = is.inorderSuccessor(root);
        System.out.println(res.val);

        // 2
        res = is.inorderSuccessor(root.left.left);
        System.out.println(res.val);
    }
}