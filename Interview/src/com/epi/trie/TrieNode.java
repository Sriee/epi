package com.epi.trie;

import java.util.List;
import java.util.ArrayList;


public class TrieNode {

	public char letter;
	public List<TrieNode> child = null;
	public boolean isWord = false;
			
	public TrieNode() {
		this.child = new ArrayList<>(26);
		for(int i = 0; i < 26; i++) {
			this.child.add(null);
		}
	}
	
	public TrieNode(char ch) {
		this.letter = ch;
		this.child = new ArrayList<>(26);
		for(int i = 0; i < 26; i++) {
			this.child.add(null);
		}
	}
	
	@Override
	public String toString() {
		return "TrieNode(" + this.letter + ", " + this.isWord + ")";
	}
}
