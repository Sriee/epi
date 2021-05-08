package binary_search;

public class _367_PerfectSquare {

    // Binary Search Template 1
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num / 2 + 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sq = (long) mid * mid;

            if (sq > num)
                right = mid - 1;
            else if (sq < num)
                left = mid + 1;
            else
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        _367_PerfectSquare ps = new _367_PerfectSquare();
        int[] arr = {81, 4938, 282, 1667, 346921};

        for (int v : arr)
            System.out.printf("Is %d a perfect square - %s\n", v, ps.isPerfectSquare(v) ? "Yes" : "No");
    }
}
