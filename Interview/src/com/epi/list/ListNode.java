package com.epi.list;

public class ListNode<T extends Comparable<T>> implements Comparable<ListNode<T>>{
	public T data;
	public ListNode<T> next;
	
	public ListNode() {
		this.data = null;
		this.next = null;
	}
	
	public ListNode(T data){
		this.data = data;
	}
	
	public ListNode(T data, ListNode<T> next){
		this.data = data;
		this.next = next;
	}
	
	@Override
	public int compareTo(ListNode<T> other){
		return this.data.compareTo(other.data);
	}
	
	@Override
	public String toString(){
		return "ListNode(" + data + ")";
	}
}

