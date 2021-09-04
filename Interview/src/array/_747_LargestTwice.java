package array;

class _747_LargestTwice {
    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE, maxIdx = -1, n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIdx = i;
            }
        }

        for (int i = 0; i < n; i++) {
            int tm = nums[i] * 2;
            if (max < tm && i != maxIdx)
                return -1;
        }

        return maxIdx;
    }

    public static void main(String[] args) {
        _747_LargestTwice lt = new _747_LargestTwice();
        int[][] input = {
                {3, 6, 1, 0},
                {1, 2, 3, 4},
                {1}
        };
        for (int[] nums : input) {
            System.out.println(lt.dominantIndex(nums));
        }
    }
}