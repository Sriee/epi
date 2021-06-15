package heaps;

import java.util.*;

public class _1985_KthLargestInteger {

    /**
     * Approach 1: Heap
     * <p>
     * TC: O(n log k)
     * SC: O(k)
     */
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> a.length() == b.length() ? a.compareTo(b) : Integer.compare(a.length(), b.length()));

        for (String s : nums) {
            pq.offer(s);

            if (pq.size() > k)
                pq.poll();
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        _1985_KthLargestInteger kli = new _1985_KthLargestInteger();
        String[] nums;
        int k;

        nums = new String[]{"3", "6", "7", "10"};
        System.out.println(kli.kthLargestNumber(nums, 4));

        nums = new String[]{"2", "21", "12", "1"};
        System.out.println(kli.kthLargestNumber(nums, 3));

        nums = new String[]{"0", "0"};
        System.out.println(kli.kthLargestNumber(nums, 1));
    }
}
