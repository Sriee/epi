package tree;

import java.util.*;

public class _199_RightSideView {

    /**
     * DFS Approach. Other approach do a level order traversal with DFS.
     * 1. The below approach asks for list of node that a node sees.
     * 2. Once it receives the elements after traversal, we then make a decision on which elements needs to be sent to
     * level N - 1.
     */
    public List<Integer> rightSideDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        result.add(root.val);
        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);

        int ls = left.size(), rs = right.size();

        if (ls == 0 || rs >= ls)
            result.addAll(right);
        else if (rs == 0)
            result.addAll(left);
        else {
            result.addAll(right);
            int start = ls - rs;
            result.addAll(left.subList(ls - start, ls));
        }

        return result;
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
}
