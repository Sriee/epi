package dp;

public class BuySellStock {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Optimal run time.
	 * 
	 * Given an integer array which repersents stock prices. Find the maximum profit (Buy low sell high).
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

	public static void main(String[] args) {
		BuySellStock stock = new BuySellStock();
		int[] prices = new int[] { 17, 15, 1, 17, 9, 5, 23, 12, 7, 2, 25, 12, 23, 19, 21, 23, 21, 14, 22, 24 };

		System.out.println(stock.maxProfit(prices));
	}

}
