package com.epi.trie;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 955 ms. Below average run time. It's okay since hard problem got accepted (:+1)  
 * 
 */
public class StreamChecker {
	private TNode root;
	private Queue<TNode> queue;
	
	public StreamChecker() { 
		root = new TNode();
		this.queue = new LinkedList<>();
	}
	
	public StreamChecker(String[] words) {
		this.root = new TNode();
		this.queue = new LinkedList<>();
		for(String word: words)
			this.insert(word);
	}
	
	/**
	 * Insert word in Trie (Prefix tree)
	 * 
	 * @param word
	 */
	public void insert(String word) {
		TNode node = this.root;

		for(char ch: word.toCharArray()) {
			int idx = ch - 'a';

			if(node.child[idx] == null)
				node.child[idx] = new TNode();

			node = node.child[idx];
		}

		node.word = word;
	}
	
	/**
	 * For some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) 
	 * spell one of the words in the given list.
	 * 
	 * @param letter - Stream character
	 * @return true if the letter on stream is accepted, false otherwise
	 */
	public boolean query(char letter) {		
		boolean found = false;
		int idx = letter - 'a';
		
		if(queue.isEmpty()) {
			if(this.root.child[idx] != null) {
				queue.add(this.root.child[idx]);
				found = this.root.child[idx].word != null;
			} 
		} else {
				int curQ = this.queue.size();
				int i = 0;
				
				while(i < curQ) {
					TNode node = this.queue.remove();
					
					if(node.child[idx] != null) {
						this.queue.add(node.child[idx]);
						found = node.child[idx].word != null;
					}
					i++;
				}
				
				if(this.root.child[idx] != null) {
					this.queue.add(this.root.child[idx]);
					if(this.root.child[idx].word != null)
						found = true;
				}
		}
		
		return found;
	}
	
	public static void main(String[] args) {
		String[] words = new String[] {"cd", "f", "kl"};
		StreamChecker s = new StreamChecker(words);
		
		for(char ch: new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l'}) {
			System.out.println(s.query(ch));
		}
	}
}
