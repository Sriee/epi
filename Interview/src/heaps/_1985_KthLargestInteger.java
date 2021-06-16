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

    /**
     * Approach 2: Sort
     * <p>
     * Ironically, for this problem OJ lists this approach as the fastest one.
     * TC: O(n log n)
     * SC: O(log n)
     */
    public String kthLargestNumber2(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> a.length() == b.length() ?
                b.compareTo(a) : Integer.compare(b.length(), a.length())
        );
        return nums[k - 1];
    }

    Random rand = new Random();

    /**
     * Approach 3: Quick Select approach
     * <p>
     * TC: O(n) - Average case. O(n ^ 2) worst case. Since we are choosing the pivot index in a random, possibility for
     * worst case TC is drastically reduced.
     * SC: O(1)
     */
    public String kthLargestNumber3(String[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private String quickSelect(String[] nums, int left, int right, int nk) {
        while (left < right) {
            int partitionPos = partition(nums, left, right);

            if (partitionPos < nk)
                left = partitionPos + 1;
            else if (partitionPos > nk)
                right = partitionPos - 1;
            else
                return nums[partitionPos];
        }

        return nums[left];
    }

    private int partition(String[] nums, int left, int right) {
        int pivotIdx = left + rand.nextInt(right - left), j = left;
        swap(nums, pivotIdx, right);
        pivotIdx = right;

        for (int i = left; i <= right; i++) {
            if (compare(nums[i], nums[pivotIdx])) {
                swap(nums, i, j);
                j++;
            }
        }

        swap(nums, right, j);
        return j;
    }

    private boolean compare(String a, String b) {
        int aL = a.length(), bL = b.length();
        return (aL == bL ? a.compareTo(b) : Integer.compare(aL, bL)) < 0;
    }

    private void swap(String[] nums, int first, int second) {
        String temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public static void main(String[] args) {
        _1985_KthLargestInteger kli = new _1985_KthLargestInteger();
        String[] nums;

        nums = new String[]{"3", "6", "7", "10"};
        System.out.println(kli.kthLargestNumber3(nums, 4));

        nums = new String[]{"2", "21", "12", "1"};
        System.out.println(kli.kthLargestNumber2(nums, 3));

        nums = new String[]{"0", "0"};
        System.out.println(kli.kthLargestNumber(nums, 1));
    }
}
