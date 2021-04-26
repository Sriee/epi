package tree;

import java.util.Arrays;

/**
 * This problem illustrates a concept called "Binary Lifting".
 * <p>
 * Note:
 * This solution works only works when parent[i] < i
 */
public class _1483_KthAncestor {
    private final int log, n;
    private final int[][] up;

    public _1483_KthAncestor(int n, int[] parent) {
        this.n = n;
        this.log = (int) (Math.log(n) / Math.log(2));
        this.up = new int[n][log + 1];
        this.preProcess(parent);
    }

    private void preProcess(int[] parent) {
        // Initialize up
        for (int i = 0; i < n; i++)
            Arrays.fill(up[i], -1);

        // Populate 'up' table
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];

            for (int j = 1; j <= log; j++) {
                if (up[i][j - 1] == -1)
                    break;

                up[i][j] = up[up[i][j - 1]][j - 1];
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
        for (int i = 0; i < n; i++)
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
