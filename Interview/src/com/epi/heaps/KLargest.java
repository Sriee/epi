package com.epi.heaps;

import java.util.*;

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
	
	/**
	 * Leet code problem. Solution -> Accepted
	 * 
	 * Compute the k most frequent numbers
	 * 
	 * @param nums 
	 * @param k
	 * @return list of k frequent numbers in the given array
	 */
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> result = new LinkedList<>();
		PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		for(int i : nums)
			map.put(i, map.getOrDefault(i, 0) + 1);

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			queue.add(entry);
			if(queue.size() > k)
				queue.poll();
		}
		
		while(k != 0) {
			k--;
			result.add(0, queue.poll().getKey());
		}
		return result;
	}

	/**
	 * Leet code. Solution -> Accepted
	 *
	 * Calculate the kth smallest pairs from two arrays
	 * 
	 * @param  nums1 Array 1
	 * @param  nums2 Array 2
	 * @param  k     Number of smallest pairs to be present in the result
	 * @return       List of k smallest pairs
	 */
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
		List<List<Integer>> result = new LinkedList<>();
		for(int i: nums1) {
			for(int j: nums2) {
				List<Integer> temp = new LinkedList<>();
				temp.add(i);
				temp.add(j);
				
				queue.add(temp);
				if(queue.size() > k)
					queue.poll();
			}
		}
		
		while(!queue.isEmpty()) {
			result.add(0, queue.poll());
		}
		return result;
	}
}
