package leet;

import java.util.Queue;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleNumber {

    private class QueueNode implements Comparable<QueueNode> {
        public int array;
        public int index;
        public int value;

        public QueueNode(int array, int index, int value) {
            this.array = array;
            this.index = index;
            this.value = value;
        }

        public int compareTo(QueueNode other) {
            if (this.value < other.value)
                return -1;
            else if (this.value > other.value)
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return String.format("[%s, index= %d, value= %d]", (this.array == 0) ? "m" : "n", this.index, this.value);
        }
    }

    private void merge(int[] one, int m, int[] two, int n) {
        Queue<QueueNode> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(new QueueNode(0, i, one[i]));
            pq.add(new QueueNode(1, i, two[i]));
        }

        for (int j = n; j < m; j++) {
            pq.add(new QueueNode(0, j, one[j]));
        }

        for (int k = 0; k < m + n; k++) {
            one[k] = pq.poll().value;
        }

        System.out.println(Arrays.toString(one));
    }

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        sn.merge(new int[] { 0 }, 0, new int[] { 1 }, 1);
    }
}
