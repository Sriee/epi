package array;

class _209_MinSubArrSum {
    public int minSubArrayLen(int target, int[] nums) {
        int begin = 0, end = 0, sum = 0, res = Integer.MAX_VALUE;

        while (end < nums.length) {
            if (nums[end] == target)
                return 1;

            sum += nums[end++];

            while (sum >= target) {
                res = Math.min(res, end - begin);
                sum -= nums[begin++];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        _209_MinSubArrSum ms = new _209_MinSubArrSum();
        int[][] input = {
                {2, 3, 1, 2, 4, 3},
                {1, 4, 4},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };
        int[] targets = {7, 1, 11};

        for (int i = 0; i < targets.length; i++) {
            int res = ms.minSubArrayLen(targets[i], input[i]);
            System.out.println(res);
        }
    }
}