package heaps.two_heaps;

import util.PrintHypens;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _502_Ipo {
    private class Project implements Comparable<Project> {
        int capital, profit;

        public Project(int c, int p) {
            this.capital = c;
            this.profit = p;
        }

        @Override
        public int compareTo(Project other) {
            return this.capital - other.capital;
        }
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Project> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < profits.length; i++) {
            if (capital[i] > w) {
                minHeap.offer(new Project(capital[i], profits[i]));
            } else {
                maxHeap.offer(profits[i]);
            }
        }

        while (k-- > 0 && !maxHeap.isEmpty()) {
            w += maxHeap.poll();

            while (!minHeap.isEmpty() && minHeap.peek().capital <= w) {
                maxHeap.offer(minHeap.poll().profit);
            }
        }

        return w;
    }

    public static void main(String[] args) {
        _502_Ipo ipo = new _502_Ipo();

        int[] w = {1, 2, 1, 7, 2};
        int[] k = {2, 3, 3, 2, 4};
        int[][] capitals = {
                {1, 2, 2, 3},
                {1, 3, 4, 5, 6},
                {1, 2, 3, 4},
                {6, 7, 8, 10},
                {2, 3, 5, 6, 8, 12}
        };
        int[][] profits = {
                {2, 4, 6, 8},
                {1, 2, 3, 4, 5},
                {1, 3, 5, 7},
                {4, 8, 12, 14},
                {1, 2, 5, 6, 8, 9}
        };

        for (int i = 0; i < k.length; i++) {
            System.out.println((i + 1) + ".\tProject capital requirements: " + Arrays.toString(capitals[i]));
            System.out.println("\tProject expected profits: " + Arrays.toString(profits[i]));
            System.out.println("\tNumber of projects: " + k[i]);
            System.out.println("\tStart-up capital: " + w[i]);
            System.out.println("\n\tMaximum Capital earned: " + ipo.findMaximizedCapital(w[i], k[i], profits[i], capitals[i]));
            System.out.println(PrintHypens.generate());
        }

    }
}
