package binary_search;

public class _875_Koko {

    /**
     * Binary Search Template 3
     * <p>
     * TC: O(n) + O(n log w) where w = [1, max(piles)]
     * SC: O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;

        // O(n)
        for (int p : piles)
            right = Math.max(right, p);

        // O(log w)
        while (left < right) {
            int mid = left + (right - left) / 2;

            // O(n)
            if (feasible(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean feasible(int[] piles, int hours, int mid) {
        int time = 0;

        for (int p : piles) {
            time += ((p / mid) + (p % mid == 0 ? 0 : 1));
        }

        return time <= hours;
    }

    public static void main(String[] args) {
        _875_Koko ko = new _875_Koko();
        System.out.println(ko.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }
}
