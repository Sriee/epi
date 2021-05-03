package binary_search;

public class _374_GuessNumber {
    int pick;

    private int guess(int n) {
        return Integer.compare(pick, n);
    }

    /* We are using Binary Search Template 1 as we are searching for an element
     * in a range.
     */
    public int guessNumber(int n) {
        int left = 1, right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int g = guess(mid);

            if (g == 1)
                left = mid + 1;
            else if (g == -1)
                right = mid - 1;
            else
                return mid;
        }

        return n;
    }

    public static void main(String[] args) {
        _374_GuessNumber gn = new _374_GuessNumber();
        int[] input = {4, 6, 87, 68};
        for (int num : input) {
            gn.pick = num;
            int calc = gn.guessNumber(100);
            System.out.printf("%d %s %d\n", num, num == calc ? "==" : "!=", calc);
        }
    }
}
