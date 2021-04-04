package array;

public class _560_SubArraySum {

    /**
     * Followed Sliding window pattern to implement the solution. Seems like sliding window pattern doesn't
     * work for this solution.
     *
     * TODO: Should try other approaches
     */
    public int subarraySum(int[] nums, int k) {
        int start = 0, end = 0, count = 0, sum = 0;

        while (end < nums.length) {
            sum += nums[end];

            if (sum == k)
                count++;

            end++;
            if (sum > k) {
                while (start <= end && sum > k)
                    sum -= nums[start++];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _560_SubArraySum ss = new _560_SubArraySum();
        int[] nums = {1, 1, 1};
        System.out.println(ss.subarraySum(nums, 2));
    }
}
