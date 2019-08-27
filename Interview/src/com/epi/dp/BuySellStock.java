package com.epi.dp;

public class BuySellStock {

	/**
	 * Leet code. Solution -> Accepted
	 * 
	 * Run Time: 1 ms. Optimal run time. The below code is bit hacky
	 * 
	 * Given an integer array which repersents stock prices. Find the maximum profit (Buy low sell high).
	 * 
	 * @param prices of the stock
	 * @return max profit if available else 0
	 */
	public int maxProfit(int[] prices) {
		int buy = -1, sell = -1, max = 0;

		for (int i = 0; i < prices.length; i++) {
			if (buy == -1) {
				buy = prices[i];
			} else if (buy != -1 & sell == -1) {
				if (prices[i] < buy)
					buy = prices[i];
				else {
					sell = prices[i];
					if (sell - buy > max)
						max = sell - buy;
				}
			} else {
				if (prices[i] < buy) {
					buy = prices[i];
					sell = -1;
				} else if (prices[i] > sell) {
					sell = prices[i];
					if (sell - buy > max)
						max = sell - buy;
				}
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
