package com.epi.heaps;

import java.util.Comparator;
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
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Each turn, choose the two heaviest rocks
	 * 
	 * If x == y, it cancels out each other;
	 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x. At the end, there is at most 1 stone left.  
	 * 
	 * Return the weight of the stone (or 0 if there are no stones left.)
	 * 
	 * @param stones array of weights
	 * @return the weight of the stone
	 */
	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i, Integer j) {
				return j.compareTo(i);
			}
		});
		
		for(int i : stones)
			queue.add(i);
		
		while(queue.size() > 1) {
			int x = queue.poll();
			int y = queue.poll();
			
			if(x != y) {
				queue.add(Math.abs(x - y));
			}
		}
		
		return queue.isEmpty() ? 0 : queue.peek();
	}
}
