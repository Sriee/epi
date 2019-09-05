package sq;

import java.util.Arrays;

/**
 * Circular Dequeue implementation
 * 
 * Leet code. Solution -> Accepted
 * @author sriee
 *
 */
public class CircularDeque {

	private int head = -1, tail = -1, num = 0;
	private Integer[] elements;
	
	public CircularDeque(int k) {
		elements = new Integer[k];
	}
	
	public boolean insertFront(int value) {
		if(this.isFull())
			return false;
		if(head == -1) {
			elements[++head] = value;
			tail = head;
		} else {
			head = (head - 1) % elements.length;
			if(head < 0)
				head = elements.length + head;
			elements[head] = value;
		}
	
		num++;
		return true;
	}
	
	public boolean insertLast(int value) {
		if(this.isFull())
			return false;
		
		if(tail == -1) {
			elements[++tail] = value;
			head = tail;
		} else {
			tail = (tail + 1) % elements.length;
			elements[tail] = value;			
		}

		num++;
		return true;
	}
	
	public boolean deleteFront() {
		if(this.isEmpty())
			return false;
		
		elements[head] = null;
		head = (head + 1) % elements.length;
		num--;
		
		if(this.isEmpty()) {
			head = -1;
			tail = -1;
		}
		return true;
	}
	
	public boolean deleteLast() {
		if(this.isEmpty())
			return false;
		
		elements[tail] = null;
		tail = (tail - 1) % elements.length;
		num--;
		
		if(tail < 0)
			tail = elements.length + tail;
		
		if(this.isEmpty()) {
			head = -1;
			tail = -1;
		}
		return true;
	}
	public int getFront() {
		return (head == -1) ? -1 : elements[head];
	}
	
	public int getRear() {
		return (tail == -1) ? -1 : elements[tail];
	}
	
	public boolean isEmpty() {
		return num == 0;
	}
	
	public boolean isFull() {
		return num == elements.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	
	// ["insertFront","getRear","deleteLast","getRear","insertFront","insertFront","insertFront","insertFront","isFull","insertFront","isFull"]
	// [[77],[89],[],[],[],[19],[23],[23],[82],[],[45],[]]
	public static void main(String[] args) {
		// 1
		CircularDeque c = new CircularDeque(77);
		c.insertFront(89);
		System.out.println(c.getRear());
		c.deleteLast();
		System.out.println(c.getRear());
		c.insertFront(19);
		c.insertFront(23);
		c.insertFront(23);
		c.insertFront(82);
		System.out.println(c.isFull());
		c.insertFront(45);
		System.out.println(c.isFull());		
	}
}