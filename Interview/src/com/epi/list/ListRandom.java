package com.epi.list;

public class ListRandom {
	public int val;
	public ListRandom next;
	public ListRandom random;
	
	public ListRandom() {}
	
	public ListRandom(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "ListRandom(" + this.val + ")";
	}
}
