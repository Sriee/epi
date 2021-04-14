

/**
 * Did not solve.
 * <p>
 * Recursive Approach - TLE Since we are considering all combination. Intuition of using combination is right but for
 * numbers > 10000 the stack is overflowing. Memoization does not help either.
 */
public class _279_PerfectSquares {

    /**
     * BFS Approach: Trick is to identify the relation ship between current element to next element.
     */
    private int numSquares(int n) {
        int max = (int) Math.sqrt(n), curr, next, step = 0;
        int[] sq = new int[max + 1];

        for (int i = 1; i <= max; i++)
            sq[i] = i * i;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;

            for (int k = 0; k < size; k++) {
                curr = queue.poll();

                for (int i = 1; i <= max; i++) {
                    next = curr + sq[i];

                    if (next > n)
                        continue;
                    else if (next == n)
                        return step;
                    else
                        queue.offer(next);
                }
            }
        }

        return step;
    }

    public static void main(String[] args) {
        _279_PerfectSquares ps = new _279_PerfectSquares();
        int[] nums = {19, 169, 5172, 9999};
        int[] expected = {3, 1, 3, 4};

        // To enable assertions in a java, add "-ea" flag to javac compiler
        for (int i = 0; i < nums.length; i++) {
            assert ps.numSquares(nums[i]) == expected[i] : String.format("Assertion check failed for i = %d", i);
        }
    }
}
