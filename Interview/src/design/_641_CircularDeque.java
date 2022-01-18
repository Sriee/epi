package design;

public class _641_CircularDeque {
    int size, count = 0, front = -1, last = -1;
    int[] queue;

    public _641_CircularDeque(int k) {
        this.size = k;
        this.queue = new int[k];
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;

        if (isEmpty()) {
            front = 0;
            last = 0;
        } else if (front - 1 < 0) {
            front = size - 1;
        } else {
            front--;
        }

        queue[front] = value;
        count++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;

        if (isEmpty()) {
            front = 0;
            last = 0;
        } else if (last + 1 >= size) {
            last = 0;
        } else {
            last++;
        }

        queue[last] = value;
        count++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;

        count--;
        if (isEmpty()) {
            front = -1;
            last = -1;
        } else if (front + 1 >= size) {
            front = 0;
        } else {
            front++;
        }

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;

        count--;
        if (isEmpty()) {
            front = -1;
            last = -1;
        } else if (last - 1 < 0) {
            last = size - 1;
        } else {
            last--;
        }

        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : queue[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : queue[last];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public static void main(String[] args) {
        _641_CircularDeque cq = new _641_CircularDeque(5);

        System.out.println("insertFront(89) = " + cq.insertFront(89));
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("insertFront(19) = " + cq.insertFront(19));
        System.out.println("insertFront(23) = " + cq.insertFront(23));
        System.out.println("insertFront(23) = " + cq.insertFront(23));
        System.out.println("insertFront(82) = " + cq.insertFront(82));
        System.out.println("isFull() = " + cq.isFull());
        System.out.println("insertFront(45) = " + cq.insertFront(45));
        System.out.println("isFull() = " + cq.isFull());
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("insertLast(74) = " + cq.insertLast(74));
        System.out.println("deleteFront() = " + cq.deleteFront());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("insertLast(98) = " + cq.insertLast(98));
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("insertLast(99) = " + cq.insertLast(99));
        System.out.println("deleteFront() = " + cq.deleteFront());
        System.out.println("insertFront(75) = " + cq.insertFront(75));
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("insertLast(35) = " + cq.insertLast(35));
        System.out.println("insertLast(59) = " + cq.insertLast(59));
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("isEmpty() = " + cq.isEmpty());
        System.out.println("insertFront(22) = " + cq.insertFront(22));
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("isFull() = " + cq.isFull());
        System.out.println("deleteFront() = " + cq.deleteFront());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("deleteLast() = " + cq.deleteLast());
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("insertFront(21) = " + cq.insertFront(21));
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("insertFront(26) = " + cq.insertFront(26));
        System.out.println("insertFront(63) = " + cq.insertFront(63));
        System.out.println("getRear() = " + cq.getRear());
        System.out.println("isFull() = " + cq.isFull());
        System.out.println("getFront() = " + cq.getFront());
        System.out.println("insertFront(87) = " + cq.insertFront(87));
        System.out.println("insertLast(76) = " + cq.insertLast(76));
        System.out.println("getRear() = " + cq.getRear());
    }
}
