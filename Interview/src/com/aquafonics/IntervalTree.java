package com.aquafonics;

import java.util.*;

public class IntervalTree {

	private IntervalNode root = null;
	private int size = 0;
	private List<String> warnings = null; 
	
	public void put(Interval interval, String ruleId, String sensorId) throws RuleConflictException, RuleWarnings{
		warnings = new ArrayList<>();
		if(this.isEmpty()) {
			this.root = new IntervalNode(interval, ruleId, sensorId); 
		} else {
			this.put(interval, root, ruleId, sensorId);
		}
		this.size += 1;
		if(!warnings.isEmpty())
			throw new RuleWarnings(warnings.toString());
	}
	
	/**
	 * Check for conflict if the ranges intersect for the same sensors, interval won't be added if rule
	 * conflict arises 
	 *  
	 * If the interval ranges intersect for different sensors then add the sensor Id's to the warning
	 * list. The interval will be added to the interval tree 
	 *   
	 * @param currentInterval
	 * @param currentNode
	 * @param ruleId
	 * @param sensorId
	 * @throws RuleConflictException if intervals of the same sensor intersect
	 */
	private void put(Interval currentInterval, IntervalNode currentNode, String ruleId, String sensorId) throws RuleConflictException{
		// Check for overlapping intervals
		if(currentInterval.intersects(currentNode.interval)) {
			if(currentNode.sensors.contains(sensorId))	 
				throw new RuleConflictException(currentNode.rules.toString());
			else
				this.warnings.add(currentNode.sensors.toString());
		}
		
		int cmp = currentInterval.compareTo(currentNode.interval);
		if(cmp < 0) {
			if(currentNode.hasLeftChild())
				this.put(currentInterval, currentNode.left, ruleId, sensorId);
			else
				currentNode.left = new IntervalNode(currentInterval, ruleId, sensorId);
		} else if (cmp > 0) {
			if(currentNode.hasRightChild())
				this.put(currentInterval, currentNode.right, ruleId, sensorId);
			else
				currentNode.right = new IntervalNode(currentInterval, ruleId, sensorId);
		} else { 				// For sensors having the same interval  
			currentNode.addRule(ruleId);
		}
		// update N & max end point
		this.update(currentNode);
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
	
	private void print() {
		if(this.isEmpty()) {
			System.out.println("Empty.");
			return;
		}
		
		int currentLevel = 1, nextLevel = 0, height = 1;
		boolean print = true;
		Queue<IntervalNode> queue = new LinkedList<>();
		queue.add(this.root);
		while(!queue.isEmpty()) {
			currentLevel--;
			IntervalNode node = queue.remove();
			
			if(node != null) {
				if(print) {
					System.out.println("Height " + height);
					print = false;
				}
				System.out.println("\t" + node);
				queue.add(node.left);
				queue.add(node.right);
				nextLevel += 2;
			}
			if(currentLevel == 0) {
				height++;
				print = true;
				currentLevel = nextLevel;
				nextLevel = 0;
			}
			
		}
	}
	
	public static void main(String[] args) {
		IntervalTree it = new IntervalTree();
		
		try {
			it.put(new Interval(15, 20), "first", "21");
			it.put(new Interval(10, 30), "second", "22");
			it.put(new Interval(17, 19), "third", "23");
			it.put(new Interval(5, 20), "fourth", "24");
			it.put(new Interval(12, 15), "fifth", "25");
			it.put(new Interval(30, 40), "sixth", "26");
		} catch (RuleWarnings e) {
			e.printStackTrace();
		} catch (RuleConflictException e) {
			e.printStackTrace();
		}
		
		it.print();
	}	
}
