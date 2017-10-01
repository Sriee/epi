package com.aquafonics;

import java.util.List;
import java.util.ArrayList;

public class IntervalTree {

	private IntervalNode root = null;
	private int size = 0;
	private List<String> warnings = null; 
	
	public void put(int min, int max, String ruleId, String sensorId) throws RuleWarnings{
		warnings = new ArrayList<>();
		if(this.isEmpty()) {
			this.root = new IntervalNode(new Interval(min, max), ruleId, sensorId); 
		} else {
			this.put(new Interval(min, max), root, ruleId, sensorId);
		}
		this.size += 1;
		if(!warnings.isEmpty())
			throw new RuleWarnings(warnings.toString());
	}
	
	/**
	 * While inserting don't need to check  for conflict as put is used for actuators with the same 
	 * action  
	 * @param currentInterval
	 * @param currentNode
	 * @param ruleId
	 * @param sensorId
	 * @throws RuleConflictException
	 * @throws RuleWarnings
	 */
	private void put(Interval currentInterval, IntervalNode currentNode, String ruleId, String sensorId) {
		// Check for overlapping intervals
		if(currentInterval.intersects(currentNode.interval)) {
			this.warnings.add(currentNode.sensors.toString());
		}
		
		int cmp = currentInterval.compareTo(currentNode.interval);
		if(cmp < 0) {
			if(currentNode.hasLeftChild())
				this.put(currentNode.interval, currentNode.left, ruleId, sensorId);
			else
				currentNode.left = new IntervalNode(currentInterval, ruleId, sensorId);
		} else if (cmp > 0) {
			if(currentNode.hasRightChild())
				this.put(currentNode.interval, currentNode.right, ruleId, sensorId);
			else
				currentNode.right = new IntervalNode(currentInterval, ruleId, sensorId);
		} else { 				// For sensors having the same interval  
			currentNode.addRule(ruleId);
		}
		// update N & max end point
		this.update(currentNode);
	}
	
	public void check(Interval currentInterval, String ruleId, String sensorId) {
		
	}
	
	public boolean contains(int min, int max) { return this.contains(new Interval(min, max)); }
	
	public boolean contains(Interval interval) {
		if(this.isEmpty() || interval == null) return false;
		else return (this.get(root, interval) != null);
	}
	
	public IntervalNode get(Interval interval) {
		if(this.isEmpty()) 
			return null;
		else 
			return this.get(root, interval);
	}
	
	private IntervalNode get(IntervalNode currentNode, Interval currentInterval) {
		int cmp = currentNode.interval.compareTo(currentInterval);
		if (cmp < 0) {
			if (currentNode.hasLeftChild())
				return this.get(currentNode.left, currentInterval);
			else
				return null;
		} else if (cmp > 0) {
			if (currentNode.hasRightChild())
				return this.get(currentNode.right, currentInterval);
			else
				return null;
		} else {
			return currentNode;
		}
	}
	
	// Helper Functions
	private void update(IntervalNode currentNode) {
		if(currentNode.hasBothChildren()) {
			currentNode.N = 1 + currentNode.left.N + currentNode.right.N;
			currentNode.max_endpoint = this.max(currentNode.interval.max, 
					currentNode.left.max_endpoint, currentNode.right.max_endpoint);
		} else if (currentNode.hasAnyChildren()) {
			if(currentNode.hasLeftChild()) {
				currentNode.N = 1 + currentNode.left.N;
				currentNode.max_endpoint = Math.max(currentNode.interval.max, currentNode.left.max_endpoint);
			} else {
				currentNode.N = 1 + currentNode.right.N;
				currentNode.max_endpoint = Math.max(currentNode.interval.max, currentNode.right.max_endpoint);
			}
		}
	}
	
	public int size() { return this.size; }

	public boolean isEmpty() { return this.size == 0; }

	public int height() { return this.height(root); }
	
	private int height(IntervalNode node) {
		if(node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	private int max(int a, int b, int c) { return Math.max(a, Math.max(b, c)); }
	
	public static void main(String[] args) {
		IntervalTree it = new IntervalTree();
		
		try {
			it.put(20, 25, "second", "21");
			it.put(1, 15, "first", "22");
			it.put(20, 22, "fourth", "23");
			it.put(30, 50, "third", "24");
		} catch (RuleWarnings e) {
			e.printStackTrace();
		}
		
		System.out.println(String.format("%s %d %d", it.contains(20, 25), it.height(), it.size()));
	}	
}
