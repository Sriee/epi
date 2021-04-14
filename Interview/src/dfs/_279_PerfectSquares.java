package dfs;

import java.util.*;

/**
 * Did not solve. TLE. Memoization does not help. The following recursive solution won't work. Because the number of
 * steps computed is not optimal
 * <p>
 * Example: For n = 269
 * <p>
 * Optimal steps = 2; 100 + 169
 * Our solution returns = 3; 256 + 9 + 4
 */
public class _279_PerfectSquares {
    int[] squares;
    int answer = Integer.MAX_VALUE;
    Map<Integer, Integer> map = new HashMap<>();

    public int numSquares(int n) {
        int max = (int) Math.sqrt(n);
        squares = new int[max + 1];

        for (int i = 1; i <= max; i++)
            squares[i] = i * i;

        System.out.println(Arrays.toString(squares) + "\n");
        // dfs(n, 0, 0);
        return answer;
    }

    private void dfs(int n, int soFar, int steps) {
        if (soFar == n) {
            answer = Math.min(answer, steps);
            return;
        }

        for (int i = squares.length - 1; i > 0; i--) {
            int curr = soFar + squares[i];
            if (curr > n)
                continue;

            int rest = n - curr;
            if (map.containsKey(rest)) {
                System.out.println("Am I " + curr + " " + map.get(rest));
                answer = Math.min(answer, steps + map.get(rest));
            } else {
                System.out.println("Else " + curr + " " + (steps + 1));
                map.put(curr, steps + 1);
                dfs(n, curr, steps + 1);
            }

            if (answer != Integer.MAX_VALUE)
                break;
        }
    }

    public static void main(String[] args) {
        _279_PerfectSquares ps = new _279_PerfectSquares();

        System.out.println(ps.numSquares(269));
    }
}
