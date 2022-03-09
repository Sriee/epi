package heaps.two_heaps;

import java.util.*;
import util.PrintHypens;

public class _2231_DigitSwapByParity {
    public int largestInteger(int num) {
        int[] pos = new int[10];
        int idx = 0;
        PriorityQueue<Integer> even = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> odd = new PriorityQueue<>(Collections.reverseOrder());

        for (; num > 0; idx++) {
            int rem = num % 10;
            num /= 10;

            if ((rem & 1) == 0) { // even
                even.offer(rem);
            } else { // odd
                odd.offer(rem);
                pos[idx]++;
            }
        }

        for (idx = idx - 1; idx >= 0; idx--) {
            num *= 10;
            if (!odd.isEmpty() && pos[idx] == 1) {
                num += odd.poll();
            } else if (!even.isEmpty()) {
                num += even.poll();
            }
        }

        return num;
    }

    public static void main(String[] args) {
        _2231_DigitSwapByParity dsp = new _2231_DigitSwapByParity();

        int[] testCases = {1234, 65875, 4321, 2468, 98123};
        for (int num : testCases) {
            System.out.println("\tInput number: " + num);
            System.out.println("\tOutput number: " + dsp.largestInteger(num));
            System.out.println(PrintHypens.generate());
        }
    }
}
