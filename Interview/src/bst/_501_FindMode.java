package bst;

import java.util.*;

class _501_FindMode {
    Random rand = new Random();

    /**
     * Naive Approach: Write the contents of BST to a map and find out mode
     */
    Map<Integer, Integer> map = new HashMap<>();
    int maxCount = 0;

    public int[] naiveApproach(BSTNode root) {
        preOrder(root);

        int num = 0, i = 0;
        for (int node : map.keySet()) {
            if (map.get(node) == maxCount)
                num++;
        }

        int[] res = new int[num];
        for (int node : map.keySet()) {
            if (map.get(node) == maxCount)
                res[i++] = node;
        }

        return res;
    }

    private void preOrder(BSTNode node) {
        if (node == null)
            return;

        int val = 0;
        if (map.containsKey(node.val))
            val = map.get(node.val);

        val++;
        maxCount = Math.max(maxCount, val);
        map.put(node.val, val);

        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Inorder Approach - Technique 2
     * <p>
     * We make use of a prev pointer that keeps track of previous node in an inorder traversal.
     */
    Deque<Integer> modes;
    int count = 1;
    BSTNode prev = null;

    public int[] inorderApproach(BSTNode root) {
        modes = new ArrayDeque<>();
        inorder(root);

        int[] res = new int[modes.size()];
        int i = 0;

        for (int m : modes)
            res[i++] = m;

        return res;
    }

    private void inorder(BSTNode node) {
        if (node == null)
            return;

        inorder(node.left);

        if (prev != null) {
            count = prev.val == node.val ? count + 1 : 1;
        }

        if (count > maxCount) {
            maxCount = count;
            // Emptying the container is faster compared to creating a new one
            modes.clear();
            modes.offerLast(node.val);
        } else if (count == maxCount) {
            modes.offerLast(node.val);
        }

        prev = node;
        inorder(node.right);
    }

    public int[] findMode(BSTNode root) {
        int[] res = null;
        switch (rand.nextInt(2)) {
            case 0:
                System.out.println("Naive Approach");
                res = naiveApproach(root);
                break;
            case 1:
                System.out.println("Inorder Approach");
                res = inorderApproach(root);
                break;
        }

        return res;
    }

    public static void main(String[] args) {
        _501_FindMode fm = new _501_FindMode();
        BSTNode root = new BSTNode(1);
        root.right = new BSTNode(2);
        root.right.left = new BSTNode(2);

        int[] res = fm.findMode(root);
        System.out.println(Arrays.toString(res));
    }
}