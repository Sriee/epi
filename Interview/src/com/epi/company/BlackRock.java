package com.epi.company;

import java.text.DecimalFormat;
import java.util.*;

public class BlackRock {

	public long findMax(int n,String tree){
		if(tree == null || tree.trim().isEmpty()) return 0;
		
		String[] tokens = tree.split(" ");
		Long[] treeArray = new Long[tokens.length];
		
		long levelSum = 0, odd = 0, even = 0;
		int level = 1, current = 1, next = 0, idx;
		
		for(int i = 0; i < tokens.length; i++){
			treeArray[i] = (tokens[i].equals("#")) ? null : Long.parseLong(tokens[i]);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		while(!q.isEmpty()){
			current--;
			idx = q.remove();
			if(idx < treeArray.length && treeArray[idx] != null){
				
				levelSum += treeArray[idx];
				
				q.add(2 * idx + 1);	// Left
				q.add(2 * idx + 2); // Right
				next += 2;
			}	
			
			if(current == 0){
				current = next;
				next = 0;
				if(level % 2 == 0){
					even += levelSum;
				} else {
					odd += levelSum;
				}
				levelSum = 0;
				level++;
			}
		}
		return Math.max(odd, even);		
	}
	
	public int[] arbitage(String[] quotes){
		if(quotes == null || quotes.length == 0) return null;
		
		int[] result = new int[quotes.length];
		double initial = 100000;
		
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(java.math.RoundingMode.FLOOR);
		
		for(int i = 0; i < quotes.length; i++){
			double arb = 0.0;
			String[] values = quotes[i].split(" ");
			double[] tokens = new double[3];
			tokens[0] = Double.parseDouble(values[0]);
			tokens[1] = Double.parseDouble(values[1]);
			tokens[2] = Double.parseDouble(values[2]);
			
			try{
				arb = Double.parseDouble(df.format(initial / tokens[0]));
				arb = Double.parseDouble(df.format(arb / tokens[1]));
				arb = Double.parseDouble(df.format(arb / tokens[2]));
				
				result[i] = (int)(arb - initial);
				if(result[i] < 0) result[i] = 0;
			} catch (Exception e){
				result[i] = 0;
			}
		}
		return result;
	}
	
	public String electionWinner(String[] events){
		String winner = null;
		int max = Integer.MIN_VALUE;
		Map<String, Integer> vote = new HashMap<>();
		for(String candidate : events){
			if(vote.isEmpty() || !vote.containsKey(candidate)){
				vote.put(candidate, 1);
			} else {
				vote.put(candidate, vote.get(candidate) + 1);
			}
		}
		
		for(Map.Entry<String, Integer> entry : vote.entrySet()){
			if(entry.getValue() > max){
				max = entry.getValue();
				winner = entry.getKey();
			} else if(entry.getValue() == max){
				winner = (entry.getKey().compareTo(winner) > 0) ? entry.getKey() : winner;
			}
		}
		return winner;
	}
	
	public static void main(String[] args) {
		BlackRock br = new BlackRock();
		System.out.println("Max " + br.findMax(6, "3 4 5 1 3 # 1"));
	}

}
