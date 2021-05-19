package binary_search;

public class _1482_MinDaysForMakingBouquets {

    /**
     * Binary Search Template 3
     * <p>
     * TC: O(n) + O(n log w) where w = [1, max(bloomDay)]
     * SC: O(1)
     */
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k)
            return -1;

        int left = 1, right = 0;

        for (int b : bloomDay) {
            right = Math.max(right, b);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (feasible(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean feasible(int[] bloomDay, int m, int k, int mid) {
        int bouquets = 0, flowers = 0;

        for (int b : bloomDay) {
            if (b > mid) {
                flowers = 0;
            } else if (++flowers >= k) {
                flowers = 0;
                bouquets++;
            }

            /*
             * In the previous approach we traverse the entire bloomDay array to find num of bouquets that can be
             * formed. But this is not required. We short circuit our search when we find out that we can make m
             * bouquets for given mid.
             *
             * Worst case: We will still be doing a O(n) search but
             * Average case: OJ reported a faster run time
             */
            if (bouquets == m)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        _1482_MinDaysForMakingBouquets mdb = new _1482_MinDaysForMakingBouquets();
        int[] arr;

        // 1
        arr = new int[]{1, 10, 3, 10, 2};
        System.out.println(mdb.minDays(arr, 3, 1)); // 3
        System.out.println(mdb.minDays(arr, 3, 2)); // -1

        // 2
        arr = new int[]{7, 7, 7, 7, 12, 7, 7};
        System.out.println(mdb.minDays(arr, 2, 3)); // 12

        // 3
        arr = new int[]{5, 37, 55, 92, 22, 52, 31, 62, 99, 64, 92, 53, 34, 84, 93, 50, 28};
        System.out.println(mdb.minDays(arr, 8, 2)); // 93
    }
}
