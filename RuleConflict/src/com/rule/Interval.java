package com.rule;

public class Interval implements Comparable<Interval>{
	
	public final int min;
	public final int max;
	
	public Interval(int min, int max) {
		if(min <= max) {
			this.min = min;
			this.max = max;
		} else { throw new IllegalArgumentException(); }
	}
	
	public boolean overrides(Interval other) {
		if(this.min == other.min && (this.max < other.max || other.max < this.max)) {
			return true;
		} else if(this.max == other.max && (this.min < other.min || other.min < this.min)){
			return true;
		}
		return false;
	}
	
	public boolean intersects(Interval other) {
		if(other.max < this.min) return false;
		if(this.max < other.min) return false;
		if(this.min == other.min && this.max == other.max) return false;
		return true;
	}
	
	@Override
	public int compareTo(Interval other) {
		if(this.min < other.min) return -1;
		else if(this.min > other.min) return 1;
		else if(this.max < other.max) return -1;
		else if(this.max > other.max) return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		return "[min= " + this.min + ", max= " + this.max + "]";
	}
	
	public static void main(String[] args) {
		Interval a = new Interval(10, 20);
		Interval b = new Interval(0, 7);
		Interval c = new Interval(18, 56);
		Interval d = new Interval(30, 100);
		Interval e = new Interval(18, 56);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println();
		
		System.out.println(a.intersects(b));
		System.out.println(a.intersects(c));
		System.out.println(b.intersects(c));
		System.out.println(a.intersects(c));
		System.out.println(c.intersects(e));
	}
}
