package heaps;

import java.util.*;

public class _1046_LastStoneWeight {
    /**
     * TC = O(n log n) where n is the number of stones
     * SC = O(n)
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones)
            maxHeap.offer(stone);

        while (maxHeap.size() > 1) {
            int x = maxHeap.poll();
            int y = maxHeap.poll();

            if (x != y)
                maxHeap.offer(x - y);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        _1046_LastStoneWeight lsw = new _1046_LastStoneWeight();

        int[][] inp = {
                {6, 71, 25, 38, 17, 23, 0, 19, 3, 46, 27, 3, 48, 1, 2},
                {2, 7, 4, 1, 8, 1},
                {1}
        };

        for (int[] entry : inp) {
            System.out.println(lsw.lastStoneWeight(entry));
        }
    }
}
