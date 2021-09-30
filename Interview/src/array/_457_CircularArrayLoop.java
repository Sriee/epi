package array;

public class _457_CircularArrayLoop {

    int len = 0;

    /**
     * Floyd Cycle Detection Algorithm using slow/fast pointer technique.
     */
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        len = nums.length;
        int slow, fast;
        for (int i = 0; i < len; i++) {
            if (nums[i] % len == 0) continue;

            boolean currDir = nums[i] >= 0;

            slow = i;
            fast = i;
            while (slow != -1 || fast != -1) {
                slow = nextStep(nums, currDir, slow);

                if (slow == -1) break;
                fast = nextStep(nums, currDir, nextStep(nums, currDir, i));

                if (fast != -1 && slow == fast)
                    return true;
            }
        }

        return false;
    }

    private int nextStep(int[] nums, boolean direction, int currentIndex) {
        if (currentIndex == -1)
            return -1;
        boolean nextDirection = nums[currentIndex] >= 0;
        if (direction != nextDirection || nums[currentIndex] % len == 0)
            return -1;

        int next = (currentIndex + nums[currentIndex]) % len;
        return (next < 0) ? next + len : next;
    }

    public static void main(String[] args) {
        _457_CircularArrayLoop cal = new _457_CircularArrayLoop();
        int[][] inputs = {
            {2,-1,1,2,2},               // true
            {-1},                       // false
            {1, 3, -2, -4, 1},          // true
            {-2, -3, -9},               // false
            {-5, -4, -3, -2, -1},       // true
            {-1, -2, -3, -4, -5},       // false
            {-1, -2, -3, -4, -5, 6},    // false
            {2, 1, -1, -2, 2},          // false
            {2, 1, -1, -2},             // false
            {3, -2, -1, 1},             // true
            {1, 2, -3, 3, 4, 7, 1}      // true
        };

        for (int[] inp : inputs) {
            System.out.println(cal.circularArrayLoop(inp));
        }
    }
}
