package queue;

/**
 * Circular Queue implementation. This is called a ring buffer because we make use of two pointers to reuse the spaces
 * in the array container.
 *
 * Asked in our 2018 Microsoft Interview for Azure Team.
 */
class _622_CircularQueue {

    private int k, size, head, tail;
    private int[] arr;

    public _622_CircularQueue(int k) {
        this.k = k;
        this.size = 0;
        this.head = -1;
        this.tail = -1;
        this.arr = new int[k];
    }

    public boolean enQueue(int value) {
        if(this.isFull())
            return false;

        if(this.isEmpty())
            this.head++;

        this.tail = (this.tail + 1) % this.k;
        this.size++;

        this.arr[this.tail] = value;
        return true;
    }

    public boolean deQueue() {
        if(this.isEmpty())
            return false;

        this.head = (this.head + 1) % this.k;
        this.size--;

        if(this.isEmpty()) {
            this.head = -1;
            this.tail = -1;
        }

        return true;
    }

    public int Front() {
        return (this.isEmpty()) ? -1 : this.arr[this.head];
    }

    public int Rear() {
        return (this.isEmpty()) ? -1 : this.arr[this.tail];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.k;
    }

    public static void main(String[] args) {
        _622_CircularQueue queue = new _622_CircularQueue(3);
        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.Rear());
        System.out.println(queue.Front());
    }
}