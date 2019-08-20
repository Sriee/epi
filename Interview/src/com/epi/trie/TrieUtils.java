package com.epi.trie;

/**
 * Leet code. Solution -> Accepted
 * 
 * Trie (Prefix Tree) Implementation
 * 
 */
public class TrieUtils {

	public TrieNode root = null;
	
	public TrieUtils() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode iter = this.root;
		
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(iter.child.get(idx) == null) {
				iter.child.set(idx, new TrieNode(word.charAt(i)));
			}
			iter = iter.child.get(idx);
		}
		iter.isWord = true;
	}
	
	public boolean search(String word) {
		TrieNode iter = this.root;
		
		for(int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if(iter.child.get(idx) == null)
				return false;
			iter = iter.child.get(idx);
		}
		return iter.isWord;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode iter = this.root;
		
		for(int i = 0; i < prefix.length(); i++) {
			int idx = prefix.charAt(i) - 'a';
			if(iter.child.get(idx) == null)
				return false;
			iter = iter.child.get(idx);
		}
		return true;
	}
}
