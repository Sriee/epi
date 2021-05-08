package binary_search;

public class _153_FindMinRotated {

    // Follows Binary Search Template 2.
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }

        return nums[left];
    }

    public static void main(String[] args) {
        _153_FindMinRotated rdup = new _153_FindMinRotated();
        int[][] inputs = {
                {1, 2, 3, 4, 5},
                {0, 6, 7, 8, 9, 10, 11, 0},
                {2, 3, 4},
                {-3, -1, 3}
        };

        for (int[] inp : inputs)
            System.out.println(rdup.findMin(inp));
    }
}
