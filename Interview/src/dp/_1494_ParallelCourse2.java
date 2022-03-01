package dp;

import util.PrintHypens;

import java.util.*;

public class _1494_ParallelCourse2 {

    // Using globals to avoid passing these values are parameters.
    int[] inDegree, mem;
    int n, k;

    /**
     * Topological sort won't work for this problem. Try out the first example.
     * <p>
     * The solution is to use Dynamic Programming.
     * <p>
     * TC: O(n^2 x 2^n)
     * SC: O(2^n x n)
     */
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        // Initialize globals
        this.inDegree = new int[n];
        this.n = n;
        this.k = k;

        // For memoization in DP. use array instead of map to improve time and space complexity.
        this.mem = new int[1 << n];
        Arrays.fill(mem, -1);

        // Calculate mask
        int mask = 0;
        for (int i = 0; i < n; i++) {
            mask |= (1 << i);
        }

        /*
         * Use a bit-masking technique to represent inDegree
         * Since we need to know the courses that we take in each iteration, we are using a HaspMap here.
         *
        Map<Integer, List<Integer>> inDegree = new HashMap<>();
        for (int[] relation: relations) {
            List<Integer> deps = inDegree.getOrDefault(relation[1], new ArrayList<>());
            deps.add(relation[0]);
            inDegree.put(relation[1], deps);
        }
        */

        /*
         * Use a bit-masking technique to represent inDegree. Take note of two things.
         *
         * 1. The courses are now 0-indexed instead of 1-indexed.
         * 2. The pre-requisites are marked as either 0 & 1 from the right most bit.
         *
         * For example, [2, 1] relation will be
         * inDegree[0] = inDegree[0] | inDegree[1]; 0000 -> 0010
         *  Bit mask = 0 0 1 0
         *    course = 3 2 1 0
         */
        for (int[] relation : relations) {
            this.inDegree[relation[1] - 1] |= (1 << relation[0] - 1);
        }

        return dp(mask);
    }

    private int dp(int currentMask) {
        if (currentMask == 0) {
            return currentMask;
        } else if (mem[currentMask] != -1) {
            return mem[currentMask];
        }

        // Select courses for current semester. i.e. courses with 0 inDegree's
        List<Integer> coursesForCurrentSemester = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int courseMask = (1 << i);

            // Course not already taken && it's inDegree should be 0
            if ((courseMask & currentMask) != 0 && (inDegree[i] & currentMask) == 0) {
                coursesForCurrentSemester.add(i);
            }
        }

        if (coursesForCurrentSemester.size() > k) {
            int ans = Integer.MAX_VALUE;
            List<Integer> maskCombinations = new ArrayList<>();

            getMaskCombinations(maskCombinations, coursesForCurrentSemester, currentMask, this.k, 0);
            for (int combination : maskCombinations) {
                ans = Math.min(ans, 1 + dp(combination));
            }
            mem[currentMask] = ans;
        } else {
            // Mark courses as taken. 1 -> 0
            int newMask = currentMask;
            for (int course : coursesForCurrentSemester) {
                newMask ^= (1 << course);
            }

            mem[currentMask] = 1 + dp(newMask);
        }

        return mem[currentMask];
    }

    /**
     * Generate mask combinations from the list of courses.
     * <p>
     * For example, [5, 7, 8, 10], the combinations will be the following for k = 3
     * [
     * 111001011111, [5, 7, 8]
     * 101101011111, [5, 7, 10]
     * 101011011111, [5, 8, 10]
     * 101001111111  [7, 8, 10]
     * ]
     */
    private void getMaskCombinations(List<Integer> res, List<Integer> coursesForCurrentSemester, int mask, int k, int idx) {
        if (k == 0) {
            res.add(mask);
            return;
        }

        for (int i = idx; i < coursesForCurrentSemester.size(); i++) {
            int nextMask = mask ^ (1 << coursesForCurrentSemester.get(i));
            // System.out.printf("%s -> %s\n", Integer.toBinaryString(mask), Integer.toBinaryString(nextMask));
            getMaskCombinations(res, coursesForCurrentSemester, nextMask, k - 1, i + 1);
        }
    }

    public static void main(String[] args) {
        _1494_ParallelCourse2 pc2 = new _1494_ParallelCourse2();

        int[] ns = {12, 8, 4, 5};
        int[] ks = {3, 3, 2, 2};
        int[][][] prerequisites = {
                {{11, 10}, {6, 3}, {2, 5}, {9, 2}, {4, 12}, {8, 7}, {9, 5}, {6, 2}, {7, 2}, {7, 4}, {9, 3}, {11, 1}, {4, 3}},
                {{2, 7}, {1, 6}, {2, 8}, {8, 7}, {6, 7}, {5, 4}, {1, 7}, {1, 2}, {1, 4}, {2, 6}},
                {{2, 1}, {3, 1}, {1, 4}},
                {{2, 1}, {3, 1}, {4, 1}, {1, 5}}
        };

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("%d.\tn=%d, k=%d, prerequisites=%s\n", (i + 1), ns[i], ks[i], Arrays.deepToString(prerequisites[i]));
            System.out.println("\tMinimum semesters to take all courses= " + pc2.minNumberOfSemesters(ns[i], prerequisites[i], ks[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}
