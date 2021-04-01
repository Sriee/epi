package sq;

import java.util.Arrays;

/**
 * Implementation of circular queue Leet code. Solution -> Accepted
 * 
 * @author sriee
 */
public class CircularQueue {

    private int head = -1, tail = -1, num = 0;
    private Integer[] elements;

    public CircularQueue(int k) {
        this.elements = new Integer[k];
    }

    public boolean enQueue(int value) {
        // Queue is full
        if (this.isFull())
            return false;

        // First Element
        if (this.head == -1) {
            this.elements[++this.head] = value;
            this.tail = this.head;
        } else {
            this.tail = (this.tail + 1) % this.elements.length;
            this.elements[this.tail] = value;
        }
        this.num++;

        return true;
    }

    public boolean deQueue() {
        if (this.isEmpty())
            return false;

        this.elements[this.head] = null;
        this.head = (this.head + 1) % this.elements.length;
        this.num--;

        if (this.isEmpty()) {
            this.head = -1;
            this.tail = -1;
        }

        return true;
    }

    public int Front() {
        return (this.head == -1) ? -1 : this.elements[this.head];
    }

    public int Rear() {
        return (this.tail == -1) ? this.tail : this.elements[this.tail];
    }

    public boolean isEmpty() {
        return this.num == 0;
    }

    public boolean isFull() {
        return this.num == this.elements.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.elements);
    }

    // ["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
    // [[6],[6],[],[],[],[5],[],[],[],[],[],[]]
    // ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","deQueue","deQueue","isEmpty","isEmpty","Rear","Rear","deQueue"]
    // [[8],[3],[9],[5],[0],[],[],[],[],[],[],[]]

    public static void main(String[] args) {
        CircularQueue c = new CircularQueue(3);
        c.enQueue(21);
        c.enQueue(33);
        c.enQueue(44);
        c.enQueue(0);
        System.out.println(c);
        System.out.println(c.deQueue());
        System.out.println(c.deQueue());
        System.out.println(c.deQueue());
        System.out.println(c.isEmpty());
        c.enQueue(12);
        System.out.println(c);

        System.out.println(c.Front());
        System.out.println(c.Rear());
        c.enQueue(67);
        System.out.println(c.isFull());
        System.out.println(c);
    }

}
