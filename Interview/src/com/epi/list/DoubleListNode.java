package com.epi.list;

/**
 * @author sriee
 *
 */
public class DoubleListNode<T> {

	public T val;
	public DoubleListNode<T> prev = null;
	public DoubleListNode<T> next = null;
	public DoubleListNode<T> child = null;
	
	public DoubleListNode() {
		this.val = null;
	}
	
	public DoubleListNode(T val) {
		this.val = val;
	}
	
	public DoubleListNode(DoubleListNode<T> prev, DoubleListNode<T> next) {
		this.prev = prev;
		this.next = next;
	}
	
}
