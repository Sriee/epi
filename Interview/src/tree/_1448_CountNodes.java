package tree;

/**
 * Asked in Microsoft Online Assessment June 2021.
 */
public class _1448_CountNodes {
    private int calcNode(TreeNode node, int max) {
        if(node == null)
            return 0;

        /*
         * We made a mistake of initializing 'count' variable globally and doing the below steps
         *
         * What was the mistake?
         *   At each step the count is calculated, so when the recursion ends 'count' already holds
         *   the information for a traversed path.
         *
         *   If you are using global variable, end of the recursion path would have the result
         *   If not initialize a variable at a level i, aggregate the results from level i+1's
         *   and pass it to level i-1.
         */
        int count = (node.val >= max) ? 1 : 0;

        count += calcNode(node.left, Math.max(node.val, max));
        count += calcNode(node.right, Math.max(node.val, max));
        return count;
    }

    public int goodNodes(TreeNode root) {
        return calcNode(root, Integer.MIN_VALUE);
    }
}
