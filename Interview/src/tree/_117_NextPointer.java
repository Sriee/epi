package tree;

public class _117_NextPointer {
    private class TreeLinkNode {
        public int val;
        public TreeLinkNode left, right, next;

        public TreeLinkNode() {
        }

        public TreeLinkNode(int _val) {
            val = _val;
        }

        public TreeLinkNode(int _val, TreeLinkNode _left, TreeLinkNode _right, TreeLinkNode _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    TreeLinkNode prev, leftMost;

    private void process(TreeLinkNode node) {
        if (node == null)
            return;

        if (prev == null)
            leftMost = node;
        else
            prev.next = node;
        prev = node;
    }

    public TreeLinkNode connect(TreeLinkNode root) {
        if (root == null)
            return null;

        leftMost = root;

        while (leftMost != null) {
            prev = null;
            TreeLinkNode iter = leftMost;
            leftMost = null;

            while (iter != null) {
                this.process(iter.left);
                this.process(iter.right);
                iter = iter.next;
            }
        }

        return root;
    }
}
