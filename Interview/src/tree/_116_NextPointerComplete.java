package tree;

public class _116_NextPointerComplete {

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

    public TreeLinkNode connect(TreeLinkNode root) {
        if (root == null)
            return null;

        TreeLinkNode treeLinkNode = root;

        while (treeLinkNode.left != null) {

            TreeLinkNode iter = treeLinkNode;
            while (iter != null) {
                iter.left.next = iter.right;

                if (iter.next != null)
                    iter.right.next = iter.next.left;

                iter = iter.next;
            }

            treeLinkNode = treeLinkNode.left;
        }

        return root;
    }

}
