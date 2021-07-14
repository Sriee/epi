package bst;

import java.util.*;

class _501_FindMode {
    /**
     * Naive Approach: Write the contents of BST to a map and find out mode
     */
    Map<Integer, Integer> map = new HashMap<>();
    int maxCount = 0;

    public int[] findMode(BSTNode root) {
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

    public static void main(String[] args) {
        _501_FindMode fm = new _501_FindMode();
        BSTNode root = new BSTNode(1);
        root.right = new BSTNode(2);
        root.right.left = new BSTNode(2);

        int[] res = fm.findMode(root);
        System.out.println(Arrays.toString(res));
    }
}