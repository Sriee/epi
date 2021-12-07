package dp;

import java.util.*;

public class Fibonacci {

    Map<Integer, Integer> mem = new HashMap<>();

    public int fib(int n) {
        if (mem.containsKey(n)) {
            return mem.get(n);
        }

        if (n == 1 || n == 2)
            return 1;

        int res = fib(n - 1) + fib(n - 2);
        mem.put(n, res);

        return res;
    }

    public static void main(String[] args) {
        Fibonacci fb = new Fibonacci();

        System.out.println(fb.fib(40));
    }
}
