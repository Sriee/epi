package heaps;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class _373_KSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*
         * Declare priority queue variable as minHeap or maxHeap instead of using generic names
         * like 'queue' or 'pq'. It is clearer and follows good naming conventions practice.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        List<List<Integer>> result = new LinkedList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return result;

        for (int i = 0; i < nums1.length && i < k; i++)
            minHeap.offer(new int[] { nums1[i], nums2[0], 0 });

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] top = minHeap.poll();

            result.add(Arrays.asList(top[0], top[1]));

            if (top[2] == nums2.length - 1)
                continue;

            minHeap.offer(new int[] { top[0], nums2[top[2] + 1], top[2] + 1 });
        }

        return result;
    }

    public static void main(String[] args) {
        _373_KSmallestPairs ksp = new _373_KSmallestPairs();

        int[][] nums1 = {
                {1, 7, 11},
                {1, 1, 2},
                {1, 2}
        };
        int[][] nums2 = {
                {2, 4, 6},
                {1, 2, 3},
                {3}
        };
        int[] ks = {3, 2, 3};

        for (int i = 0; i < ks.length; i++) {
            List<List<Integer>> res = ksp.kSmallestPairs(nums1[i], nums2[i], ks[i]);
            System.out.println(res);
        }
    }
}
