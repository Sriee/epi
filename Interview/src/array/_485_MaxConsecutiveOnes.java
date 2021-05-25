package array;

class _485_MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, counter = 0;

        for (int i : nums) {
            if (i == 0) {
                res = Math.max(res, counter);
                counter = 0;
            } else {
                counter++;
            }
        }
        return Math.max(res, counter);
    }

    public static void main(String[] args) {
        _485_MaxConsecutiveOnes mc = new _485_MaxConsecutiveOnes();
        int[][] inputs = {
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1}
        };

        for (int[] input : inputs) {
            System.out.println(mc.findMaxConsecutiveOnes(input));
        }
    }
}