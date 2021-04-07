package tree;
import java.util.*;

/**
 * Asked in Microsoft OA 2019
 */
public class _257_BTPaths {
    List<String> output = new ArrayList<>();

    private void inOrder(TreeNode node, StringBuilder sb) {
        if(node == null) return;

        sb.append(node.val).append("->");
        if(node.left == null && node.right == null) {
            sb.delete(sb.length() - 2, sb.length());
            output.add(sb.toString());
        } else {
            inOrder(node.left, new StringBuilder(sb));
            inOrder(node.right, new StringBuilder(sb));
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        inOrder(root, new StringBuilder());
        return output;
    }

    public static void main(String[] args) {
        _257_BTPaths btp = new _257_BTPaths();
        List<String> out;

        TreeNode root = new TreeNode(1);
        out = btp.binaryTreePaths(root);
        System.out.println(out);

        btp.output.clear();
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        out = btp.binaryTreePaths(root);
        System.out.println(out);
    }
}
