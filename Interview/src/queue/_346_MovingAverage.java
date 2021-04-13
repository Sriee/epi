package queue;

public class _346_MovingAverage {

    // Variables for Ring Buffer approach
    private int size, capacity, head, tail;
    private int[] arr;
    private double sum;

    public _346_MovingAverage(int size) {
        capacity = 0;
        head = -1;
        tail = -1;
        sum = 0;
        this.size = size;
        arr = new int[size];
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

    public static void main(String[] args) {
        _346_MovingAverage mavg = new _346_MovingAverage(4);

        int[] vals = {1, 10, 3, 5, 6, -168845, 57832, -48, 100000};

        for (int val : vals) System.out.print(mavg.next(val) + " ");
        System.out.println();
    }
}
