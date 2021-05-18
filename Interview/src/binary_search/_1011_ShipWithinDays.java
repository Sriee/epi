package binary_search;

public class _1011_ShipWithinDays {

    /**
     * Binary Search Template 3
     * <p>
     * TC: O(n) + O(n log w) w = [max(weights), sum(weights)]
     * SC: O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;

        /*
         * O(n) -> To find out the search space
         *
         * Note: The optimal solution skips this O(n) scan to find the search space. Instead it keeps left = 1 and
         * right = 500 * (weights.length / days + 1) which works but in practical scenarios we wouldn't know the
         * upper bound for weights.
         */
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        // O(log w)
        while (left < right) {
            int mid = left + (right - left) / 2;
            // O(n)
            int numDays = findNumDays(weights, mid);

            if (numDays > days) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int findNumDays(int[] nums, int guess) {
        int daysCount = 1, sumWeight = 0;

        for (int num : nums) {
            if (sumWeight + num > guess) {
                daysCount++;
                sumWeight = 0;
            }

            sumWeight += num;
        }

        return daysCount;
    }

    public static void main(String[] args) {
        _1011_ShipWithinDays swd = new _1011_ShipWithinDays();
        int[][] inputs = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 2, 2, 4, 1, 4},
                {1, 2, 3, 1, 1}
        };
        int[] days = {5, 3, 4};
        for (int i = 0; i < days.length; i++) {
            System.out.println(swd.shipWithinDays(inputs[i], days[i]));
        }
    }
}
