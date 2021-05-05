package binary_search;

/**
 * Binary Search Template 2
 */
public class _162_PeakElement {
    int[] n;

    private boolean isPeak(int idx) {
        boolean prev, next;

        if (idx - 1 < 0)
            prev = true;
        else
            prev = n[idx] > n[idx - 1];

        if (idx + 1 >= n.length)
            next = true;
        else
            next = n[idx] > n[idx + 1];

        return prev && next;
    }

    public int findPeakElement(int[] nums) {
        this.n = nums;
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isPeak(mid))
                return mid;
            else if (nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }


    public static void main(String[] args) {
        _162_PeakElement pe = new _162_PeakElement();
        int[][] input = {
                {1, 2, 1, 3, 5, 6, 7},
                {1, 2, 3, 4},
                {8, 1, 2, 3, -1}
        };

        for (int[] inp : input) {
            System.out.println(pe.findPeakElement(inp));
        }
    }
}
