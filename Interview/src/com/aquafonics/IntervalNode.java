package com.aquafonics;

import java.util.List;
import java.util.ArrayList;

public class IntervalNode{
	Interval interval;
	List<String> rules;
	IntervalNode left, right;
	int N;
	int max_endpoint;
	
	public IntervalNode(Interval interval, String sensor) {
		this.interval = interval;	
		rules = new ArrayList<>();
		rules.add(sensor);
		this.max_endpoint = interval.max;
		this.N = 1;
	}
	
	public void addRule(String sensor) { this.rules.add(sensor); }
	
	public boolean hasLeftChild() { return this.left != null; }
	
	public boolean hasRightChild() { return this.right != null; }
	
	public boolean hasBothChildren() { return (this.left != null && this.right != null); }
	
	public boolean hasAnyChildren() { return (this.left != null || this.right != null); }
	
	@Override
	public String toString() {
		return String.format("[IntervalNode= %s,Sensors= %s, N= %d, max_endpoint= %d]", this.interval, this.rules.toString(), this.N,  
				this.max_endpoint);
	}
	
	public static void main(String[] args) {
		IntervalNode n1 = new IntervalNode(new Interval(1, 15), "first");
		System.out.println(n1);
		System.out.println(String.format("%s %s %s %s", n1.hasLeftChild(), n1.hasRightChild(), n1.hasBothChildren(), n1.hasAnyChildren()));

		IntervalNode n2 = new IntervalNode(new Interval(20, 25), "second");
		n1.left = n2;
		System.out.println(n2);
		
		IntervalNode n3 = new IntervalNode(new Interval(30, 50), "third");
		n3.addRule("fourth");
		n1.right = n3;
		
		System.out.println(n3);
	}	
}
