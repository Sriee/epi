package tree;

import java.util.Arrays;

/**
 * This problem illustrates a concept called "Binary Lifting".
 */
public class _1483_KthAncestor {
    private final int log;      // Height of the tree
    private final int[][] up;   // 2^jth ancestor for each i

    public _1483_KthAncestor(int n, int[] parent) {
        this.log = (int) (Math.log(n) / Math.log(2));
        this.up = new int[n][log + 1];
        this.preProcess(n, parent);
    }

    private void preProcess(int n, int[] parent) {
        // Initialize up
        for (int i = 0; i < n; i++)
            Arrays.fill(up[i], -1);

        /*
         * Populate 'up' table.
         *
         * The following way of populating up table only
         * works when parent[i] < i. It fails for the following case
         *
         *   0
         *    \
         *     3
         *    /
         *   2
         *
         * because we will be looking up parent for not yet filled node. For instance,
         * while filling up up[2][1] we will be looking up up[3][0].
         *
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];

            for (int j = 1; j <= log; j++) {
                if (up[i][j - 1] == -1)
                    break;

                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        *
        * To solve this problem, we will be filling n nodes for each level
        */
        // 2^0
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];
        }

        // 2^j, j = 1 to log
        for (int j = 1; j <= log; j++) {
            for (int i = 0; i < n; i++) {
                int p = up[i][j - 1];

                // Since we are visiting all the array elements
                // we can fill -1 for nodes without a parent
                up[i][j] = p == -1 ? -1 : up[p][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int j = log; j >= 0; j--) {
            if (k >= (1 << j)) {
                node = up[node][j];

                if (node == -1) break;
                k -= 1 << j;
            }
        }
        return node;
    }

    // Print 'up'
    private void printUp() {
        for (int i = 0; i < up.length; i++)
            System.out.println(i + " => " + Arrays.toString(up[i]));
    }

    public static void main(String[] args) {
        int n = 13;
        int[] parents = {-1, 0, 0, 1, 1, 2, 2, 3, 4, 4, 5, 7, 10};
        _1483_KthAncestor ka = new _1483_KthAncestor(n, parents);
        ka.printUp();

        System.out.println(ka.getKthAncestor(9, 3));
    }
}
