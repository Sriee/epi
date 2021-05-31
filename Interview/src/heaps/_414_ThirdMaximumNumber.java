package heaps;

import java.util.*;

class _414_ThirdMaximumNumber {

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = nums[0];

        for (int i : nums) {
            if (pq.contains(i))
                continue;

            pq.offer(i);

            if (pq.size() > 3)
                pq.poll();
        }

        if (pq.size() < 3) {
            while (!pq.isEmpty())
                res = pq.poll();
        } else {
            res = pq.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        _414_ThirdMaximumNumber tmn = new _414_ThirdMaximumNumber();
        int[][] inputs = {
                {17, 18, 5, 4, 6, 1},
                {33, 44, 11, 9, 5, 1}
        };

        for (int[] input : inputs) {
            System.out.println(tmn.thirdMax(input));
        }
    }
}