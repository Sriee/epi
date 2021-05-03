package binary_search;

/**
 * Binary Search Template 1
 */
public class _69_Sqrt {

    public int mySqrt(int x) {
        if (x < 2)
            return x;

        int left = 1, right = x / 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sq = (long) mid * mid;  // To prevent overflows

            if (sq > x)
                right = mid - 1;
            else if (sq < x)
                left = mid + 1;
            else
                return mid;
        }

        return right;
    }

    public static void main(String[] args) {
        _69_Sqrt sq = new _69_Sqrt();
        int[] n = {0, 2, 16, 19, 21474847, 4748347, 2147483647};

        for (int number : n) {
            int actual = (int) Math.sqrt(number);
            int computed = sq.mySqrt(number);
            System.out.printf("%d %s %d\n", actual, actual == computed ? "==" : "!=", computed);
        }
    }
}
