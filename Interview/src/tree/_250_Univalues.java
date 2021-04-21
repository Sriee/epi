package tree;

public class _250_Univalues {

    int ans = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;

        preOrder(root, root.val);
        return ans;
    }

    private boolean preOrder(TreeNode node, int val) {
        if (node == null)
            return true;

        boolean left = preOrder(node.left, node.val);
        boolean right = preOrder(node.right, node.val);

        if (left && right) {
            ans++;
            return node.val == val;
        }

        return false;
    }

    public static void main(String[] args) {
        _250_Univalues uv = new _250_Univalues();

        // [10,1,5,null,null,5,5,5,null]
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(5);
        System.out.println(uv.countUnivalSubtrees(root));

        // []
        System.out.println(uv.countUnivalSubtrees(null));

        // [1,2,3,null,null,null]
        uv.ans = 0;
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(uv.countUnivalSubtrees(root));

        // [5,1,5,5,5,null,5]
        uv.ans = 0;
        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(5);
        System.out.println(uv.countUnivalSubtrees(root));
    }
}
