package com.epi.trie;


/**
 * Leet code. Solution -> Accepted
 * 
 * Run Time: 144 ms. Above average run time. Trick was to insert words in trie in reverse order  
 * 
 */
public class StreamChecker {
	private TNode root;
	private StringBuilder sb;
	
	public StreamChecker(String[] words) {
		this.root = new TNode();
		this.sb = new StringBuilder();
		for(String word: words)
			this.insert(word);
	}
	
	/**
	 * Insert word in Trie in the reverse order.
	 * 
	 * @param word
	 */
	public void insert(String word) {
		TNode node = this.root;

		for(int i = word.length() - 1; i > -1; i--) {
			int idx = word.charAt(i) - 'a';

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
	 * @param letter Stream character
	 * @return true if the letter on stream is accepted, false otherwise
	 */
	public boolean query(char letter) {
		this.sb.append(letter);
		TNode iter = this.root;
		
		for(int i = this.sb.length() - 1; i > -1; i--) {
			iter = iter.child[this.sb.charAt(i) - 'a'];
			
			if(iter == null)
				return false;
			
			if(iter.word != null)
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String[] words = new String[] {"cd", "f", "kl"};
		StreamChecker s = new StreamChecker(words);
		
		for(char ch: new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l'}) {
			System.out.println(s.query(ch));
		}
	}
}
