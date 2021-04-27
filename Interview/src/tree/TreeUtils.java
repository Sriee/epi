package tree;

import java.util.*;

public class TreeUtils {
    Random rand = new Random();

    public TreeNode construct(Integer[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        return construct(arr, 0);
    }

    private TreeNode construct(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null)
            return null;

        TreeNode node = new TreeNode(arr[index]);
        node.left = construct(arr, 2 * index + 1);
        node.right = construct(arr, 2 * index + 2);

        return node;
    }

    /*
     * ==========================================================================
     * Inorder Traversal
     * ==========================================================================
     */
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> tree = new ArrayList<>();

        if (this.isRecursive()) {
            this.inOrderRecursive(root, tree);
        } else {
            this.inOrderIterative(root, tree);
        }

        return tree;
    }

    private void inOrderRecursive(TreeNode root, List<Integer> tree) {
        if (root == null)
            return;

        inOrderRecursive(root.left, tree);
        tree.add(root.val);
        inOrderRecursive(root.right, tree);
    }

    private void inOrderIterative(TreeNode root, List<Integer> tree) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            tree.add(curr.val);
            curr = curr.right;
        }
    }

    /*
     * ==========================================================================
     * Pre-order Traversal
     * ==========================================================================
     */
    private List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (this.isRecursive())
            preOrderRecursive(root, result);
        else
            preOrderIterative(root, result);

        return result;
    }

    private void preOrderRecursive(TreeNode root, List<Integer> result) {
        if (root == null)
            return;

        result.add(root.val);
        this.preOrderRecursive(root.left, result);
        this.preOrderRecursive(root.right, result);
    }

    private void preOrderIterative(TreeNode root, List<Integer> result) {
        if (root == null)
            return;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            result.add(node.val);

            if (node.right != null) queue.offerFirst(node.right);
            if (node.left != null) queue.offerFirst(node.left);
        }
    }

    private boolean isRecursive() {
        boolean rec = rand.nextInt(2) % 2 == 0;
        System.out.printf("Using %s Approach.", rec ? "Recursive" : "Iterative");
        return rec;
    }

    public static void main(String[] args) {
        TreeUtils util = new TreeUtils();

        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = util.construct(arr);
        List<Integer> tree = util.inOrder(root);
        System.out.println(tree);

        tree = util.preOrder(root);
        System.out.println(tree);
    }
}
