package com.epi.list;

public class ListNode<T extends Comparable<T>>{
	public T data;
	ListNode<T> next;
	
	public ListNode(T data){
		this.data = data;
	}
	
	public ListNode(T data, ListNode<T> next){
		this.data = data;
		this.next = next;
	}
	
	@Override
	public String toString(){
		return "ListNode(" + data + ")";
	}
}
