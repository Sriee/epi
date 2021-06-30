package array;

class _2006_CountDifference {
    public int countKDifference(int[] nums, int k) {
        int[] map = new int[101];
        int res = 0;

        for (int i : nums) {
            res += (i + k < 101 ? map[i + k] : 0) + (i - k > 0 ? map[i - k] : 0);
            map[i]++;
        }

        return res;
    }

    public static void main(String[] args) {
        _2006_CountDifference cd = new _2006_CountDifference();
        int[] nums;

        // 1
        nums = new int[]{1, 2, 2, 1};
        System.out.println(cd.countKDifference(nums, 5));

        // 2
        nums = new int[]{1, 3};
        System.out.println(cd.countKDifference(nums, 3));

        // 3
        nums = new int[]{3, 2, 1, 5, 4};
        System.out.println(cd.countKDifference(nums, 2));
    }
}