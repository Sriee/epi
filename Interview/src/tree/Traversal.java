package tree;

import java.util.List;
import java.util.Arrays;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Traversal {

    /**
     * Binary Tree Level Order Traversal Leet code. Solution -> Accepted
     * 
     * @param root Root of the tree
     * @return List of nodes for each level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> innerList = new LinkedList<>();

        int current = 1, next = 0;
        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode node = queue.remove();
            current--;
            if (node != null) {
                innerList.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
                next += 2;
            }

            if (current == 0) {
                if (!innerList.isEmpty()) {
                    result.add(innerList);
                }
                innerList = new LinkedList<>();
                current = next;
                next = 0;
            }
        }
        return result;
    }

    /**
     * Binary Tree Zig Zag Level Order Traversal Leet code. Solution -> Accepted
     * 
     * @param root Root of the tree
     * @return List of nodes for each level (zig-zag order)
     */
    public List<List<Integer>> zigZag(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> innerList = new LinkedList<>();
        int current = 1, next = 0, dir = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            current--;

            if (node != null) {
                if (dir % 2 == 0) {
                    innerList.add(node.val);
                } else {
                    innerList.add(0, node.val);
                }
                queue.add(node.left);
                queue.add(node.right);
                next += 2;
            }

            if (current == 0) {
                if (!innerList.isEmpty()) {
                    result.add(innerList);
                    innerList = new LinkedList<>();
                }
                current = next;
                next = 0;
                dir++;
            }
        }
        return result;
    }

    /**
     * Binary Tree Level Order Traversal (Recursive implementation) Leet code.
     * Solution -> Accepted Runtime: 1ms
     * 
     * @param root Root of the tree
     * @return List of nodes for each level
     */
    private void levelTraversal(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (res.size() == depth)
            res.add(new LinkedList<>());

        res.get(depth).add(root.val);
        levelTraversal(root.left, depth + 1, res);
        levelTraversal(root.right, depth + 1, res);
    }

    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        levelTraversal(root, 0, result);
        return result;
    }

    /**
     * Diameter of a tree Diameter is defined as the maximum distance between two
     * leaf nodes. Multiple maximum distances are possible Diameter doesn't have to
     * pass through the root node Leet code. Solution -> Accepted
     *
     * @param root root node of a tree
     * @return diameter of the tree
     */
    public int diameter(TreeNode root) {
        this.ans = 1;
        this.calculateDiameter(root);
        return this.ans - 1;
    }

    public int ans; // Global variable to hold the diameter

    /**
     * Helper method to calculate diameter of the tree
     *
     * @param node current traversed node
     * @return height at the traversed node
     */
    public int calculateDiameter(TreeNode node) {
        if (node == null)
            return 0;

        int leftSubTree = this.calculateDiameter(node.left); // Height of the left sub tree
        int rightSubTree = this.calculateDiameter(node.right); // Height of the right sub tree

        // Height of a sub tree will tell us the furthest leaf node. We add 1 as we
        // consider this node
        // Sum of the them would give us the diameter at this node. We take the max
        // diameter that we know so far
        this.ans = Math.max(ans, leftSubTree + rightSubTree + 1);

        return 1 + Math.max(leftSubTree, rightSubTree);
    }

    /**
     * Calculate height of a Binary Tree
     * 
     * @param root Root of the tree
     * @return height of the tree
     */
    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(this.height(root.left), this.height(root.right));
    }

    /**
     * Calculate minimum depth of a tree Note: - For [1, 2, null] the depth is 2 not
     * 1, since there is a left child for node:1 - For [1] the depth is 1 There is a
     * difference between height and depth. We calculate the height based on the
     * number of edges from root to leaf node where as depth is based on the levels
     * of the tree. We start depth from level 1. Leet code. Solution -> Accepted
     *
     * @param root root node of a tree
     * @return minimum depth of a tree
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null)
            return 1 + this.minDepth(root.right);

        if (root.right == null)
            return 1 + this.minDepth(root.left);

        int leftHeight = this.minDepth(root.left);
        int rightHeight = this.minDepth(root.right);

        return 1 + Math.min(leftHeight, rightHeight);
    }

    /**
     * Print Binary Tree on the console
     * 
     * @param elements should be of format List<List<T>>
     */
    public void print(List<List<Integer>> elements) {
        System.out.print("[");
        for (List<Integer> inner : elements) {
            for (Integer val : inner) {
                System.out.print(val + " ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Traversal s = new Traversal();
    }
}
