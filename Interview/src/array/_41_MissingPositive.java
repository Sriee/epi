package array;

public class _41_MissingPositive {

    public int firstMissingPositive(int[] nums) {
        int i = 0, j, n = nums.length;

        while (i < n) {
            j = nums[i] - 1;

            if (j >= 0 && j < n && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        for (int k = 0; k < n; k++) {
            if (nums[k] != k + 1)
                return k + 1;
        }

        return n + 1;
    }

    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        _41_MissingPositive mp = new _41_MissingPositive();
        int[][] inputs = {
                {3, 4, -1, 1},
                {9, 6, 4, 2, 3, 5, 7, 0, 1}
        };
        for (int[] inp : inputs)
            System.out.println(mp.firstMissingPositive(inp));
    }
}
