package dp;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;

public class _121_BuySellStock {

    public int maxCut(int[] prices, int n) {
        if (n == 0)
            return 0;

        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, prices[i] + maxCut(prices, n - i));
        }

        return q;
    }

    /**
     * Leet code. Solution -> Accepted Run Time: 1 ms. Optimal run time. Given an
     * integer array which repersents stock prices. Find the maximum profit (Buy low
     * sell high).
     * 
     * @param prices of the stock
     * @return max profit if available else 0
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    private int[] generate(int n) {
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++)
            arr[i] = rand.nextInt(100);

        return arr;
    }

    public static void main(String[] args) {
        _121_BuySellStock stock = new _121_BuySellStock();
        int[] prices = null;
        /*
         * int[] prices = new int[] { 17, 15, 1, 17, 9, 5, 23, 12, 7, 2, 25, 12, 23, 19,
         * 21, 23, 21, 14, 22, 24 }; System.out.println(stock.maxProfit(prices));
         */
        // prices = new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        prices = stock.generate(50);

        Instant start = Instant.now();
        int result = stock.maxCut(prices, 35);
        Instant end = Instant.now();
        Duration timeLapsed = Duration.between(start, end);

        System.out.println(result + "\nTime: " + timeLapsed.getSeconds());
    }

}
