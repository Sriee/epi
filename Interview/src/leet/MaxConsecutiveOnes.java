package leet;

public class MaxConsecutiveOnes {
    private static int maxOnes(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int total = 0, current = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                current++;
            else {
                if (current >= total)
                    total = current;
                current = 0;
            }
        }

        if (current >= total)
            total = current;
        return total;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 0, 1, 1, 0, 1 };

        System.out.println(maxOnes(null));
        System.out.println(maxOnes(new int[] {}));
        System.out.println(maxOnes(arr));
    }
}
