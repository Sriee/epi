package com.epi.sq;

import java.util.Arrays;

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

	// ["getRear","deleteLast","getFront","getFront","insertLast","deleteFront","getFront","insertLast","getRear","insertLast","getRear","getFront","getFront","getFront"] 
	// [[],[],[],[],[74],[],[],[98],[],[99],[],[],[],[]] 
	
	// ["getRear","getRear","insertFront","getFront","getFront","getFront","getFront","deleteFront","insertFront","getFront","deleteLast","insertLast","insertLast"]
	// [[],[],[8],[],[],[],[],[],[75],[],[],[35],[59]] 
	
	// ["getRear","getRear","getRear","isEmpty","insertFront","deleteLast","getFront","deleteLast","getRear","getFront","isFull","isFull","deleteFront"] 
	// [[],[],[],[],[22],[],[],[],[],[],[],[],[]]
	
	// ["getFront","deleteLast","getRear","insertFront","getFront","insertFront","insertFront","getRear","isFull","getFront","getFront","insertFront","insertLast"]
	// [[],[],[],[21],[],[26],[63],[],[],[],[],[87],[76]]
	
	// ["getRear","getRear","deleteLast","insertFront","getRear","insertLast","getFront","getFront","getFront","getRear","insertFront","isEmpty","getFront"]
	// [[],[],[],[26],[],[67],[],[],[],[],[36],[],[]]
	
	// ["getFront","insertFront","deleteFront","insertFront","deleteLast","getFront","getRear","getFront","insertFront","getFront","deleteFront","insertFront","isEmpty","getRear","getRear","getRear","getRear","deleteFront","getRear","isEmpty","deleteFront","insertFront","insertLast","deleteLast"]
	// [[],[72],[],[87],[],[],[],[],[85],[],[],[91],[],[],[],[],[],[],[],[],[],[34],[44],[]]
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
		
		// 2
		System.out.println(c.getRear());
		c.deleteLast();
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		c.insertLast(74);
		c.deleteFront();
		System.out.println(c.getFront());
		c.insertLast(98);
		System.out.println(c.getRear());
		c.insertLast(99);
		System.out.println(c.getRear());
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		
		// 3
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		c.insertFront(8);
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		c.deleteFront();
		c.insertFront(75);
		System.out.println(c.getFront());
		c.deleteLast();
		c.insertLast(35);
		c.insertLast(59);
		
		// 4
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		System.out.println(c.isEmpty());
		c.insertFront(22);
		c.deleteLast();
		System.out.println(c.getFront());
		c.deleteLast();
		System.out.println(c.getRear());
		System.out.println(c.getFront());
		System.out.println(c.isFull());
		System.out.println(c.isFull());
		c.deleteFront();
		
		// 5
		System.out.println(c.getFront());
		c.deleteLast();
		System.out.println(c.getRear());
		c.insertFront(22);
		System.out.println(c.getFront());
		c.insertFront(26);
		c.insertFront(63);
		System.out.println(c.getRear());
		System.out.println(c.isFull());
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		c.insertFront(87);
		c.insertLast(76);
		
		// 6
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		c.deleteLast();
		c.insertFront(26);
		System.out.println(c.getRear());
		c.insertLast(67);
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		System.out.println(c.getFront());
		System.out.println(c.getRear());
		c.insertFront(36);
		System.out.println(c.isEmpty());
		System.out.println(c.getFront());
		
		// 7
		// ["getFront","insertFront","deleteFront","insertFront","deleteLast","getFront","getRear","getFront","insertFront","getFront","deleteFront","insertFront"]
		// [[],[72],[],[87],[],[],[],[],[85],[],[],[91]]
		System.out.println(c.getFront());
		c.insertFront(72);
		c.deleteFront();
		c.insertFront(87);
		c.deleteLast();
		System.out.println(c.getFront());
		System.out.println(c.getRear());
		System.out.println(c.getFront());
		c.insertFront(85);
		System.out.println(c.getFront());
		c.deleteFront();
		c.insertFront(91);
		
		// 8
		// ["isEmpty","getRear","getRear","getRear","getRear","deleteFront","getRear","isEmpty","deleteFront","insertFront","insertLast","deleteLast"]
		// [[],[],[],[],[],[],[],[],[],[34],[44],[]]
		System.out.println(c.isEmpty());
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		System.out.println(c.getRear());
		c.deleteFront();
		System.out.println(c.getRear());
		System.out.println(c.isEmpty());
		c.deleteFront();
		c.insertFront(34);
		c.insertLast(44);
		c.deleteLast();
		System.out.println(c);
	}

}
