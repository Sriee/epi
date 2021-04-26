package tree;

import java.util.*;

public class _199_RightSideView {

    /**
     * DFS Approach.
     */
    public List<Integer> rightSideDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        fillRightSide(root, result, 0);
        return result;
    }

    private void fillRightSide(TreeNode root, List<Integer> result, int depth) {
        if (root == null)
            return;

        // Add element to the result set only when we discover a new level
        if (depth == result.size())
            result.add(root.val);

        // Traverse right first so that the right most node will be visited first
        // at each level
        fillRightSide(root.right, result, depth + 1);
        fillRightSide(root.left, result, depth + 1);
    }

    public List<Integer> rightSideBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null)
            return result;

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 1; i <= size; i++) {
                TreeNode node = queue.poll();

                if (i == size) {
                    result.add(node.val);
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        Random rand = new Random();
        return rand.nextInt(2) % 2 == 0 ? rightSideDFS(root) : rightSideBFS(root);
    }

    public static void main(String[] args) {
        _199_RightSideView rsv = new _199_RightSideView();

        // [43,23,47,null,37,null,53,29,41,null,null,null,31]
        TreeNode root = new TreeNode(43);
        root.left = new TreeNode(23);
        root.right = new TreeNode(47);
        root.left.right = new TreeNode(37);
        root.right.right = new TreeNode(53);
        root.left.right.left = new TreeNode(29);
        root.left.right.right = new TreeNode(41);
        root.left.right.left.right = new TreeNode(31);

        List<Integer> res = rsv.rightSideView(root);
        System.out.println(res);
    }
}
