package binary_search;

public class _410_SplitArrLargestSum {

    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int pieces = split(nums, mid);

            if (pieces > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int split(int[] arr, int upperLimit) {
        int pieces = 1;
        long sum = 0;
        for (int num : arr) {

            if (sum + num > upperLimit) {
                pieces++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return pieces;
    }

    public static void main(String[] args) {
        _410_SplitArrLargestSum sal = new _410_SplitArrLargestSum();

        int[] nums = {7, 2, 5, 10, 8};
        int[] ms = {1, 5, 2, 3};

        for (int m : ms) {
            System.out.println(sal.splitArray(nums, m));
        }
    }
}
