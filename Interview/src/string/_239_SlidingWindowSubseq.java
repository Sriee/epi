package string;

import java.util.*;

public class _239_SlidingWindowSubseq {

    /**
     * Sliding Window pattern
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.removeLast();
            }

            queue.offerLast(i);
        }

        res[idx++] = nums[queue.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.removeLast();
            }

            queue.offerLast(i);

            while (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.removeFirst();
            }

            res[idx++] = nums[queue.peekFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        _239_SlidingWindowSubseq sws = new _239_SlidingWindowSubseq();
        int[][] inputs = {
            {1, 3, -1, -3, 5, 3, 6, 7},
            {1},
            {7, 2, 4},
            {1, -1}
        };
        int[] ks = {3, 1, 2, 1};

        for (int i = 0; i < ks.length; i++)
            System.out.println(Arrays.toString(sws.maxSlidingWindow(inputs[i], ks[i])));
    }
}
