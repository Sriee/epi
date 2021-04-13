package queue;

import java.util.*;

public class _346_MovingAverage {

    // Variables for Ring Buffer approach
    private int size, capacity, head, tail;
    private int[] arr;
    private double sum;

    // Variables for Built-in queue approach
    private Queue<Integer> queue;

    public _346_MovingAverage(int size) {
        capacity = 0;
        head = -1;
        tail = -1;
        sum = 0;
        this.size = size;
        arr = new int[size];

        queue = new LinkedList<>();
    }

    public double next(int val) {
        tail = (tail + 1) % size;

        if (capacity < size) {
            if (head == -1)
                head++;
            capacity++;
        } else if (head == tail) {
            sum -= arr[head];
            head = (head + 1) % size;
        }

        arr[tail] = val;
        sum += val;

        return sum / capacity;
    }

    // Built-in Queue Approach
    private double nextQ(int val) {
        if (queue.size() == size)
            sum -= queue.poll();

        queue.offer(val);
        sum += val;
        return sum / queue.size();
    }

    public static void main(String[] args) {
        _346_MovingAverage mavg = new _346_MovingAverage(4);

        int[] vals = {1, 10, 3, 5, 6, -168845, 57832, -48, 100000};

        for (int val : vals) System.out.print(mavg.next(val) + " ");
        System.out.println();

        mavg.sum = 0;
        for (int val : vals) System.out.print(mavg.next(val) + " ");
        System.out.println();
    }
}
