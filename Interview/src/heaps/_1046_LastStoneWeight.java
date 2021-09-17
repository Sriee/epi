package heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _1046_LastStoneWeight {
    /**
     * Leet code problem. Solution -> Accepted Each turn, choose the two heaviest
     * rocks If x == y, it cancels out each other; If x != y, the stone of weight x
     * is totally destroyed, and the stone of weight y has new weight y-x. At the
     * end, there is at most 1 stone left. Return the weight of the stone (or 0 if
     * there are no stones left.)
     *
     * @param stones array of weights
     * @return the weight of the stone
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return j.compareTo(i);
            }
        });

        for (int i : stones)
            queue.add(i);

        while (queue.size() > 1) {
            int x = queue.poll();
            int y = queue.poll();

            if (x != y)
                queue.add(x - y);

        }

        return queue.isEmpty() ? 0 : queue.peek();
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
