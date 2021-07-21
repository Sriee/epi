package bst;

import java.util.*;

class _1902_DepthBST {
    /**
     * Naive Approach is to construct the BST and then find Depth. This attempt threw a TLE for large input case.
     * <p>
     * Let's say we've constructed the tree with [6, 2, 1, 8] so far.  The tree would look something like below:
     *      6 (Depth =0)
     *    /  \
     * (1) 2    8 (1)
     * /
     * (2) 1
     * <p>
     * Next we are trying to insert 4 into the tree. In terms of order, 4 is between 2 and 6. They are more likely to
     * become parents of node 4. So, for finding the neighbors of 4, we use ceil and floor methods of TreeMap. We then
     * choose the node at max depth (Node 2).
     * 6 (Depth =0)
     * /  \
     * (1) 2    8 (1)
     * /  \
     * (2) 1    4 (2)
     * <p>
     * Note the use of TreeMap for constructing a height balanced tree. Without the use of TreeMap the run time of
     * this approach would also degrade to O(n^2).
     */
    public int maxDepthBST(int[] order) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxDepth = 0;

        for (int i : order) {
            Map.Entry<Integer, Integer> lower = map.floorEntry(i), upper = map.ceilingEntry(i);
            int newDepth = 1 + Math.max(lower == null ? 0 : lower.getValue(),
                    upper == null ? 0 : upper.getValue()
            );
            maxDepth = Math.max(maxDepth, newDepth);
            map.put(i, newDepth);
        }

        return maxDepth;
    }

    public static void main(String[] args) {
        _1902_DepthBST db = new _1902_DepthBST();
        int[][] input = {
                {2, 1, 4, 3},
                {1, 2, 3, 4}
        };

        for (int[] nums : input)
            System.out.println(db.maxDepthBST(nums));
    }
}