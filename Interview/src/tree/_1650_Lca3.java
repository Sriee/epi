package tree;

import java.util.*;

public class _1650_Lca3 {
    private static class Node {
        int val;
        Node left, right, parent;

        public Node(int _val) {
            this.val = _val;
        }

        @Override
        public String toString() {
            return String.format("Node(%d, l=%s, r=%s, p=%s)",
                    this.val,
                    this.left == null ? null : this.left.val,
                    this.right == null ? null : this.right.val,
                    this.parent == null ? null : this.parent.val
            );
        }
    }

    private Node construct(Integer[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        return construct(arr, 0);
    }

    private Node construct(Integer[] arr, int index) {
        if (index >= arr.length || arr[index] == null)
            return null;

        Node node = new Node(arr[index]);
        node.left = construct(arr, 2 * index + 1);
        node.right = construct(arr, 2 * index + 2);

        if (node.left != null) node.left.parent = node;
        if (node.right != null) node.right.parent = node;

        return node;
    }

    private List<Node> preOrder(Node root) {
        List<Node> tree = new ArrayList<>();

        if (root != null)
            preOrderRecursive(root, tree);
        return tree;
    }

    private void preOrderRecursive(Node root, List<Node> tree) {
        if (root == null)
            return;

        tree.add(root);
        preOrderRecursive(root.left, tree);
        preOrderRecursive(root.right, tree);
    }

    /**
     * Approach 1: Storing the parents of node p as we traverse from p to root.
     * <p>
     * TC: O(n)
     * SC: O(h) where h = height of the tree
     */
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> parents = new HashSet<>();

        Node curr = p;
        while (curr != null) {
            parents.add(curr);
            curr = curr.parent;
        }

        if (parents.contains(q))
            return q;

        curr = q;
        while (curr != null) {
            if (parents.contains(curr))
                break;

            curr = curr.parent;
        }

        return curr;
    }

    public Node lowestCommonAncestor2(Node p, Node q) {
        int left = lengthFromNodeToRoot(p);
        int right = lengthFromNodeToRoot(q);
        int min = Math.min(left, right);

        while (left > min) {
            p = p.parent;
            left--;
        }

        while (right > min) {
            q = q.parent;
            right--;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }

    private int lengthFromNodeToRoot(Node node) {
        int count = 0;
        Node curr = node;

        while (curr != null) {
            count++;
            curr = curr.parent;
        }

        return count;
    }

    public static void main(String[] args) {
        _1650_Lca3 lc = new _1650_Lca3();

        // Tree
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        Node root = lc.construct(arr), ancestor, a, b;
        List<Node> tree = lc.preOrder(root);
        System.out.println(tree);

        // TC 1
        a = root.right; // 1
        b = root.left.right; // 2
        ancestor = lc.lowestCommonAncestor(a, b);
        System.out.println(ancestor.val);

        // TC 2
        a = root.left.right; // 2
        b = root.right; // 5
        ancestor = lc.lowestCommonAncestor2(a, b);
        System.out.println(ancestor.val);
    }
}
