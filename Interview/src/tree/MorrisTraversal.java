package tree;

import java.util.*;

class MorrisTraversal {
    /**
     * Algorithm for Morris Traversal
     * <p>
     * 1. Initialize current as root
     * 2. While current is not NULL
     * If current does not have a left child
     * i. Print current’s data
     * ii. Go to the right. Since we have visited all the nodes in the left subtree.
     * Else
     * i. Find the right most node in current's left subtree. Make current the right child of the rightmost
     * node
     * ii. If there is no right child
     * - Attach current as the right child (Note: We are creating a cycle)
     * Else
     * - Detach the successor and traverse left
     * <p>
     * How does this work without stack?
     * The reason for using a stack is to store a node that we can backtrack to when our left exploration has ended.
     * <p>
     * As we go through a parent node to which we will need to backtrack to -> we find the node which we will need to
     * backtrack from and create a link to the parent node.
     * <p>
     * When we backtrack? When we cannot go further. When we cannot go further? When no left child's present.
     * <p>
     * Where we backtrack to? Notice: to SUCCESSOR!
     * <p>
     * So, as we follow nodes along left-child path, set the predecessor at each step to point to the current node.
     * This way, the predecessors will have links to successors (a link for backtracking).
     * <p>
     * We follow left while we can until we need to backtrack. When we need to backtrack, we print the current node and
     * follow the right link to the successor.
     * <p>
     * If we have just backtracked -> we need to follow the right child (we are done with left child).
     * <p>
     * How to tell whether we have just backtracked? Get the predecessor of the current node and check if it has a right
     * link (to this node). If it has - than we followed it. remove the link to restore the tree.
     * <p>
     * If there was no left link => we did not backtrack and should proceed following left children.
     */
    public List<Integer> morrisTraversal(TreeNode root) {
        TreeNode curr = root, pred;
        List<Integer> lst = new ArrayList<>();

        while (curr != null) {
            if (curr.left == null) {
                lst.add(curr.val);
                curr = curr.right;
            } else {
                pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    lst.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return lst;
    }

    public static void main(String[] args) {
        MorrisTraversal mt = new MorrisTraversal();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> lst = mt.morrisTraversal(root);
        System.out.println(lst);
    }
}