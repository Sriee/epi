package array;

class _487_MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int count = 0, idx = 0;
        boolean filled = false;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (filled) {
                    ans = Math.max(ans, count);
                    count = i - idx - 1;
                    idx = i;
                } else {
                    filled = true;
                    idx = i;
                }
            }

            count++;
        }

        return Math.max(ans, count);
    }

    public static void main(String[] args) {
        _487_MaxConsecutiveOnesII mc2 = new _487_MaxConsecutiveOnesII();
        int[][] inputs = {
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1}
        };

        for (int[] input : inputs) {
            System.out.println(mc2.findMaxConsecutiveOnes(input));
        }
    }
}