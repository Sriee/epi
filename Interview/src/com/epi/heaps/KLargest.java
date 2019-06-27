package com.epi.heaps;

import java.util.PriorityQueue;

public class KLargest {
	PriorityQueue<Integer> queue = null;
	int k = 0;
	
	public KLargest(int k, int[] nums) {
		this.queue = new PriorityQueue<>() ;
		this.k = k;

		for(int i : nums)
			this.add(i);
	}
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Given a array of values, find the kth largest element. 
	 * 
	 * @param val to add to the queue
	 * @return kth largest element in a queue
	 */
	public int add(int val) {
		if(this.queue.size() < k)
			this.queue.add(val);
		else {
			if(val > this.queue.peek()){
				this.queue.poll();
				this.queue.add(val);
			}
		}

		return this.queue.peek();
	}
}
